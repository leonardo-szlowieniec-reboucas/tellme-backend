package leoreboucas.com.tellme.service.impl;

import leoreboucas.com.tellme.entity.Answer;
import leoreboucas.com.tellme.entity.Survey;
import leoreboucas.com.tellme.entity.Respondent;
import leoreboucas.com.tellme.exception.ResourceNotFoundException;
import leoreboucas.com.tellme.repository.AnswerRepository;
import leoreboucas.com.tellme.repository.SurveyRepository;
import leoreboucas.com.tellme.repository.RespondentRepository;
import leoreboucas.com.tellme.service.SurveyService;
import leoreboucas.com.tellme.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SurveyServiceImpl implements SurveyService {
    private final SurveyRepository surveyRepository;
    private final EmailService emailService;
    private final AnswerRepository answerRepository;
    private final RespondentRepository respondentRepository;

    @Transactional
    @Override
    public Survey saveSurvey(Survey survey) {
        //TODO: add log
        survey = setSurveyInRespondent(survey);
        survey = surveyRepository.save(survey);
        sendEmail(survey);
        return survey;
    }

    @Override
    public Survey findById(Long id) {

        return surveyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Survey not found with id  : " + id));
    }

    @Override
    public List<Answer> findAnswersBySurveyId(Long surveyId) {

        Survey survey = surveyRepository.findById(surveyId)
                .orElseThrow(() -> new ResourceNotFoundException("Survey not found with id: "+ surveyId));

        // Return answers only if all respondent answered it
        if (!respondentRepository.existsBySurveyAndIsDone(survey, false))
            return answerRepository.findBySurveyId(surveyId);
        return new ArrayList<>();
    }

    @Transactional
//    @Override
    public void saveAnswer(Long surveyId, Long respondentId, Answer answer) {

        Respondent respondent = respondentRepository.findById(respondentId)
                .orElseThrow(() -> new ResourceNotFoundException("Respondent not found with id: " + respondentId));
        if (!respondent.getIsDone()) {
            Survey survey = surveyRepository.findById(surveyId)
                    .orElseThrow(() -> new ResourceNotFoundException("Survey not found with id: " + surveyId));

            answer.setSurveyId(survey.getId());
            answerRepository.save(answer);

            respondent.setIsDone(true);
            respondentRepository.save(respondent);

            //Send final email if all respondents have given answer.
            if (!respondentRepository.existsBySurveyAndIsDone(survey, false))
                emailService.emailResult(survey);
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

    private void sendEmail(Survey survey) {
        for (Respondent respondent : survey.getRespondents())
            emailService.emailRespondent(respondent, survey);
    }
}
