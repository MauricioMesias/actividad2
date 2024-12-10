package com.aiep.actividad2.service.mapper;

import static com.aiep.actividad2.domain.JefeAsserts.*;
import static com.aiep.actividad2.domain.JefeTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JefeMapperTest {

    private JefeMapper jefeMapper;

    @BeforeEach
    void setUp() {
        jefeMapper = new JefeMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getJefeSample1();
        var actual = jefeMapper.toEntity(jefeMapper.toDto(expected));
        assertJefeAllPropertiesEquals(expected, actual);
    }
}
