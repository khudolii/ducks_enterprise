package com.ducks.ducks_enterprise.domain.shared;

import java.util.List;

public interface Repository<T> {
    T find(Long id);

    List<T> findAll();

    T add(T entity);

    T update(T entity);

    void remove(T entity);
}
