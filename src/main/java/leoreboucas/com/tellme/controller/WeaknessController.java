package leoreboucas.com.tellme.controller;

import leoreboucas.com.tellme.model.Test;
import leoreboucas.com.tellme.model.Weakness;
import leoreboucas.com.tellme.service.WeaknessService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
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
//        Test test = new Test();
//        test.setName("test");
//        Weakness newWeakness = new Weakness();
//        newWeakness.setDescription("trying");
//        newWeakness.setTests(Arrays.asList(test));
//        for (Test test2 : newWeakness.getTests())
//            test2.setWeakness(newWeakness);

//        Weakness weakness1 = new Weakness();
//        weakness1.setDescription("descrition hope");
       // test.setName("test hope");
//        test.setWeakness(weakness1);
//        weakness1.setTests(Arrays.asList(test));

        Test test = new Test();
//

        List<Test> list = new ArrayList<>();
        for (Test test1 : weakness.getTests()) {
            test.setName(test1.getName());
            test.setWeakness(weakness);
            list.add(test);
        }
        weakness.setTests(list);




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
