package leoreboucas.com.tellme.service;

import leoreboucas.com.tellme.dto.RespondentDto;
import leoreboucas.com.tellme.dto.SurveyDto;
import leoreboucas.com.tellme.entity.Respondent;
import leoreboucas.com.tellme.entity.Survey;

public interface EmailService {
    void emailRespondent(Respondent respondent, Survey survey);
    void emailRespondent(RespondentDto respondent, SurveyDto survey);
    void emailResult(Survey survey);
}