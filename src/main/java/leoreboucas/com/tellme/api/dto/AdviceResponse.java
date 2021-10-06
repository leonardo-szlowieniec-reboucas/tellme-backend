package leoreboucas.com.tellme.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdviceResponse {
    private String description;

    public AdviceResponse (String description) {
        this.description = description;
    }
}
