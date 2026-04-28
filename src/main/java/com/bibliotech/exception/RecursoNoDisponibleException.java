package com.bibliotech.exception;

public class RecursoNoDisponibleException extends BibliotechException {
    public RecursoNoDisponibleException() {
        super("El libro que ha solicitado no se encuentra disponible en este momento");
    }
    public RecursoNoDisponibleException(String mensaje) {
        super(mensaje);
    }
}
