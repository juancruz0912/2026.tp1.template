package com.bibliotech.exception;

public class SocioYaExisteException extends BibliotechException {
    public SocioYaExisteException() {
        super("El dni indicado es ya esta registrado a un cliente.");
    }
    public SocioYaExisteException(String mensaje) {
        super(mensaje);
    }
}
