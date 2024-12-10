package com.aiep.actividad2.service.mapper;

import static com.aiep.actividad2.domain.DepartamentoAsserts.*;
import static com.aiep.actividad2.domain.DepartamentoTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DepartamentoMapperTest {

    private DepartamentoMapper departamentoMapper;

    @BeforeEach
    void setUp() {
        departamentoMapper = new DepartamentoMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getDepartamentoSample1();
        var actual = departamentoMapper.toEntity(departamentoMapper.toDto(expected));
        assertDepartamentoAllPropertiesEquals(expected, actual);
    }
}
