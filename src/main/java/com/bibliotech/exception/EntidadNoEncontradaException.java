package com.bibliotech.exception;

public class EntidadNoEncontradaException extends BibliotechException {
    public EntidadNoEncontradaException() {
        super("La entidad indicada no existe");
    }
    public EntidadNoEncontradaException(String mensaje) {
        super(mensaje);
    }
}
