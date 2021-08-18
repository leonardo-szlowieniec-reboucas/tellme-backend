package leoreboucas.com.tellme.controller;

import leoreboucas.com.tellme.model.Advice;
import leoreboucas.com.tellme.service.AdviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/advice")
public class AdviceController {
    @Autowired
    private AdviceService adviceService;

    @PostMapping("{idAdviser}")
    public ResponseEntity<Advice> saveAdvice(@PathVariable("idAdviser") Long idAdviser, @RequestBody Advice advice) {
        //TO DO: use dto and mapper
        return new ResponseEntity<Advice>(adviceService.saveAdvice(idAdviser, advice),HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public List<Advice> getAdvicesByIdAdvised(@PathVariable("id") Long id) {
        //TO DO: use dto and mapper
        return adviceService.findByIdAdvised(id);
    }
}