package leoreboucas.com.tellme.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class SurveyMessageDto {
    private Long id;
    private String name;
    private String email;
    private List<RespondentMessageDto> respondents;
}
