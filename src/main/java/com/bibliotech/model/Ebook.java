package com.bibliotech.model;

public record Ebook(
        String isbn,
        String titulo,
        String autor,
        int anio,
        Categoria categoria,
        String urlDescarga
) implements Recurso {
    public boolean estaDisponible() { return true; }
}
