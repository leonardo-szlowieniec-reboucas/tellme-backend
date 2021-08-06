package leoreboucas.com.tellme.service.impl;

import leoreboucas.com.tellme.model.Weakness;
import leoreboucas.com.tellme.repository.WeaknessRepository;
import leoreboucas.com.tellme.service.WeaknessService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WeaknessServiceImpl implements WeaknessService {
    private WeaknessRepository weaknessRepository;

    public WeaknessServiceImpl(WeaknessRepository weaknessRepository) {
        this.weaknessRepository = weaknessRepository;
    }

    @Override
    public Weakness saveWeakness(Weakness weakness) {
        return weaknessRepository.save(weakness);
    }

    @Override
    public List<Weakness> getAllWeaknesses() {
        return weaknessRepository.findAll();
    }

    @Override
    public Weakness getWeaknessById(Long id) {
        //return weaknessRepository.getById(id);
        Optional<Weakness> weakness = weaknessRepository.findById(id);
        return weakness.get();
    }

    @Override
    public Weakness updateWeakness(Weakness weakness, Long id) {
        //Why get. Need to change
        //??? Will they save all existWeakess?
        Weakness existWeakness = weaknessRepository.findById(id).get();

        existWeakness.setId(weakness.getId());
        existWeakness.setDescription(weakness.getDescription());

        return weaknessRepository.save(existWeakness);
    }

    @Override
    public void deleteWeakness(Long id) {
        weaknessRepository.deleteById(id);
    }


}
