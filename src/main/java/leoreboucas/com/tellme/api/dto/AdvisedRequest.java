package leoreboucas.com.tellme.api.dto;

import leoreboucas.com.tellme.model.Adviser;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AdvisedRequest {
    private String name;
    private String description;
    private String email;
    private List<Adviser> advisers;

    public AdvisedRequest(String name, String description, String email, List<Adviser> advisers) {
        this.name = name;
        this.description = description;
        this.email = email;
        this.advisers = advisers;
    }
}
