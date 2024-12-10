package com.aiep.actividad2.service.mapper;

import com.aiep.actividad2.domain.Departamento;
import com.aiep.actividad2.domain.Jefe;
import com.aiep.actividad2.service.dto.DepartamentoDTO;
import com.aiep.actividad2.service.dto.JefeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Departamento} and its DTO {@link DepartamentoDTO}.
 */
@Mapper(componentModel = "spring")
public interface DepartamentoMapper extends EntityMapper<DepartamentoDTO, Departamento> {
    @Mapping(target = "jefe", source = "jefe", qualifiedByName = "jefeId")
    DepartamentoDTO toDto(Departamento s);

    @Named("jefeId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    JefeDTO toDtoJefeId(Jefe jefe);
}
