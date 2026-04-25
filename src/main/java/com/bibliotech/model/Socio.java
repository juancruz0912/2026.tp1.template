package com.bibliotech.model;

public abstract class Socio {
    protected int id;
    private static int contador = 0;
    protected int DNI;
    protected String nombre;
    protected String apellido;
    protected String email;

    public Socio(int DNI, String nombre, String apellido,  String email) {
        this.id = contador++;
        this.DNI = DNI;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
    }
    public abstract int getLimiteLibros();
}
