package leoreboucas.com.tellme.service;

import leoreboucas.com.tellme.entity.Answer;
import leoreboucas.com.tellme.entity.Survey;
import leoreboucas.com.tellme.repository.AnswerRepository;
import leoreboucas.com.tellme.repository.RespondentRepository;
import leoreboucas.com.tellme.repository.SurveyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SurveyServiceImplTest {

    @Mock
    private SurveyRepository surveyRepository;
    @Mock
    private RespondentRepository respondentRepository;
    @Mock
    private AnswerRepository answerRepository;
    @InjectMocks
    private SurveyServiceImpl surveyService;

    @Test
    void findById() {

        Survey survey1 = new Survey();
        survey1.setId(1L);
        when(surveyRepository.findById(1L))
                .thenReturn(Optional.of(survey1));

        Survey survey = surveyService.findById(1L);

        assertEquals(1L, survey.getId());
    }

    @Test
    void findAnswersBySurveyId() {
        Survey survey = new Survey();
        survey.setId(1L);

        Answer answer = new Answer();
        List<Answer> answers = new ArrayList<>();
        answers.add(answer);

        when(surveyRepository.findById(1L))
                .thenReturn(Optional.of(survey));
        when(respondentRepository.existsBySurveyAndIsDone(survey, false)).thenReturn(false);
        when(answerRepository.findBySurveyId(1L)).thenReturn(answers);

        List<Answer> answers1 = surveyService.findAnswersBySurveyId(1L);

        assertEquals(1, answers1.size());
    }
}