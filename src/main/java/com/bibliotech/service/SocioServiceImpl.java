package com.bibliotech.service;

import com.bibliotech.exception.EmailInvalidoException;
import com.bibliotech.exception.SocioYaExisteException;
import com.bibliotech.model.Socio;
import com.bibliotech.repository.SocioRepository;
import java.util.List;
import java.util.Optional;

public class SocioServiceImpl implements SocioService {
    private final SocioRepository socioRepository;

    public SocioServiceImpl(SocioRepository socioRepository) {
        this.socioRepository = socioRepository; // Inyectamos el repositorio por constructor
    }

    @Override
    public Socio guardar(Socio socio) {
        if (!validarDNi(socio.getDNI())) {
            throw new SocioYaExisteException();
        }
        if (socio.getEmail() == null || !validarEmail0(socio.getEmail())) {
            throw new EmailInvalidoException();
        }
        return socioRepository.guardar(socio);
    }

    @Override
    public Optional<Socio> buscarPorId(Integer id) {
        return socioRepository.buscarPorId(id);
    }

    @Override
    public Optional<Socio> buscarPorDNI(Integer dni) {
        return socioRepository.buscarPorDNI(dni);
    }

    @Override
    public List<Socio> buscarTodos() {
        return socioRepository.buscarTodos();
    }

    @Override
    public void eliminar(Integer id) {
        socioRepository.eliminar(id);
    }

    @Override
    public boolean validarDNi(Integer DNI) {
        return socioRepository.buscarPorDNI(DNI).isEmpty();
    }

    @Override
    public boolean validarEmail0(String email) {
        String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return email != null && email.matches(EMAIL_REGEX);
    }
}
