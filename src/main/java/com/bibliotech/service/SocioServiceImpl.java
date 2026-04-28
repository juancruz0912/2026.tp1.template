package com.bibliotech.service;
import com.bibliotech.model.Socio;
import com.bibliotech.repository.SocioRepository;
import java.util.List;
import java.util.Optional;

public class SocioServiceImpl implements SocioService{
    private final SocioRepository socioRepository;

    public SocioServiceImpl(SocioRepository socioRepository) {
        this.socioRepository = socioRepository; // Inyectamos el repositorio por constructor
    }

    public Socio guardar(Socio socio) {
        if (socio.getNombre() == null || "".equals(socio.getNombre())) {
            throw new IllegalArgumentException("El nombre del socio no puede ser nulo");
        }
        return socioRepository.guardar(socio);
    }

    public Optional<Socio> buscarPorId(Integer id) {
        return socioRepository.buscarPorId(id);
    }

    public List<Socio> buscarTodos() {
        return socioRepository.buscarTodos();
    }
    public void eliminar(Integer id){
        socioRepository.eliminar(id);
    }
}
