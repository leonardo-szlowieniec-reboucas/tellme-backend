package leoreboucas.com.tellme.service;

import leoreboucas.com.tellme.model.Advised;
import leoreboucas.com.tellme.model.Adviser;

import java.util.List;

public interface EmailService {
    void sendEmail();
    void sendEmail(String emailTo);
    void sendEmail(Adviser adviser, Advised advised);
}
