package leoreboucas.com.tellme.service;

import leoreboucas.com.tellme.entity.Answer;
import leoreboucas.com.tellme.entity.Survey;

import java.util.List;

public interface SurveyService {
    Survey saveSurvey(Survey survey);
    Survey findById(Long id);
    List<Answer> findAnswersBySurveyId(Long surveyId);
    void saveAnswer(Long surveyId, Long respondentId, Answer answer);
//    ResponseEntity<Advised> findById(Long id);
}
