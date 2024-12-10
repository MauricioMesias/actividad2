package com.aiep.actividad2.service.mapper;

import com.aiep.actividad2.domain.Jefe;
import com.aiep.actividad2.service.dto.JefeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Jefe} and its DTO {@link JefeDTO}.
 */
@Mapper(componentModel = "spring")
public interface JefeMapper extends EntityMapper<JefeDTO, Jefe> {}
