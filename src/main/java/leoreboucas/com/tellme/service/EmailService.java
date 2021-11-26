package leoreboucas.com.tellme.service;

import leoreboucas.com.tellme.dto.SurveyMessageDto;

public interface EmailService {
    void emailRespondents(SurveyMessageDto surveyMessageDto);
    void emailResult(SurveyMessageDto surveyMessageDto);
}