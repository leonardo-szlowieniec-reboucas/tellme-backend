package leoreboucas.com.tellme.service.impl;

import leoreboucas.com.tellme.model.Advice;
import leoreboucas.com.tellme.model.Adviser;
import leoreboucas.com.tellme.repository.AdviceRepository;
import leoreboucas.com.tellme.repository.AdvisedRepository;
import leoreboucas.com.tellme.repository.AdviserRepository;
import leoreboucas.com.tellme.service.AdviceService;
import leoreboucas.com.tellme.service.AdviserService;
import leoreboucas.com.tellme.service.EmailService;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdviceServiceImpl implements AdviceService {
    private AdviceRepository adviceRepository;
    private AdviserRepository adviserRepository;
    private AdvisedRepository advisedRepository;
    private EmailService emailService;

    public AdviceServiceImpl(AdviceRepository adviceRepository, AdviserRepository adviserRepository, AdvisedRepository advisedRepository, EmailService emailService) {
        this.adviceRepository = adviceRepository;
        this.adviserRepository = adviserRepository;
        this.advisedRepository = advisedRepository;
        this.emailService = emailService;
    }

    @Override
    public List<Advice> findByIdAdvised(Long idAdvised) {
        if (!adviserRepository.existsByAdvisedAndIsDone(advisedRepository.getById(idAdvised), false))
            return adviceRepository.findByIdAdvised(idAdvised);
        return new ArrayList<>();
    }

    @Override
    public Advice saveAdvice(Advice advice) {
        return adviceRepository.save(advice);
    }

    @Override
    public Advice saveAdvice(Long idAdviser, Advice advice) {
        Adviser adviser = adviserRepository.getById(idAdviser);
        if (!adviser.getIsDone()) {
            adviser.setIsDone(true);
            adviserRepository.save(adviser);
            advice.setIdAdvised(adviser.getAdvised().getId());
            advice = adviceRepository.save(advice);
            if (!adviserRepository.existsByAdvisedAndIsDone(adviser.getAdvised(), false))
                emailService.sendEmail(adviser.getAdvised());
        }
        return advice;
    }
}
