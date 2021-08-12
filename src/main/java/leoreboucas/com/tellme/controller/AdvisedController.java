package leoreboucas.com.tellme.controller;

import leoreboucas.com.tellme.model.Advised;
import leoreboucas.com.tellme.model.Adviser;
import leoreboucas.com.tellme.service.AdvisedService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/advised")
public class AdvisedController {
    private AdvisedService advisedService;

    public AdvisedController (AdvisedService advisedService) {
        this.advisedService = advisedService;
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<Advised> saveAdvised(@RequestBody Advised advised) {
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

        return new ResponseEntity<Advised>(advisedService.saveAdvised(advised), HttpStatus.CREATED);
    }
}
