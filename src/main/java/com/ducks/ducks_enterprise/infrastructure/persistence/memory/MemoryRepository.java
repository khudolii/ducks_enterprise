package com.ducks.ducks_enterprise.infrastructure.persistence.memory;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public abstract class MemoryRepository<ENTITY, ID> {

    private Map<ID, ENTITY> store = new HashMap<>();

}
