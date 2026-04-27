package com.bibliotech.repository;

import com.bibliotech.model.Recurso;
import java.util.*;

public class InMemoryRecursoRepository implements RecursoRepository {
    private final Map<String, Recurso> storage = new HashMap<>();

    @Override
    public void guardar(Recurso recurso) {
        storage.put(recurso.isbn(), recurso);
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
}
