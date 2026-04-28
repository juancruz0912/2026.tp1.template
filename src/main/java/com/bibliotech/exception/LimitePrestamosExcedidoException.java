package com.bibliotech.exception;

public class LimitePrestamosExcedidoException extends BibliotechException {
    public LimitePrestamosExcedidoException() {
        super("El Socio ya ha alcanzado el limite permitido de libros");
    }
    public LimitePrestamosExcedidoException(String mensaje) {
        super(mensaje);
    }
}
