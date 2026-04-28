package com.bibliotech.service;
import com.bibliotech.model.Prestamo;
import com.bibliotech.repository.PrestamoRepository;
import java.util.List;
import java.util.Optional;

public class PrestamoServiceImpl implements PrestamoService{
    private final PrestamoRepository prestamoRepository;

    public PrestamoServiceImpl(PrestamoRepository prestamoRepository) {
        this.prestamoRepository = prestamoRepository; // Inyectamos el repositorio por constructor
    }

    public Prestamo guardar(Prestamo prestamo) {
        if (prestamo.socio() == null) {
            throw new IllegalArgumentException("El prestamo debe estar registrado a un socio");
        }
        return prestamoRepository.guardar(prestamo);
    }

    public Optional<Prestamo> buscarPorId(Integer id) {
        return prestamoRepository.buscarPorId(id);
    }

    public List<Prestamo> buscarTodos() {
        return prestamoRepository.buscarTodos();
    }
    public void eliminar(Integer id){
        prestamoRepository.eliminar(id);
    }
}
