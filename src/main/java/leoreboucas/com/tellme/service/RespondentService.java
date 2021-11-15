package leoreboucas.com.tellme.service;

import leoreboucas.com.tellme.entity.Answer;
import leoreboucas.com.tellme.entity.Survey;
import leoreboucas.com.tellme.entity.Respondent;

public interface RespondentService {
//    Respondent findById(Long id);
    Survey findSurveyByRespondentId(Long respondentId);
//    Advice saveAdvice(Long idAdviser, Advice advice);
    void saveAnswer(Long respondentId, Answer answer);
}
