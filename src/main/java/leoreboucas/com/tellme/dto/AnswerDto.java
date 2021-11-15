package leoreboucas.com.tellme.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AnswerDto {
    @NotBlank(message = "Description is mandatory")
    private String description;
}
