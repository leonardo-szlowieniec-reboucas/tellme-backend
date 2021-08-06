package leoreboucas.com.tellme;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TellmeController {

    //get http method
    //http://localhost:8080/tell-me

    @GetMapping("/tell-me")
    public String tellme() {
        return "Tell me!";
    }
}
