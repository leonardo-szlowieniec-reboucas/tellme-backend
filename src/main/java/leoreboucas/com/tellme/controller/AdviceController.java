package leoreboucas.com.tellme.controller;

import leoreboucas.com.tellme.model.Advice;
import leoreboucas.com.tellme.model.Advised;
import leoreboucas.com.tellme.model.Weakness;
import leoreboucas.com.tellme.service.AdviceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/advice")
public class AdviceController {
    private AdviceService adviceService;

    public AdviceController(AdviceService adviceService) {
        this.adviceService = adviceService;
    }

//    @CrossOrigin
//    @PostMapping("{idAdvised}")
//    public ResponseEntity<Advice> saveAdvice(@PathVariable("idAdvised") Long idAdvised, @RequestBody Advice advice) {
//        advice.setIdAdvised(idAdvised);
//        return new ResponseEntity<Advice>(adviceService.saveAdvice(advice),HttpStatus.CREATED);
//    }

    @CrossOrigin
    @PostMapping("{idAdviser}")
    public ResponseEntity<Advice> saveAdvice(@PathVariable("idAdviser") Long idAdviser, @RequestBody Advice advice) {
        advice.setIdAdvised(idAdviser);
        return new ResponseEntity<Advice>(adviceService.saveAdvice(advice),HttpStatus.CREATED);
    }

    @CrossOrigin
    @GetMapping("{id}")
    public List<Advice> getAdvicesByIdAdvised(@PathVariable("id") Long id) {
        return adviceService.findByIdAdvised(id);
    }
}
