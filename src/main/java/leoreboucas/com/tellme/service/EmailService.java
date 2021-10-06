package leoreboucas.com.tellme.service;

import leoreboucas.com.tellme.model.Advised;
import leoreboucas.com.tellme.model.Adviser;

public interface EmailService {
    void emailAdviser(Adviser adviser, Advised advised);
    void emailResult(Advised advised);
}