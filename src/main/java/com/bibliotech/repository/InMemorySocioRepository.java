package com.bibliotech.repository;

import com.bibliotech.model.Socio;
import java.util.*;

public class InMemorySocioRepository implements SocioRepository {
    private final Map<Integer, Socio> storage = new HashMap<>();

    @Override
    public void guardar(Socio socio) {
        storage.put(socio.getId(), socio);
    }

    @Override
    public Optional<Socio> buscarPorId(Integer id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<Socio> buscarTodos() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void eliminar(Integer id) {
        storage.remove(id);
    }
}
