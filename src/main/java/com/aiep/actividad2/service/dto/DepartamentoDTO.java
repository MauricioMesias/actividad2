package com.aiep.actividad2.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the {@link com.aiep.actividad2.domain.Departamento} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DepartamentoDTO implements Serializable {

    private Long id;

    @NotNull
    private String nombre;

    @NotNull
    private String ubicacion;

    @NotNull
    private BigDecimal monto;

    private JefeDTO jefe;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public JefeDTO getJefe() {
        return jefe;
    }

    public void setJefe(JefeDTO jefe) {
        this.jefe = jefe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DepartamentoDTO)) {
            return false;
        }

        DepartamentoDTO departamentoDTO = (DepartamentoDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, departamentoDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DepartamentoDTO{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", ubicacion='" + getUbicacion() + "'" +
            ", monto=" + getMonto() +
            ", jefe=" + getJefe() +
            "}";
    }
}
