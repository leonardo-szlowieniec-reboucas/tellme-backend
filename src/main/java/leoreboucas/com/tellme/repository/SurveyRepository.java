package leoreboucas.com.tellme.repository;

import leoreboucas.com.tellme.entity.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyRepository extends JpaRepository<Survey, Long> {
}
