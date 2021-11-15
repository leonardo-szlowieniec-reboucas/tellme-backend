package leoreboucas.com.tellme.service;

import leoreboucas.com.tellme.entity.Respondent;
import leoreboucas.com.tellme.entity.Survey;
import leoreboucas.com.tellme.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    public void emailRespondent(Respondent respondent, Survey survey) {
        //TODO: add log
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("leonardo1209@gmail.com");
        message.setTo(respondent.getEmail());
        message.setSubject("Tellme - Survey for " + survey.getTitle());
        message.setText("http://localhost:4200/to-answer/" + survey.getId() + "/" + respondent.getId());

        javaMailSender.send(message);
    }

    @Override
    public void emailResult(Survey survey) {
        //TODO: add log
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("leonardo1209@gmail.com");
        message.setTo(survey.getEmail());
        message.setSubject("Tellme - Survey results for " + survey.getTitle());
        message.setText("http://localhost:4200/answers/"+ survey.getId());

        javaMailSender.send(message);
    }
}
