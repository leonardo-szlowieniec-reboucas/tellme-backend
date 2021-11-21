package leoreboucas.com.tellme.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class RespondentMessageDto {
    private Long id;
    private String email;
}
