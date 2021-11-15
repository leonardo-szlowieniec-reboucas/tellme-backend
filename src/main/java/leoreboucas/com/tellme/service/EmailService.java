package leoreboucas.com.tellme.service;

import leoreboucas.com.tellme.entity.Respondent;
import leoreboucas.com.tellme.entity.Survey;

public interface EmailService {
    void emailRespondent(Respondent respondent, Survey survey);
    void emailResult(Survey survey);
}