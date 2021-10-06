package leoreboucas.com.tellme.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdvisedResponse {
    private String name;
    private String description;

    public AdvisedResponse(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
