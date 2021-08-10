package leoreboucas.com.tellme.service;

import leoreboucas.com.tellme.model.Advised;

public interface AdvisedService {
    Advised saveAdvised(Advised advised);
    Advised getAdvisedById(Long id);
}
