package com.ducks.ducks_enterprise.infrastructure.persistence.memory;

import com.ducks.ducks_enterprise.domain.model.duck.Duck;
import com.ducks.ducks_enterprise.domain.model.duck.DuckRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class DuckRepositoryMemory extends MemoryRepository<Duck, Long> implements DuckRepository {
    @Override
    public Duck find(Long id) {
        return getStore().get(id);
    }

    @Override
    public List<Duck> findAll() {
        return new ArrayList<>(getStore().values());
    }

    @Override
    public Duck add(Duck entity) {
        Optional.ofNullable(entity)
                .orElseThrow(IllegalArgumentException::new)
                .setDuckId(SequenceGenerator.getSequenceNumber(Duck.SEQUENCE_NAME));
        return getStore().put(entity.getDuckId(), entity);
    }

    @Override
    public Duck update(Duck entity) {
        var duckId = Optional.ofNullable(entity)
                .orElseThrow(IllegalArgumentException::new)
                .getDuckId();
        return getStore().computeIfPresent(duckId, (k, v) -> entity);
    }

    @Override
    public void remove(Duck entity) {
        var duckId = Optional.ofNullable(entity)
                .orElseThrow(IllegalArgumentException::new)
                .getDuckId();
        getStore().remove(duckId);
    }
}
