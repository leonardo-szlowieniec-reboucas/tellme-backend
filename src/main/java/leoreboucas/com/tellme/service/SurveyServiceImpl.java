package leoreboucas.com.tellme.service;

import leoreboucas.com.tellme.entity.Answer;
import leoreboucas.com.tellme.entity.Survey;
import leoreboucas.com.tellme.entity.Respondent;
import leoreboucas.com.tellme.exception.ResourceNotFoundException;
import leoreboucas.com.tellme.mapper.TellmeMapper;
import leoreboucas.com.tellme.messaging.Producer;
import leoreboucas.com.tellme.repository.AnswerRepository;
import leoreboucas.com.tellme.repository.SurveyRepository;
import leoreboucas.com.tellme.repository.RespondentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SurveyServiceImpl implements SurveyService {
    private final SurveyRepository surveyRepository;
    private final AnswerRepository answerRepository;
    private final RespondentRepository respondentRepository;
    private final Producer producer;
    private final TellmeMapper mapper;

    @Transactional
    @Override
    public Survey saveSurvey(Survey survey) {
        Survey surveyRespondents = setSurveyInRespondent(survey);
        Survey surveySaved = surveyRepository.save(surveyRespondents);
        log.info("Created surveyId: " + surveySaved.getId());

        producer.publishEmailRespondents(mapper.surveyToSurveyMessageDto(surveySaved));

        return surveySaved;
    }

    @Override
    public Survey findById(Long id) {
        return surveyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Survey not found with surveyId: " + id + " (findById)"));
    }

    @Override
    public List<Answer> findAnswersBySurveyId(Long surveyId) {
        Survey survey = surveyRepository.findById(surveyId)
                .orElseThrow(() -> new ResourceNotFoundException("Survey not found with surveyId: "+ surveyId + " (findAnswersBySurveyId)"));

        // Return answers only if all respondent answered it
        if (!respondentRepository.existsBySurveyAndIsDone(survey, false))
            return answerRepository.findBySurveyId(surveyId);

        return new ArrayList<>();
    }

    @Transactional
    @Override
    public void saveAnswer(Long surveyId, Long respondentId, Answer answer) {
        Respondent respondent = respondentRepository.findById(respondentId)
                .orElseThrow(() -> new ResourceNotFoundException("Respondent not found with respondentId: " + respondentId + " (saveAnswer)"));

        if (!respondent.getIsDone()) {
            Survey survey = surveyRepository.findById(surveyId)
                    .orElseThrow(() -> new ResourceNotFoundException("Survey not found with surveyId: " + surveyId + " (saveAnswer)"));

            respondent.setIsDone(true);
            respondentRepository.save(respondent);
            // Do not log who gave the answer to keep them anonymous
            log.info("A respondent answered");

            Answer answerSurvey = setSurveyIdInAnswer(answer, survey);
            Answer answerSaved = answerRepository.save(answerSurvey);
            log.info("Saved answer for surveyId: " + answerSaved.getSurveyId() + " and answerId: " + answerSaved.getId());

            //Send result email if all respondents answered.
            if (!respondentRepository.existsBySurveyAndIsDone(survey, false))
                producer.publishEmailResult(mapper.surveyToSurveyMessageDto(survey));
        }
    }

    //To set advised in adviser for Hibernate to be able to persist this relation
    private Survey setSurveyInRespondent(Survey survey) {
        Respondent newRespondent;
        List<Respondent> respondents = new ArrayList<>();
        Survey surveyRespondents = getNewSurvey(survey);

        for (Respondent respondent : survey.getRespondents()) {
            newRespondent = new Respondent();

            newRespondent.setEmail(respondent.getEmail());
            newRespondent.setIsDone(false);
            newRespondent.setSurvey(surveyRespondents);

            respondents.add(newRespondent);
        }
        surveyRespondents.setRespondents(respondents);

        return surveyRespondents;
    }

    private Survey getNewSurvey(Survey survey) {
        Survey newSurvey = new Survey();

        newSurvey.setTitle(survey.getTitle());
        newSurvey.setDescription(survey.getDescription());
        newSurvey.setEmail(survey.getEmail());

        return newSurvey;
    }

    private Answer setSurveyIdInAnswer(Answer answer, Survey survey) {
        Answer answerSurvey = new Answer();

        answerSurvey.setSurveyId(survey.getId());
        answerSurvey.setDescription(answer.getDescription());

        return answerSurvey;
    }
}