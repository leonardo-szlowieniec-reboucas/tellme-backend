package leoreboucas.com.tellme.repository;

import leoreboucas.com.tellme.model.Advice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdviceRepository extends JpaRepository<Advice, Long> {
    List<Advice> findByIdAdvised(Long idAdvised);
}
