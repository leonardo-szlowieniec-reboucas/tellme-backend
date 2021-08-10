package leoreboucas.com.tellme.service.impl;

import leoreboucas.com.tellme.model.Advice;
import leoreboucas.com.tellme.repository.AdviceRepository;
import leoreboucas.com.tellme.service.AdviceService;
import leoreboucas.com.tellme.service.AdviserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdviceServiceImpl implements AdviceService {
    private AdviceRepository adviceRepository;
    private AdviserService adviserService;

    public AdviceServiceImpl(AdviceRepository adviceRepository) {
        this.adviceRepository = adviceRepository;
    }

    @Override
    public List<Advice> findByIdAdvised(Long advised) {
        return adviceRepository.findByIdAdvised(advised);
    }

    @Override
    public Advice saveAdvice(Advice advice) {
        return adviceRepository.save(advice);
    }

//    @Override
//    public Advice saveAdvice(Long idAdviser, Advice advice) {
//        if (!adviserService.isDone(idAdviser)) {
//
//        }
//        return adviceRepository.save(advice);
//    }
}
