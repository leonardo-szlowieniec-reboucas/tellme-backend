package leoreboucas.com.tellme.messaging;

import leoreboucas.com.tellme.dto.RespondentDto;
import leoreboucas.com.tellme.dto.SurveyDto;
import leoreboucas.com.tellme.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Consumer {

    @Autowired
    private EmailService emailService;

    @KafkaListener(topics = "emailRespondentsTopic", groupId = "tellmeGroup")
    public void consumeEmailRespondents(SurveyDto surveyDto) {

        log.debug("Consuming emailRespondentsTopic for surveyId: " + surveyDto.getId());
        emailService.emailRespondents(surveyDto);
        log.debug("Consumed emailRespondentsTopic for surveyId: " + surveyDto.getId());
    }

    @KafkaListener(topics = "emailResultTopic", groupId = "tellmeGroup")
    public void consumeEmailResult(SurveyDto surveyDto) {

        log.debug("Consuming emailResultTopic for surveyId: " + surveyDto.getId());
        emailService.emailResult(surveyDto);
        log.debug("Consumed emailResultTopic for surveyId: " + surveyDto.getId());
    }
}