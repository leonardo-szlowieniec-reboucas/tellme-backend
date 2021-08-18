package leoreboucas.com.tellme.controller;

import leoreboucas.com.tellme.model.Adviser;
import leoreboucas.com.tellme.service.AdviserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/adviser")
public class AdviserController {
    @Autowired
    AdviserService adviserService;

    @GetMapping("{id}")
    public Adviser findById(@PathVariable("id") Long id) {
        //TO DO: use dto and mapper
        return adviserService.findById(id);
    }
}