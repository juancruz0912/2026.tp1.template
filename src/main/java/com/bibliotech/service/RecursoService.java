package com.bibliotech.service;

import com.bibliotech.model.Categoria;
import com.bibliotech.model.Recurso;
import java.util.List;

public interface RecursoService extends Service<Recurso, String> {
    boolean validarISBN(String isbn);
    List<Recurso> buscarPorTitulo(String titulo);
    List<Recurso> buscarPorAutor(String autor);
    List<Recurso> buscarPorCategoria(Categoria categoria);
}
