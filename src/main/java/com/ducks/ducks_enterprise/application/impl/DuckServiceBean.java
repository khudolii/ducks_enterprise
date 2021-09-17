package com.ducks.ducks_enterprise.application.impl;

import com.ducks.ducks_enterprise.application.DuckService;
import com.ducks.ducks_enterprise.domain.model.duck.DuckRepository;
import com.ducks.ducks_enterprise.interfaces.ducks.DuckDTO;
import com.ducks.ducks_enterprise.interfaces.ducks.DuckFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DuckServiceBean implements DuckService {

    private final DuckRepository duckRepository;
    private final DuckFacade duckFacade;

    @Override
    public DuckDTO getDuckById(Long id) {
        return Optional.ofNullable(duckRepository.find(id))
                .map(duckFacade::getDTO)
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public List<DuckDTO> getAllDucks() {
        return duckFacade.getDTOList(duckRepository.findAll());
    }

    @Override
    public DuckDTO addNewDuck(DuckDTO duck) {
        return Optional.ofNullable(duck)
                .map(duckFacade::getEntity)
                .map(duckRepository::add)
                .map(duckFacade::getDTO)
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public DuckDTO updateDuck(DuckDTO duckDTO) {
        return Optional.ofNullable(duckDTO)
                .map(duckFacade::getEntity)
                .map(duckRepository::update)
                .map(duckFacade::getDTO)
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public void removeDuck(DuckDTO duckDTO) {
        Optional.ofNullable(duckDTO)
                .map(duckFacade::getEntity)
                .ifPresent(duckRepository::remove);
    }
}
