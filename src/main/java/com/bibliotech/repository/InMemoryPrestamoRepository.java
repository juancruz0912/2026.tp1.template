package com.bibliotech.repository;

import com.bibliotech.model.Prestamo;
import java.util.*;

public class InMemoryPrestamoRepository implements PrestamoRepository {
    private final Map<Integer, Prestamo> storage = new HashMap<>();

    @Override
    public Prestamo guardar(Prestamo prestamo) {
        storage.put(prestamo.id(), prestamo);
        return prestamo;
    }

    @Override
    public Optional<Prestamo> buscarPorId(Integer id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<Prestamo> buscarTodos() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void eliminar(Integer id) {
        storage.remove(id);
    }

    @Override
    public List<Prestamo> buscarPorSocio(Integer socioId) {
        return storage.values().stream()
                .filter(p -> p.socio().getId() == socioId)
                .filter(p -> p.fechaDevolucionEfectiva() == null)
                .toList();
    }
}