package leoreboucas.com.tellme.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class SurveyMessageDto {
    private Long id;
    private String name;
    private String email;
    private List<RespondentMessageDto> respondents;
}
