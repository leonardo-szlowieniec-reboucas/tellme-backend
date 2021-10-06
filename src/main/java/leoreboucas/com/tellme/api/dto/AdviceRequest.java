package leoreboucas.com.tellme.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdviceRequest {
    private Long idAdvised;
    private String description;
}
