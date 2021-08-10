package leoreboucas.com.tellme.service.impl;

import leoreboucas.com.tellme.model.Adviser;
import leoreboucas.com.tellme.repository.AdviserRepository;
import leoreboucas.com.tellme.service.AdviserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdviserServiceImpl implements AdviserService {

    private AdviserRepository adviserRepository;

    public AdviserServiceImpl(AdviserRepository adviserRepository) {
        this.adviserRepository = adviserRepository;
    }

    @Override
    public boolean isDone(Long idAdviser) {
        Adviser adviser = adviserRepository.findById(idAdviser).get();
        return adviser.getIsDone();
    }
}
