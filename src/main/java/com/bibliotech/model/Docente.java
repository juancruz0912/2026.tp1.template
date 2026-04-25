package com.bibliotech.model;

public class Docente extends Socio{
    private String departamento;

    public Docente(int DNI, String Nombre, String Apellido, String email, String departamento) {
        super(DNI, Nombre, Apellido, email);
        this.departamento = departamento;
    }

    @Override
    public int getLimiteLibros() {
        return 5;
    }
}
