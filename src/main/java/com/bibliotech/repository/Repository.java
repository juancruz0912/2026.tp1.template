package com.bibliotech.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<T, ID> {
    T guardar(T entidad);
    List<T> buscarTodos();
    void eliminar(ID id);
    Optional<T> buscarPorId(ID id);
}
