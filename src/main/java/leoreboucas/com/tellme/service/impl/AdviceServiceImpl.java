package leoreboucas.com.tellme.service.impl;

import leoreboucas.com.tellme.model.Advice;
import leoreboucas.com.tellme.model.Adviser;
import leoreboucas.com.tellme.repository.AdviceRepository;
import leoreboucas.com.tellme.repository.AdvisedRepository;
import leoreboucas.com.tellme.repository.AdviserRepository;
import leoreboucas.com.tellme.service.AdviceService;
import leoreboucas.com.tellme.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdviceServiceImpl implements AdviceService {
    @Autowired
    private AdviceRepository adviceRepository;
    @Autowired
    private AdviserRepository adviserRepository;
    @Autowired
    private AdvisedRepository advisedRepository;
    @Autowired
    private EmailService emailService;

    @Override
    public List<Advice> findByIdAdvised(Long idAdvised) {
        //TO DO: add validations
        //TO DO: handle exceptions
        //TO DO: add log
        if (!adviserRepository.existsByAdvisedAndIsDone(advisedRepository.getById(idAdvised), false))
            return adviceRepository.findByIdAdvised(idAdvised);
        return new ArrayList<>();
    }

    @Transactional
    @Override
    public Advice saveAdvice(Long idAdviser, Advice advice) {
        //TO DO: add validation
        //TO DO: handle exception
        //TO DO: add log
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
