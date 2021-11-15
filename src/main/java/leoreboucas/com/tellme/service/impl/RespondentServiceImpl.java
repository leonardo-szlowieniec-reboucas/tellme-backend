package leoreboucas.com.tellme.service.impl;

import leoreboucas.com.tellme.entity.Answer;
import leoreboucas.com.tellme.entity.Survey;
import leoreboucas.com.tellme.entity.Respondent;
import leoreboucas.com.tellme.exception.ResourceNotFoundException;
import leoreboucas.com.tellme.repository.AnswerRepository;
import leoreboucas.com.tellme.repository.SurveyRepository;
import leoreboucas.com.tellme.repository.RespondentRepository;
import leoreboucas.com.tellme.service.RespondentService;
import leoreboucas.com.tellme.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RespondentServiceImpl implements RespondentService {
    @Autowired
    private RespondentRepository respondentRepository;
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private SurveyRepository surveyRepository;
    @Autowired
    private EmailService emailService;

//    @Override
//    public Respondent findById(Long id) {
//
//        return respondentRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Respondent not found with id: " + id));
//    }

    @Transactional
    @Override
    public Survey findSurveyByRespondentId(Long respondentId) {
        //TODO: add validation
        //TODO: handle exception
        //TODO: add log
        Respondent respondent = respondentRepository.findById(respondentId)
                .orElseThrow(() -> new ResourceNotFoundException("Respondent not found with id: " + respondentId));

        return surveyRepository.getById(respondent.getSurvey().getId());
    }

    @Transactional
    @Override
    public void saveAnswer(Long respondentId, Answer answer) {
        //TODO: add validation
        //TODO: handle exception
        //TODO: add log
        Respondent respondent = respondentRepository.getById(respondentId);
        if (!respondent.getIsDone()) {
            Survey survey = surveyRepository.getById(respondent.getSurvey().getId());
//            Advised advised = advisedRepository.getById(adviser.getIdAdvised());

            answer.setSurveyId(survey.getId());
            answerRepository.save(answer);

            respondent.setIsDone(true);
            respondentRepository.save(respondent);

            //Send final email to the advised if all advisers have given advice.
            if (!respondentRepository.existsBySurveyAndIsDone(survey, false))
//                if (!adviserRepository.existsByIdAdvisedAndIsDone(advised.getId(), false))
                emailService.emailResult(survey);
        }
    }

//    @Transactional
//    @Override
//    public Advice saveAdvice(Long idAdviser, Advice advice) {
//        //TODO: add validation
//        //TODO: handle exception
//        //TODO: add log
//        Adviser adviser = adviserRepository.getById(idAdviser);
//        if (!adviser.getIsDone()) {
//            adviser.setIsDone(true);
//            adviserRepository.save(adviser);
//            advice.setIdAdvised(adviser.getAdvised().getId());
//            advice = adviceRepository.save(advice);
//            if (!adviserRepository.existsByAdvisedAndIsDone(adviser.getAdvised(), false))
//                emailService.sendEmail(adviser.getAdvised());
//        }
//        return advice;
//    }
}
