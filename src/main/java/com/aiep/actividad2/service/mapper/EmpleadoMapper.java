package com.aiep.actividad2.service.mapper;

import com.aiep.actividad2.domain.Departamento;
import com.aiep.actividad2.domain.Empleado;
import com.aiep.actividad2.service.dto.DepartamentoDTO;
import com.aiep.actividad2.service.dto.EmpleadoDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Empleado} and its DTO {@link EmpleadoDTO}.
 */
@Mapper(componentModel = "spring")
public interface EmpleadoMapper extends EntityMapper<EmpleadoDTO, Empleado> {
    @Mapping(target = "departamento", source = "departamento", qualifiedByName = "departamentoId")
    EmpleadoDTO toDto(Empleado s);

    @Named("departamentoId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DepartamentoDTO toDtoDepartamentoId(Departamento departamento);
}
