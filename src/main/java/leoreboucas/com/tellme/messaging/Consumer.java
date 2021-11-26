package leoreboucas.com.tellme.messaging;

import leoreboucas.com.tellme.dto.SurveyMessageDto;
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
    public void consumeEmailRespondents(SurveyMessageDto surveyMessageDto) {

        log.debug("Consuming emailRespondentsTopic for surveyId: " + surveyMessageDto.getId());
        emailService.emailRespondents(surveyMessageDto);
        log.debug("Consumed emailRespondentsTopic for surveyId: " + surveyMessageDto.getId());
    }

    @KafkaListener(topics = "emailResultTopic", groupId = "tellmeGroup")
    public void consumeEmailResult(SurveyMessageDto surveyMessageDto) {

        log.debug("Consuming emailResultTopic for surveyId: " + surveyMessageDto.getId());
        emailService.emailResult(surveyMessageDto);
        log.debug("Consumed emailResultTopic for surveyId: " + surveyMessageDto.getId());
    }
}