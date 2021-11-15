package leoreboucas.com.tellme.controller;

import leoreboucas.com.tellme.dto.*;
import leoreboucas.com.tellme.mapper.TellmeMapper;
import leoreboucas.com.tellme.entity.Survey;
import leoreboucas.com.tellme.service.RespondentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/respondents")
public class RespondentController {
    private final RespondentService respondentService;
    private final TellmeMapper mapper;

    @GetMapping("{respondentId}/surveys")
    public SurveyInfoDto getSurveyByRespondentId(@PathVariable Long respondentId) {

        Survey survey = respondentService.findSurveyByRespondentId(respondentId);

        return mapper.surveyToSurveyInfoDto(survey);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("{respondentId}/answers")
    public void saveAnswer(@PathVariable Long respondentId, @Valid @RequestBody AnswerDto answerDto) {

        respondentService.saveAnswer(
                respondentId,
                mapper.answerDtoToAnswer(answerDto)
        );
    }
}