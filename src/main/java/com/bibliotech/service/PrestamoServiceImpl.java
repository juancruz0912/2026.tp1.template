package com.bibliotech.service;

import com.bibliotech.exception.DatosInvalidosException;
import com.bibliotech.exception.EntidadNoEncontradaException;
import com.bibliotech.exception.LimitePrestamosExcedidoException;
import com.bibliotech.exception.RecursoNoDisponibleException;
import com.bibliotech.model.LibroFisico;
import com.bibliotech.model.Prestamo;
import com.bibliotech.model.Socio;
import com.bibliotech.repository.PrestamoRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

public class PrestamoServiceImpl implements PrestamoService {
    private final PrestamoRepository prestamoRepository;

    public PrestamoServiceImpl(PrestamoRepository prestamoRepository) {
        this.prestamoRepository = prestamoRepository; // Inyectamos el repositorio por constructor
    }

    @Override
    public Prestamo guardar(Prestamo prestamo) {
        if (prestamo.socio() == null || prestamo.libro() == null) {
            throw new DatosInvalidosException("El préstamo debe tener un socio y un recurso válido.");
        }

        //verificamos disponibilidad y límite
        if (!prestamo.libro().estaDisponible()) {
            throw new RecursoNoDisponibleException("El recurso '" + prestamo.libro().titulo() + "' no está disponible.");
        }

        if (cantidadDeLibrosDelSocio(prestamo.socio()) >= prestamo.socio().getLimiteLibros()) {
            throw new LimitePrestamosExcedidoException("El socio " + prestamo.socio().getNombre() +
                    " ha alcanzado su límite de " + prestamo.socio().getLimiteLibros() + " libros.");
        }

        // 3. Marcamos el recurso como prestado (si es LibroFisico)
        if (prestamo.libro() instanceof LibroFisico libro) {
            libro.prestar();
        }

        return prestamoRepository.guardar(prestamo);
    }

    @Override
    public void registrarDevolucion(Integer prestamoId) {
        Prestamo original = prestamoRepository.buscarPorId(prestamoId)
                .orElseThrow(() -> new EntidadNoEncontradaException("Préstamo no encontrado con ID: " + prestamoId));

        if (original.fechaDevolucionEfectiva() != null) {
            throw new DatosInvalidosException("Este préstamo ya fue devuelto anteriormente.");
        }

        //Marcamos el recurso como disponible nuevamente
        if (original.libro() instanceof LibroFisico libro) {
            libro.devolver();
        }

        //Creamos la nueva versión del record con la fecha de devolución
        Prestamo devuelto = new Prestamo(
                original.id(),
                original.socio(),
                original.libro(),
                original.fechaPrestamo(),
                original.fechaDevolucionPactada(),
                LocalDate.now()
        );

        prestamoRepository.guardar(devuelto);
    }

    @Override
    public Optional<Prestamo> buscarPorId(Integer id) {
        return prestamoRepository.buscarPorId(id);
    }

    @Override
    public List<Prestamo> buscarTodos() {
        return prestamoRepository.buscarTodos();
    }

    @Override
    public void eliminar(Integer id) {
        prestamoRepository.eliminar(id);
    }

    @Override
    public int diasDeRetraso(Integer id) {
        Prestamo prestamo = prestamoRepository.buscarPorId(id)
                .orElseThrow(() -> new EntidadNoEncontradaException("Préstamo no encontrado"));

        LocalDate fechaFin;

        if(prestamo.fechaDevolucionEfectiva() != null){
            fechaFin = prestamo.fechaDevolucionEfectiva();
        } else{
            fechaFin = LocalDate.now();
        }

        if (fechaFin.isAfter(prestamo.fechaDevolucionPactada())) {
            return (int) ChronoUnit.DAYS.between(prestamo.fechaDevolucionPactada(), fechaFin);
        }
        
        return 0;
    }

    private int cantidadDeLibrosDelSocio(Socio socio) {
        // El repositorio ya filtra por fechaDevolucionEfectiva == null
        return prestamoRepository.buscarPorSocio(socio.getId()).size();
    }
}
