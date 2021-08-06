package leoreboucas.com.tellme.service;

import leoreboucas.com.tellme.model.Weakness;

import java.util.List;

public interface WeaknessService {
    Weakness saveWeakness(Weakness weakness);
    List<Weakness> getAllWeaknesses();
    Weakness getWeaknessById(Long id);
    Weakness updateWeakness(Weakness weakness, Long id);
    void deleteWeakness(Long id);
}
