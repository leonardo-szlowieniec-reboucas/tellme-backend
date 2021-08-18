package leoreboucas.com.tellme.repository;

import leoreboucas.com.tellme.model.Advice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdviceRepository extends JpaRepository<Advice, Long> {
    List<Advice> findByIdAdvised(Long idAdvised);
}
