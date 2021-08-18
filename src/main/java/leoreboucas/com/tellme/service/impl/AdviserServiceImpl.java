package leoreboucas.com.tellme.service.impl;

import leoreboucas.com.tellme.model.Adviser;
import leoreboucas.com.tellme.repository.AdviserRepository;
import leoreboucas.com.tellme.service.AdviserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdviserServiceImpl implements AdviserService {
    @Autowired
    private AdviserRepository adviserRepository;

    @Override
    public Adviser findById(Long id) {
        //TO DO: add validation
        //TO DO: handle exception
        //TO DO: add log
        return adviserRepository.findById(id).get();
    }
}
