package leoreboucas.com.tellme.service;

import leoreboucas.com.tellme.model.Advice;
import leoreboucas.com.tellme.model.Advised;
import leoreboucas.com.tellme.model.Adviser;

public interface AdviserService {
    Adviser findById(Long id);
    Advised findAdvisedByIdAdviser(Long idAdviser);
//    Advice saveAdvice(Long idAdviser, Advice advice);
    void giveAdvice(Long idAdviser, Advice advice);
}
