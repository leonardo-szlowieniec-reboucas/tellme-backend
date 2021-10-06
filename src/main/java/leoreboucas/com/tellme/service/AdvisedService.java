package leoreboucas.com.tellme.service;

import leoreboucas.com.tellme.model.Advice;
import leoreboucas.com.tellme.model.Advised;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AdvisedService {
    Advised saveAdvised(Advised advised);
    Advised findById(Long id);
    List<Advice> findAdviceByIdAdvised(Long idAdvised);
//    ResponseEntity<Advised> findById(Long id);
}
