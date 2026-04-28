package com.bibliotech.repository;

import com.bibliotech.model.Categoria;
import com.bibliotech.model.Recurso;
import java.util.*;

public class InMemoryRecursoRepository implements RecursoRepository {
    private final Map<String, Recurso> storage = new HashMap<>();

    @Override
    public Recurso guardar(Recurso recurso) {
        storage.put(recurso.isbn(), recurso);
        return recurso;
    }

    @Override
    public Optional<Recurso> buscarPorId(String isbn) {
        return Optional.ofNullable(storage.get(isbn));
    }

    @Override
    public List<Recurso> buscarTodos() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void eliminar(String isbn) {
        storage.remove(isbn);
    }

    @Override
    public List<Recurso> buscarPorTitulo(String titulo) {
        String query = titulo.toLowerCase();
        return storage.values().stream()
                .filter(r -> r.titulo().toLowerCase().contains(query))
                .toList();
    }

    @Override
    public List<Recurso> buscarPorAutor(String autor) {
        String query = autor.toLowerCase();
        return storage.values().stream()
                .filter(r -> r.autor().toLowerCase().contains(query))
                .toList();
    }

    @Override
    public List<Recurso> buscarPorCategoria(Categoria categoria) {
        return storage.values().stream()
                .filter(r -> r.categoria() == categoria)
                .toList();
    }
}
