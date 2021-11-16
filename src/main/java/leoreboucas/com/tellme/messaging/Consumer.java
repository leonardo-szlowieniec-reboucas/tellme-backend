package leoreboucas.com.tellme.messaging;

import leoreboucas.com.tellme.dto.RespondentDto;
import leoreboucas.com.tellme.dto.SurveyDto;
import leoreboucas.com.tellme.entity.Respondent;
import leoreboucas.com.tellme.entity.Survey;
import leoreboucas.com.tellme.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    @Autowired
    private EmailService emailService;
//
//    @KafkaListener(topics = "mytopic", groupId = "myGroup")
//    public void consumerFromTopic(String message) {
//        System.out.println(">>>>> consumed: " +message);
//    }

    @KafkaListener(topics = "sendEmail", groupId = "tellmeGroup")
    public void consumerFromTopic(SurveyDto surveyDto) {
        System.out.println(">>>> consume: " +surveyDto);
        sendEmail(surveyDto);
    }

    private void sendEmail(SurveyDto surveyDto) {
        for (RespondentDto respondentDto : surveyDto.getRespondents()) {
            System.out.println(">>>> sending email: " + respondentDto.getEmail());
            emailService.emailRespondent(respondentDto, surveyDto);
        }
    }
}
