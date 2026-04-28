package com.bibliotech.service;
import com.bibliotech.model.Recurso;
import com.bibliotech.repository.RecursoRepository;
import java.util.List;
import java.util.Optional;

public class RecursoServiceImpl implements RecursoService {
    private final RecursoRepository recursoRepository;

    public RecursoServiceImpl(RecursoRepository recursoRepository) {
        this.recursoRepository = recursoRepository;  // Inyectamos el repositorio por constructor
    }

    public Recurso guardar(Recurso recurso) {
        // Aquí podrías agregar lógica: ej, validar que el título no sea nulo
        if (recurso.titulo() == null || recurso.titulo().isEmpty()) {
            throw new IllegalArgumentException("El nombre del recurso no puede estar vacío");
        }
        return recursoRepository.guardar(recurso);
    }

    public Optional<Recurso> buscarPorId(String isbn) {
        return recursoRepository.buscarPorId(isbn);
    }

    public List<Recurso> buscarTodos() {
        return recursoRepository.buscarTodos();
    }

    public void eliminar(String isbn) {
        recursoRepository.eliminar(isbn);
    }
}


