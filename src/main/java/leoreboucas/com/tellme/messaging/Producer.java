package leoreboucas.com.tellme.messaging;

import leoreboucas.com.tellme.dto.SurveyDto;
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
    private KafkaTemplate<String, SurveyDto> kafkaTemplate;

    public void publishEmailRespondents(SurveyDto surveyDto) {

        this.kafkaTemplate.send(emailRespondentsTopic, surveyDto);
        log.info("Published emailRespondentsTopic for surveyId: " + surveyDto.getId()
                + " with " + surveyDto.getRespondents().size() + " respondents");
    }

    public void publishEmailResult(SurveyDto surveyDto) {

        this.kafkaTemplate.send(emailResultTopic, surveyDto);
        log.info("Published emailResultTopic for surveyId: " + surveyDto.getId());
    }
}
