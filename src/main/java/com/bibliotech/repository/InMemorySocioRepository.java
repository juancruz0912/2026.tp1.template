package com.bibliotech.repository;

import com.bibliotech.model.Socio;
import java.util.*;

public class InMemorySocioRepository implements SocioRepository {
    private final Map<Integer, Socio> storage = new HashMap<>();

    @Override
    public Socio guardar(Socio socio) {
        storage.put(socio.getId(), socio);
        return socio;
    }

    @Override
    public Optional<Socio> buscarPorId(Integer id) {
        return Optional.ofNullable(storage.get(id));
    }
    @Override
    public Optional<Socio> buscarPorDNI(Integer dni) {
        return storage.values().stream()
                .filter(s -> s.getDNI() == dni)
                .findFirst();
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
