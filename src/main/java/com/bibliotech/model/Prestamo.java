package com.bibliotech.model;

import java.time.LocalDate;

public record Prestamo(
        int id,
        Socio socio,
        Recurso libro,
        LocalDate fechaPrestamo,
        LocalDate fechaDevolucionPactada,
        LocalDate fechaDevolucionEfectiva
){}
