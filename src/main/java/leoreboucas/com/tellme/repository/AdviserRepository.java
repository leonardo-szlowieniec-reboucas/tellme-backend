package leoreboucas.com.tellme.repository;

import leoreboucas.com.tellme.model.Advised;
import leoreboucas.com.tellme.model.Adviser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdviserRepository extends JpaRepository<Adviser, Long> {
    Boolean existsByAdvisedAndIsDone(Advised advised, Boolean isDone);
}
