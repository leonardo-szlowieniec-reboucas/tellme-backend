package leoreboucas.com.tellme.mapper;

import leoreboucas.com.tellme.dto.AnswerDto;
import leoreboucas.com.tellme.dto.SurveyDto;
import leoreboucas.com.tellme.dto.SurveyInfoDto;
import leoreboucas.com.tellme.entity.Answer;
import leoreboucas.com.tellme.entity.Survey;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TellmeMapper {

    @Mapping(target = "title", source = "name")
    Survey surveyDtoToSurvey(SurveyDto surveyDto);

    @Mapping(source = "title", target = "name")
    SurveyInfoDto surveyToSurveyInfoDto(Survey survey);

    List<AnswerDto> answerToAnswerDto(List<Answer> answers);

    Answer answerDtoToAnswer(AnswerDto answerDto);

    @Mapping(source = "title", target = "name")
    SurveyDto surveyToSurveyDto(Survey survey);
}
