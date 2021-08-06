package leoreboucas.com.tellme;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StrengthController {

    @GetMapping("/strength")
    public Strength getStrength() {
        return new Strength(1L, "perseverant");
    }

    @GetMapping("/strengths")
    public List getStrengths() {
        List<Strength> strengths = new ArrayList<>();
        strengths.add(new Strength(1L, "perseverant"));
        strengths.add(new Strength(2L, "inteligent"));

        return strengths;
    }

    //
    @GetMapping("/strength/{id}/{description}")
    public Strength getStrength(@PathVariable("id") Long id, @PathVariable("description") String description) {
        return new Strength(id, description);
    }
}
