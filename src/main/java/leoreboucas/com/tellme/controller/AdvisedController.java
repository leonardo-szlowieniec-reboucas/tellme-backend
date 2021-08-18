package leoreboucas.com.tellme.controller;

import leoreboucas.com.tellme.model.Advised;
import leoreboucas.com.tellme.service.AdvisedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/advised")
public class AdvisedController {
    @Autowired
    private AdvisedService advisedService;

    @PostMapping
    public ResponseEntity<Advised> saveAdvised(@RequestBody Advised advised) {
        //TO DO: use dto and mapper
        return new ResponseEntity<Advised>(advisedService.saveAdvised(advised), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public Advised findById(@PathVariable("id") Long id) {
        //TO DO: use dto and mapper
        return advisedService.findById(id);
    }
}