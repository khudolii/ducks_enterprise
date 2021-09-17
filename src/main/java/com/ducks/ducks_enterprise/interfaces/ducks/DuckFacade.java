package com.ducks.ducks_enterprise.interfaces.ducks;

import com.ducks.ducks_enterprise.domain.model.duck.Color;
import com.ducks.ducks_enterprise.domain.model.duck.Duck;
import com.ducks.ducks_enterprise.domain.model.duck.State;
import com.ducks.ducks_enterprise.domain.shared.DTOFacade;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DuckFacade implements DTOFacade<Duck, DuckDTO> {

    public DuckDTO getDTO(Duck duck) {
        if (Objects.nonNull(duck)) {
            return DuckDTO.builder()
                    .setDuckId(duck.getDuckId())
                    .setAge(duck.getAge())
                    .setColor(duck.getColor().name())
                    .setName(duck.getName())
                    .setWeight(duck.getWeight())
                    .setWingsLength(duck.getWingsLength())
                    .setState(duck.getState().name())
                    .build();
        } else {
            throw new IllegalArgumentException("Duck cannot be null");
        }
    }

    public Duck getEntity(DuckDTO duckDTO) {
        if (Objects.nonNull(duckDTO)) {
            return Duck.builder()
                    .setDuckId(duckDTO.getDuckId())
                    .setAge(duckDTO.getAge())
                    .setColor(Color.valueOf(duckDTO.getColor()))
                    .setName(duckDTO.getName())
                    .setWeight(duckDTO.getWeight())
                    .setWingsLength(duckDTO.getWingsLength())
                    .setState(State.valueOf(duckDTO.getState()))
                    .build();
        } else {
            throw new IllegalArgumentException("DuckDTO cannot be null");
        }
    }

    @Override
    public List<DuckDTO> getDTOList(List<Duck> ducks) {
        return CollectionUtils.isEmpty(ducks)
                ? new ArrayList<>()
                : ducks.stream().map(this::getDTO)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public List<Duck> getEntityList(List<DuckDTO> duckDTOS) {
        return CollectionUtils.isEmpty(duckDTOS)
                ? new ArrayList<>()
                : duckDTOS.stream().map(this::getEntity)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
