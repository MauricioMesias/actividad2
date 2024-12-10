package com.aiep.actividad2.domain;

import static com.aiep.actividad2.domain.DepartamentoTestSamples.*;
import static com.aiep.actividad2.domain.JefeTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.aiep.actividad2.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DepartamentoTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Departamento.class);
        Departamento departamento1 = getDepartamentoSample1();
        Departamento departamento2 = new Departamento();
        assertThat(departamento1).isNotEqualTo(departamento2);

        departamento2.setId(departamento1.getId());
        assertThat(departamento1).isEqualTo(departamento2);

        departamento2 = getDepartamentoSample2();
        assertThat(departamento1).isNotEqualTo(departamento2);
    }

    @Test
    void jefeTest() {
        Departamento departamento = getDepartamentoRandomSampleGenerator();
        Jefe jefeBack = getJefeRandomSampleGenerator();

        departamento.setJefe(jefeBack);
        assertThat(departamento.getJefe()).isEqualTo(jefeBack);

        departamento.jefe(null);
        assertThat(departamento.getJefe()).isNull();
    }
}
