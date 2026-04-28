package com.bibliotech.service;

import com.bibliotech.model.Prestamo;
import com.bibliotech.model.Recurso;
import com.bibliotech.model.Socio;

public interface PrestamoService extends Service<Prestamo, Integer> {
    void registrarDevolucion(Integer id);
    int diasDeRetraso(Integer id);
}
