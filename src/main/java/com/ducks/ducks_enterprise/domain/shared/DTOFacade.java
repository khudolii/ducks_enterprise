package com.ducks.ducks_enterprise.domain.shared;

import java.util.List;

public interface DTOFacade<ENTITY, DTO> {
    DTO getDTO(ENTITY entity);

    ENTITY getEntity(DTO dto);

    List<DTO> getDTOList(List<ENTITY> entityList);

    List<ENTITY> getEntityList(List<DTO> dtoList);
}
