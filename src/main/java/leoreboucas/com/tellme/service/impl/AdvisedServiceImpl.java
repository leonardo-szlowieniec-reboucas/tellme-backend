package leoreboucas.com.tellme.service.impl;

import leoreboucas.com.tellme.model.Advised;
import leoreboucas.com.tellme.model.Adviser;
import leoreboucas.com.tellme.repository.AdvisedRepository;
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

    @Transactional
    @Override
    public Advised saveAdvised(Advised advised) {
        //TO DO: add validation
        //TO DO: handle exception
        //TO DO: add log
        advised = setAdvisedInAdviser(advised);
        advised = advisedRepository.save(advised);
        sendEmail(advised);
        return advised;
    }

    private void sendEmail(Advised advised) {
        for (Adviser adviser : advised.getAdvisers())
            emailService.sendEmail(adviser, advised);
    }

    @Override
    public Advised findById(Long id) {
        //TO DO: add validation
        //TO DO: handle exception
        //TO DO: add log
        return advisedRepository.findById(id).get();
    }

    //To set advised in adviser for Hibernate to be able to persist this relation
    private Advised setAdvisedInAdviser(Advised advised) {
        //TO DO: add validation
        //TO DO: handle exception
        //TO DO: add log
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
}
