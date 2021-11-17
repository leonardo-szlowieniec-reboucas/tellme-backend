package leoreboucas.com.tellme.service;

import leoreboucas.com.tellme.dto.RespondentDto;
import leoreboucas.com.tellme.dto.SurveyDto;
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
    public void emailRespondents(SurveyDto surveyDto) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("leonardo1209@gmail.com");
        message.setSubject("Tellme - Survey for " + surveyDto.getName());

        for (RespondentDto respondentDto : surveyDto.getRespondents()) {
            message.setTo(respondentDto.getEmail());
            message.setText("http://localhost:4200/to-answer/" + surveyDto.getId() + "/" + respondentDto.getId());

            javaMailSender.send(message);
            log.info("Email sent to surveyId: " + surveyDto.getId() + " respondentId: " +  respondentDto.getId());
        }
    }

    @Override
    public void emailResult(SurveyDto surveyDto) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("leonardo1209@gmail.com");
        message.setTo(surveyDto.getEmail());
        message.setSubject("Tellme - Survey results for " + surveyDto.getName());
        message.setText("http://localhost:4200/answers/"+ surveyDto.getId());

        javaMailSender.send(message);
        log.info("Result email sent to surveyId: " + surveyDto.getId());
    }
}
