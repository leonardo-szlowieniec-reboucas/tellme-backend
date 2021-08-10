package leoreboucas.com.tellme.controller;

import leoreboucas.com.tellme.model.Advised;
import leoreboucas.com.tellme.model.Adviser;
import leoreboucas.com.tellme.repository.AdvisedRepository;
import leoreboucas.com.tellme.service.AdvisedService;
import leoreboucas.com.tellme.service.EmailService;
import leoreboucas.com.tellme.service.impl.EmailServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/advised")
public class AdvisedController {
    private AdvisedService advisedService;
    private EmailService emailService;

    public AdvisedController (AdvisedService advisedService, EmailService emailService) {
        this.advisedService = advisedService;
        this.emailService = emailService;
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<Advised> saveAdvised(@RequestBody Advised advised) {

        //emailService.sendEmail();
        return new ResponseEntity<Advised>(advisedService.saveAdvised(advised), HttpStatus.CREATED);

    }

//    @GetMapping("/sendEmail")
//    public String sendEmail() {
//        return emailService.sendEmail();
//    }
}
