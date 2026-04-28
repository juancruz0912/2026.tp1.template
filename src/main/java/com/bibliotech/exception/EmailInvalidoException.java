package com.bibliotech.exception;

public class EmailInvalidoException extends BibliotechException {
    public EmailInvalidoException() {
        super("El email indicado es inválido.");
    }
    public EmailInvalidoException(String mensaje) {
        super(mensaje);
    }
}
