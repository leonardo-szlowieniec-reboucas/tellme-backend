package leoreboucas.com.tellme.service.impl;

import leoreboucas.com.tellme.model.Advice;
import leoreboucas.com.tellme.model.Advised;
import leoreboucas.com.tellme.model.Adviser;
import leoreboucas.com.tellme.repository.AdviceRepository;
import leoreboucas.com.tellme.repository.AdvisedRepository;
import leoreboucas.com.tellme.repository.AdviserRepository;
import leoreboucas.com.tellme.service.AdviserService;
import leoreboucas.com.tellme.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdviserServiceImpl implements AdviserService {
    @Autowired
    private AdviserRepository adviserRepository;
    @Autowired
    private AdviceRepository adviceRepository;
    @Autowired
    private AdvisedRepository advisedRepository;
    @Autowired
    private EmailService emailService;

    @Override
    public Adviser findById(Long id) {
        //TODO: add validation
        //TODO: handle exception
        //TODO: implements isPresent()
        return adviserRepository.findById(id).get();
    }

    @Transactional
    @Override
    public Advised findAdvisedByIdAdviser(Long idAdviser) {
        //TODO: add validation
        //TODO: handle exception
        //TODO: add log
        Adviser adviser = adviserRepository.getById(idAdviser);
        return advisedRepository.getById(adviser.getAdvised().getId());
    }

    @Transactional
    @Override
    public void giveAdvice(Long idAdviser, Advice advice) {
        //TODO: add validation
        //TODO: handle exception
        //TODO: add log
        Adviser adviser = adviserRepository.getById(idAdviser);
        if (!adviser.getIsDone()) {
            Advised advised = advisedRepository.getById(adviser.getAdvised().getId());
//            Advised advised = advisedRepository.getById(adviser.getIdAdvised());

            advice.setIdAdvised(advised.getId());
            adviceRepository.save(advice);

            adviser.setIsDone(true);
            adviserRepository.save(adviser);

            //Send final email to the advised if all advisers have given advice.
            if (!adviserRepository.existsByAdvisedAndIsDone(advised, false))
//                if (!adviserRepository.existsByIdAdvisedAndIsDone(advised.getId(), false))
                emailService.emailResult(advised);
        }
    }

//    @Transactional
//    @Override
//    public Advice saveAdvice(Long idAdviser, Advice advice) {
//        //TODO: add validation
//        //TODO: handle exception
//        //TODO: add log
//        Adviser adviser = adviserRepository.getById(idAdviser);
//        if (!adviser.getIsDone()) {
//            adviser.setIsDone(true);
//            adviserRepository.save(adviser);
//            advice.setIdAdvised(adviser.getAdvised().getId());
//            advice = adviceRepository.save(advice);
//            if (!adviserRepository.existsByAdvisedAndIsDone(adviser.getAdvised(), false))
//                emailService.sendEmail(adviser.getAdvised());
//        }
//        return advice;
//    }
}
