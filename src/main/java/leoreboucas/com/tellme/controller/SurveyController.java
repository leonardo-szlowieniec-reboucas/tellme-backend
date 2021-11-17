package leoreboucas.com.tellme.controller;

import leoreboucas.com.tellme.dto.*;
import leoreboucas.com.tellme.mapper.TellmeMapper;
import leoreboucas.com.tellme.entity.Survey;
import leoreboucas.com.tellme.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/surveys")
public class SurveyController {

    private final SurveyService surveyService;
    private final TellmeMapper mapper;

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping
    public void createSurvey(@Valid @RequestBody SurveyDto surveyDto) {

        surveyService.saveSurvey(
                mapper.surveyDtoToSurvey(surveyDto));
    }

    @GetMapping("{id}")
    public SurveyInfoDto getSurveyInfoById(@PathVariable Long id) {

        return mapper.surveyToSurveyInfoDto(
                surveyService.findById(id));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/{surveyId}/respondents/{respondentId}/answers")
    public void saveAnswer(
            @PathVariable Long surveyId,
            @PathVariable Long respondentId,
            @Valid @RequestBody AnswerDto answerDto) {

        surveyService.saveAnswer(
                surveyId,
                respondentId,
                mapper.answerDtoToAnswer(answerDto));
    }

    @GetMapping("{id}/answers")
    public List<AnswerDto> getAnswersBySurveyId(@PathVariable Long id) {

        return mapper.answerToAnswerDto(
                surveyService.findAnswersBySurveyId(id));
    }
}