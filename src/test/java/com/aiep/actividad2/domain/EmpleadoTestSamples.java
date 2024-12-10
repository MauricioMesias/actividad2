package com.aiep.actividad2.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class EmpleadoTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Empleado getEmpleadoSample1() {
        return new Empleado().id(1L).nombre("nombre1").apellido("apellido1").telefono("telefono1").correo("correo1");
    }

    public static Empleado getEmpleadoSample2() {
        return new Empleado().id(2L).nombre("nombre2").apellido("apellido2").telefono("telefono2").correo("correo2");
    }

    public static Empleado getEmpleadoRandomSampleGenerator() {
        return new Empleado()
            .id(longCount.incrementAndGet())
            .nombre(UUID.randomUUID().toString())
            .apellido(UUID.randomUUID().toString())
            .telefono(UUID.randomUUID().toString())
            .correo(UUID.randomUUID().toString());
    }
}
