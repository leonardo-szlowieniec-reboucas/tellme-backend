package leoreboucas.com.tellme.service;

import leoreboucas.com.tellme.model.Advised;
import leoreboucas.com.tellme.model.Adviser;

public interface EmailService {
    void sendEmail(Adviser adviser, Advised advised);
    void sendEmail(Advised advised);
}
