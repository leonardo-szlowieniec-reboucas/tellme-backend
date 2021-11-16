package leoreboucas.com.tellme.messaging;

import leoreboucas.com.tellme.dto.SurveyDto;
import leoreboucas.com.tellme.entity.Respondent;
import leoreboucas.com.tellme.entity.Survey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {
//    private static final String topic = "mytopic";
    private static final String topicSurvey = "sendEmail";

//    @Autowired
//    private KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    private KafkaTemplate<String, SurveyDto> kafkaTemplateSurvey;

//    public void publishToTopic(String message) {
//
//        System.out.println("published: " + topic);
//
//        this.kafkaTemplate.send(topic, message);
//    }

    public void publishToTopic(SurveyDto surveyDto) {

        System.out.println(">>>> publish: " + topicSurvey);

        this.kafkaTemplateSurvey.send(topicSurvey, surveyDto);
    }


}
