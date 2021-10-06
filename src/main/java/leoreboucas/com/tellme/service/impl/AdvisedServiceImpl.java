package leoreboucas.com.tellme.service.impl;

import leoreboucas.com.tellme.model.Advice;
import leoreboucas.com.tellme.model.Advised;
import leoreboucas.com.tellme.model.Adviser;
import leoreboucas.com.tellme.repository.AdviceRepository;
import leoreboucas.com.tellme.repository.AdvisedRepository;
import leoreboucas.com.tellme.repository.AdviserRepository;
import leoreboucas.com.tellme.service.AdvisedService;
import leoreboucas.com.tellme.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdvisedServiceImpl implements AdvisedService {
    @Autowired
    private AdvisedRepository advisedRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private AdviceRepository adviceRepository;
    @Autowired
    private AdviserRepository adviserRepository;

    @Transactional
    @Override
    public Advised saveAdvised(Advised advised) {
        //TODO: add validation
        //TODO: handle exception
        //TODO: add log
        advised = setAdvisedInAdviser(advised);
        advised = advisedRepository.save(advised);
        sendEmail(advised);
        return advised;
    }

//    @Transactional
//    @Override
//    public Advised saveAdvised(Advised advised) {
//        //TODO: add validation
//        //TODO: handle exception
//        //TODO: add log
//        advised = setAdvisedInAdviser(advised);
//        advised = advisedRepository.save(advised);
//        sendEmail(advised);
//        return advised;
//    }

    private void sendEmail(Advised advised) {
        for (Adviser adviser : advised.getAdvisers())
            emailService.emailAdviser(adviser, advised);
    }

    @Override
    public Advised findById(Long id) {
        //TODO: add validation
        //TODO: handle exception
        //TODO: implement isPresent()
        return advisedRepository.findById(id).get();
    }

    //To set advised in adviser for Hibernate to be able to persist this relation
    private Advised setAdvisedInAdviser(Advised advised) {
        //TODO: add validation
        //TODO: handle exception
        //TODO: add log
        Adviser adviser;
        List<Adviser> advisers = new ArrayList<>();
        for (Adviser adviser1 : advised.getAdvisers()) {
            adviser = new Adviser();
            adviser.setEmail(adviser1.getEmail());
            adviser.setAdvised(advised);
            adviser.setIsDone(false);
            advisers.add(adviser);
        }
        advised.setAdvisers(advisers);
        return advised;
    }

    @Override
    public List<Advice> findAdviceByIdAdvised(Long idAdvised) {
        //TODO: simplify not using OneToMany bidirectional

        if (!adviserRepository.existsByAdvisedAndIsDone(advisedRepository.getById(idAdvised), false))
//            if (!adviserRepository.existsByIdAdvisedAndIsDone(idAdvised, false))
            return adviceRepository.findByIdAdvised(idAdvised);
        return new ArrayList<>();
    }

    //TODO move to controller
//    @Override
//    public ResponseEntity<Advised> findById(Long id) {
//        //TODO: add validation
//        //TODO: handle exception
//        //TODO: add log
//        Optional<Advised> advised = advisedRepository.findById(id);
//        if(advised.isPresent())
//            return ResponseEntity.ok(advised.get());
//
//        return ResponseEntity.notFound().build();
//    }
}
