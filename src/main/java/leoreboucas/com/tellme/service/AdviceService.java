package leoreboucas.com.tellme.service;

import leoreboucas.com.tellme.model.Advice;

import java.util.List;

public interface AdviceService {
    List<Advice> findByIdAdvised(Long idAdvised);
    Advice saveAdvice(Advice advice);
}
