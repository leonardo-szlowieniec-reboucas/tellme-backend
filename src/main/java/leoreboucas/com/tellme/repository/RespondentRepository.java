package leoreboucas.com.tellme.repository;

import leoreboucas.com.tellme.entity.Survey;
import leoreboucas.com.tellme.entity.Respondent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RespondentRepository extends JpaRepository<Respondent, Long> {
    Boolean existsBySurveyAndIsDone(Survey survey, Boolean isDone);
}
