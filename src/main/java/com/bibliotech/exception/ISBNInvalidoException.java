package com.bibliotech.exception;

public class ISBNInvalidoException extends RuntimeException {
    public ISBNInvalidoException() {
        super("Ya existe un recurso con esta ISBN");
    }
    public ISBNInvalidoException(String message) {
        super(message);
    }
}
