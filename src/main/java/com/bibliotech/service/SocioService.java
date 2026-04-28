package com.bibliotech.service;
import com.bibliotech.model.Socio;

import java.util.Optional;


public interface SocioService extends Service<Socio,  Integer> {
    Optional<Socio> buscarPorDNI(Integer dni);
    boolean validarDNi(Integer id);
    boolean validarEmail0(String email);
}
