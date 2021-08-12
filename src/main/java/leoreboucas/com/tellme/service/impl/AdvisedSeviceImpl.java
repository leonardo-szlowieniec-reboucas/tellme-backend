package leoreboucas.com.tellme.service.impl;

import leoreboucas.com.tellme.model.Advised;
import leoreboucas.com.tellme.model.Adviser;
import leoreboucas.com.tellme.repository.AdvisedRepository;
import leoreboucas.com.tellme.service.AdvisedService;
import leoreboucas.com.tellme.service.EmailService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdvisedSeviceImpl implements AdvisedService {
    private AdvisedRepository advisedRepository;
    private EmailService emailService;

    public AdvisedSeviceImpl(AdvisedRepository advisedRepository, EmailService emailService) {
        this.advisedRepository = advisedRepository;
        this.emailService = emailService;
    }

    @Override
    public Advised saveAdvised(Advised advised) {
        //this.sendEmail(advised.getAdvisers());
        Advised advised1 = advisedRepository.save(advised);
        this.sendEmail(advised1);

        return advised1;
    }

    @Override
    public Advised getAdvisedById(Long id) {
        Optional<Advised> advised = advisedRepository.findById(id);
        return advised.get();
    }

//    private void sendEmail(List<Adviser> advisers) {
//        for (Adviser adviser : advisers)
//            emailService.sendEmail(adviser.getEmail());
//    }

    private void sendEmail(Advised advised) {
        for (Adviser adviser : advised.getAdvisers())
            emailService.sendEmail(adviser, advised);
    }
}
