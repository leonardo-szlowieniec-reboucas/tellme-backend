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

        survey = setSurveyInRespondent(survey);
        survey = surveyRepository.save(survey);
        log.info("Created surveyId: " + survey.getId());

        producer.publishEmailRespondents(mapper.surveyToSurveyMessageDto(survey));

        return survey;
    }

    @Override
    public Survey findById(Long id) {

        return surveyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Survey not found with surveyId: " + id + " (findById)"));
    }

//    .orElseThrow(() -> new ResourceNotFoundException("Survey not found with surveyId: " + id + " " +
//            StackWalker.getInstance().walk(frames -> frames
//            .findFirst()
//            .map(StackWalker.StackFrame::getMethodName))
//            ));


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

            answer.setSurveyId(survey.getId());
            //TODO to confirm if I need to create a new instance of answer or if I can used the parameter to receive the return.
            answer = answerRepository.save(answer);
            log.info("Saved answer for surveyId: " + answer.getSurveyId() + " and answerId: " + answer.getId());

            //Send result email if all respondents answered.
            if (!respondentRepository.existsBySurveyAndIsDone(survey, false))
                producer.publishEmailResult(mapper.surveyToSurveyMessageDto(survey));
        }
    }

    //To set advised in adviser for Hibernate to be able to persist this relation
    private Survey setSurveyInRespondent(Survey survey) {

        Respondent respondent;
        List<Respondent> respondents = new ArrayList<>();

        for (Respondent respondent1 : survey.getRespondents()) {
            respondent = new Respondent();
            respondent.setEmail(respondent1.getEmail());
            respondent.setSurvey(survey);
            respondent.setIsDone(false);
            respondents.add(respondent);
        }
        survey.setRespondents(respondents);

        return survey;
    }
}