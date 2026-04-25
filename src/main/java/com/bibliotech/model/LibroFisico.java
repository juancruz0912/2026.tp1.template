package com.bibliotech.model;

public class LibroFisico implements Recurso {
    private final String isbn;
    private final String titulo;
    private final String autor;
    private final int anio;
    private final Categoria categoria;
    private boolean disponible;

    public LibroFisico(String isbn, String titulo, String autor,
                       int anio, Categoria categoria) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.anio = anio;
        this.categoria = categoria;
        this.disponible = true;
    }

    public String isbn()      { return isbn; }
    public String titulo()    { return titulo; }
    public String autor()     { return autor; }
    public int anio()         { return anio; }
    public Categoria categoria() { return categoria; }

    public boolean estaDisponible() { return disponible; }
    public void prestar()  { disponible = false; }
    public void devolver() { disponible = true; }
}

