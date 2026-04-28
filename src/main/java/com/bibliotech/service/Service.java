package com.bibliotech.service;

import java.util.List;
import java.util.Optional;

public interface Service<T, ID> {
    T guardar(T entidad);
    Optional<T> buscarPorId(ID id);
    List<T> buscarTodos();
    void eliminar(ID id);

}
