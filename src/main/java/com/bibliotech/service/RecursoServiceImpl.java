package com.bibliotech.service;

import com.bibliotech.exception.ISBNInvalidoException;
import com.bibliotech.model.Categoria;
import com.bibliotech.model.Recurso;
import com.bibliotech.repository.RecursoRepository;
import java.util.List;
import java.util.Optional;

public class RecursoServiceImpl implements RecursoService {
    private final RecursoRepository recursoRepository;

    public RecursoServiceImpl(RecursoRepository recursoRepository) {
        this.recursoRepository = recursoRepository;
    }

    @Override
    public Recurso guardar(Recurso recurso) {
        if (!validarISBN(recurso.isbn())) {
            throw new ISBNInvalidoException();
        }

        return recursoRepository.guardar(recurso);
    }

    @Override
    public Optional<Recurso> buscarPorId(String id) {
        return recursoRepository.buscarPorId(id);
    }

    @Override
    public List<Recurso> buscarTodos() {
        return recursoRepository.buscarTodos();
    }

    @Override
    public void eliminar(String id) {
        recursoRepository.eliminar(id);
    }

    @Override
    public boolean validarISBN(String isbn) {
        return recursoRepository.buscarPorId(isbn).isEmpty();
    }

    @Override
    public List<Recurso> buscarPorTitulo(String titulo) {
        return recursoRepository.buscarPorTitulo(titulo);
    }

    @Override
    public List<Recurso> buscarPorAutor(String autor) {
        return recursoRepository.buscarPorAutor(autor);
    }

    @Override
    public List<Recurso> buscarPorCategoria(Categoria categoria) {
        return recursoRepository.buscarPorCategoria(categoria);
    }
}
