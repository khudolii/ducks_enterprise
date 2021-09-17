package com.ducks.ducks_enterprise.application;

import com.ducks.ducks_enterprise.interfaces.ducks.DuckDTO;

import java.util.List;

public interface DuckService {
    DuckDTO getDuckById(Long id);
    List<DuckDTO> getAllDucks();
    DuckDTO addNewDuck(DuckDTO duck);
    DuckDTO updateDuck(DuckDTO duckDTO);
    void removeDuck(DuckDTO duckDTO);
}
