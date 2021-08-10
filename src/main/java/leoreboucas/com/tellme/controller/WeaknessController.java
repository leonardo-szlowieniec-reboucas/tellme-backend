package leoreboucas.com.tellme.controller;

import leoreboucas.com.tellme.model.Weakness;
import leoreboucas.com.tellme.service.WeaknessService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/weaknesses")
public class WeaknessController {
    private WeaknessService weaknessService ;

    public WeaknessController(WeaknessService weaknessService) {
        this.weaknessService = weaknessService;
    }

    @PostMapping
    public ResponseEntity<Weakness> saveWeakness(@RequestBody Weakness weakness) {
        return new ResponseEntity<Weakness>(weaknessService.saveWeakness(weakness), HttpStatus.CREATED);
    }
    @CrossOrigin
    @GetMapping
    public List<Weakness> getAllWeaknesses() {
        return weaknessService.getAllWeaknesses();
    }

    @GetMapping("{id}")
    public ResponseEntity<Weakness> getWeaknessById(@PathVariable("id") Long id) {
        return new ResponseEntity<Weakness>(weaknessService.getWeaknessById(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Weakness> updateWeakness(@PathVariable("id") Long id, @RequestBody Weakness weakness) {
        return new ResponseEntity<Weakness>(weaknessService.updateWeakness(weakness, id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteWeakness(@PathVariable("id") Long id){
        weaknessService.deleteWeakness(id);

        return new ResponseEntity<String>("Deleted.", HttpStatus.OK);
    }
}
