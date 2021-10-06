package leoreboucas.com.tellme.api.controller;

import leoreboucas.com.tellme.api.dto.AdviceRequest;
import leoreboucas.com.tellme.api.dto.AdviceResponse;
import leoreboucas.com.tellme.api.dto.AdvisedResponse;
import leoreboucas.com.tellme.model.Advice;
import leoreboucas.com.tellme.model.Advised;
import leoreboucas.com.tellme.model.Adviser;
import leoreboucas.com.tellme.service.AdviserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("adviser")
public class AdviserController {
    @Autowired
    AdviserService adviserService;

    @GetMapping("{idAdviser}/advised")
    public AdvisedResponse findAdvisedByIdAdviser(@PathVariable Long idAdviser) {
        Advised advised = adviserService.findAdvisedByIdAdviser(idAdviser);

        return mapToAdvisedResponse(advised);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("{idAdviser}/advice")
    public void giveAdvice(@PathVariable Long idAdviser, @RequestBody AdviceRequest adviceRequest) {
        adviserService.giveAdvice(idAdviser, mapToAdviceEntity(adviceRequest));
    }

//    @ResponseStatus(HttpStatus.CREATED)
//    @PostMapping("{idAdviser}/advice")
//    public AdviceResponse giveAdvice(@PathVariable Long idAdviser, @RequestBody AdviceRequest adviceRequest) {
//
//        return mapToAdviceResponse(adviserService.giveAdvice(idAdviser, mapToAdviceEntity(adviceRequest)));
//    }

    private AdvisedResponse mapToAdvisedResponse(Advised advised) {

        return new AdvisedResponse(advised.getName(), advised.getDescription());
    }

    private AdviceResponse mapToAdviceResponse(Advice advice) {
        return new AdviceResponse(advice.getDescription());
    }

    private Advice mapToAdviceEntity(AdviceRequest adviceRequest) {
        Advice advice = new Advice();
        advice.setIdAdvised(adviceRequest.getIdAdvised());
        advice.setDescription(adviceRequest.getDescription());
        return advice;
    }


//    @PostMapping("{idAdviser}/advice")
//    public ResponseEntity<Advice> saveAdvice(@PathVariable Long idAdviser, @RequestBody Advice advice) {
//        //TO DO: use dto and mapper
////        return new ResponseEntity<Advice>(adviceService.saveAdvice(idAdviser, advice),HttpStatus.CREATED); //My original solution
////        return new ResponseEntity(adviceService.saveAdvice(idAdviser, advice),HttpStatus.CREATED);
//        return ResponseEntity.ok(adviserService.saveAdvice(idAdviser, advice));
//    }
}