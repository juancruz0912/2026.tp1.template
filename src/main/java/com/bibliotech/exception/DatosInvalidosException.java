package com.bibliotech.exception;

public class DatosInvalidosException extends RuntimeException {
    public DatosInvalidosException() {
        super("Los datos ingresados no son validos");
    }
    public DatosInvalidosException(String message) {
        super(message);
    }
}
