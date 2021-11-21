package leoreboucas.com.tellme.messaging;

import leoreboucas.com.tellme.dto.SurveyDto;
import leoreboucas.com.tellme.dto.SurveyMessageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Producer {

    private static final String emailRespondentsTopic = "emailRespondentsTopic";
    private static final String emailResultTopic = "emailResultTopic";
    @Autowired
    private KafkaTemplate<String, SurveyMessageDto> kafkaTemplate;

    public void publishEmailRespondents(SurveyMessageDto surveyMessageDto) {

        this.kafkaTemplate.send(emailRespondentsTopic, surveyMessageDto);
        log.info("Published emailRespondentsTopic for surveyId: " + surveyMessageDto.getId()
                + " with " + surveyMessageDto.getRespondents().size() + " respondents");
    }

    public void publishEmailResult(SurveyMessageDto surveyMessageDto) {

        this.kafkaTemplate.send(emailResultTopic, surveyMessageDto);
        log.info("Published emailResultTopic for surveyId: " + surveyMessageDto.getId());
    }
}
