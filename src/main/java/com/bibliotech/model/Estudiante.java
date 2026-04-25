package com.bibliotech.model;

public class Estudiante extends Socio {
    private String carrera;

    public Estudiante(int DNI, String Nombre, String Apellido, String email, String carrera) {
        super(DNI, Nombre, Apellido, email);
        this.carrera = carrera;
    }

    @Override
    public int getLimiteLibros() {
        return 3;
    }
}

