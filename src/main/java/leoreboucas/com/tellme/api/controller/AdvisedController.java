package leoreboucas.com.tellme.api.controller;

import leoreboucas.com.tellme.api.dto.AdviceResponse;
import leoreboucas.com.tellme.api.dto.AdvisedRequest;
import leoreboucas.com.tellme.api.dto.AdvisedResponse;
import leoreboucas.com.tellme.model.Advice;
import leoreboucas.com.tellme.model.Advised;
import leoreboucas.com.tellme.service.AdvisedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("advised")
public class AdvisedController {
    @Autowired
    private AdvisedService advisedService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public AdvisedResponse saveAdvised(@RequestBody AdvisedRequest advisedRequest) {
        //TODO: use ModelMapper or another solution
        Advised advised = mapToAdvisedEntity(advisedRequest);

        return mapToAdvisedResponse(advisedService.saveAdvised(advised));
    }

    @GetMapping("{id}")
    public AdvisedResponse findById(@PathVariable Long id) {
        //TODO: use ModelMapper or another solution
        return mapToAdvisedResponse(advisedService.findById(id));
//        return advisedService.findById(id);
    }

    @GetMapping("{id}/advice")
    public List<AdviceResponse> findAdviceByIdAdvised(@PathVariable Long id) {
        //TO DO: use dto and mapper
        return mapToListAdviceResponse(advisedService.findAdviceByIdAdvised(id));
    }

    private List<AdviceResponse> mapToListAdviceResponse(List<Advice> advices) {
        List<AdviceResponse> adviceResponses = new ArrayList<>();
        for (Advice advice : advices) {
            adviceResponses.add(mapToAdviceResponse(advice));
        }
        return adviceResponses;
    }

    private AdviceResponse mapToAdviceResponse(Advice advice) {
        return new AdviceResponse(advice.getDescription());
    }

    private List<AdvisedResponse> mapToListResponse(List<Advised> adviseds) {
        List<AdvisedResponse> advisedResponses = new ArrayList<>();
        for (Advised advised : adviseds) {
            advisedResponses.add(mapToAdvisedResponse(advised));
        }
        return advisedResponses;
    }

    private AdvisedResponse mapToAdvisedResponse(Advised advised) {
        return new AdvisedResponse(advised.getName(), advised.getDescription());
    }

    private Advised mapToAdvisedEntity(AdvisedRequest advisedRequest) {
        Advised advised = new Advised();
        advised.setName(advisedRequest.getName());
        advised.setDescription(advisedRequest.getDescription());
        advised.setEmail(advisedRequest.getEmail());
        advised.setAdvisers(advisedRequest.getAdvisers());

        return advised;
    }

//    @GetMapping("{id}/advice")
//    public ResponseEntity<List<Advice>> getAdvicesByIdAdvised(@PathVariable Long id) {
//        TODO: use dto and mapper
//        List<Advice> advice = adviceService.findByIdAdvised(id);
//        if (!advice.isEmpty())
//            return ResponseEntity.ok(advice);
//
//        return ResponseEntity.notFound().build();
//    }

//    @PostMapping
//    public ResponseEntity<Advised> saveAdvised(@RequestBody Advised advised) {
//        //TODO: use dto
//        return new ResponseEntity<Advised>(advisedService.saveAdvised(advised), HttpStatus.CREATED);
//    }
}