package leoreboucas.com.tellme.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AnswerDto {
    @NotBlank(message = "Description is mandatory")
    private String description;
}
