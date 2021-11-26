package leoreboucas.com.tellme.service;

import leoreboucas.com.tellme.dto.RespondentMessageDto;
import leoreboucas.com.tellme.dto.SurveyMessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender javaMailSender;

   @Override
    public void emailRespondents(SurveyMessageDto surveyMessageDto) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("leonardo1209@gmail.com");
        message.setSubject("Tellme - Survey for " + surveyMessageDto.getName());

        for (RespondentMessageDto respondentMessageDto : surveyMessageDto.getRespondents()) {
            message.setTo(respondentMessageDto.getEmail());
            message.setText("http://localhost:4200/to-answer/" + surveyMessageDto.getId() + "/" + respondentMessageDto.getId());

            javaMailSender.send(message);
            log.info("Email sent to surveyId: " + surveyMessageDto.getId() + " respondentId: " +  respondentMessageDto.getId());
        }
    }

    @Override
    public void emailResult(SurveyMessageDto surveyMessageDto) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("leonardo1209@gmail.com");
        message.setTo(surveyMessageDto.getEmail());
        message.setSubject("Tellme - Survey results for " + surveyMessageDto.getName());
        message.setText("http://localhost:4200/answers/"+ surveyMessageDto.getId());

        javaMailSender.send(message);
        log.info("Result email sent to surveyId: " + surveyMessageDto.getId());
    }
}
