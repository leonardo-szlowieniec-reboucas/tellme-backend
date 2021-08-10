package leoreboucas.com.tellme.service.impl;

import leoreboucas.com.tellme.model.Advice;
import leoreboucas.com.tellme.model.Advised;
import leoreboucas.com.tellme.model.Adviser;
import leoreboucas.com.tellme.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    JavaMailSender javaMailSender;

    public void sendEmail() {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("leonardo1209@gmail.com");
        message.setTo("leonardo1209@gmail.com");
        message.setSubject("Test Subject");
        message.setText("Test Body");

        javaMailSender.send(message);

      //  return "Mail sent successfully";
    }

    public void sendEmail(String emailTo) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("leonardo1209@gmail.com");
        message.setTo(emailTo);
        message.setSubject("Test Subject");
        message.setText("Test Body");

        javaMailSender.send(message);
    }

    public void sendEmail(Adviser adviser, Advised advised) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("leonardo1209@gmail.com");
        message.setTo(adviser.getEmail());
        message.setSubject("Tellme - Feedback for " + advised.getName());
        message.setText("http://localhost:4200/save-advice/"+advised.getId());

        javaMailSender.send(message);
    }
}
