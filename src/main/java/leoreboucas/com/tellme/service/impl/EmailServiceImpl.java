package leoreboucas.com.tellme.service.impl;

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

//    public void sendEmail(Adviser adviser, Advised advised) {
    public void emailAdviser(Adviser adviser, Advised advised) {
        //TODO: add validation
        //TODO: handle exception
        //TODO: add log
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("leonardo1209@gmail.com");
        message.setTo(adviser.getEmail());
        message.setSubject("Tellme - Feedback for " + advised.getName());
        message.setText("http://localhost:4200/give-advice/"+adviser.getId());

        javaMailSender.send(message);
    }

//    public void sendEmail(Advised advised) {
    @Override
    public void emailResult(Advised advised) {
        //TODO: add validation
        //TODO: handle exception
        //TODO: add log
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("leonardo1209@gmail.com");
        message.setTo(advised.getEmail());
        message.setSubject("Tellme - Advice for " + advised.getName());
        message.setText("http://localhost:4200/list-advice/"+advised.getId());

        javaMailSender.send(message);
    }
}
