package com.ducks.ducks_enterprise.domain.model.duck;

import com.ducks.ducks_enterprise.domain.shared.Repository;

import java.util.List;

public interface DuckRepository extends Repository<Duck> {
    @Override
    Duck find(Long id);

    @Override
    List<Duck> findAll();

    @Override
    Duck add(Duck entity);

    @Override
    Duck update(Duck entity);

    @Override
    void remove(Duck entity);
}
