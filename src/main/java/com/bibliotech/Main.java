package com.bibliotech;

import com.bibliotech.exception.BibliotechException;
import com.bibliotech.model.*;
import com.bibliotech.repository.*;
import com.bibliotech.service.*;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- 📚 Inicializando BiblioTech Core System ---");

        // 1. Inicialización de Repositorios (Capa de Datos)
        RecursoRepository recursoRepo = new InMemoryRecursoRepository();
        SocioRepository socioRepo = new InMemorySocioRepository();
        PrestamoRepository prestamoRepo = new InMemoryPrestamoRepository();

        // 2. Inicialización de Servicios (Capa de Negocio - Inyección de Dependencias)
        RecursoService recursoService = new RecursoServiceImpl(recursoRepo);
        SocioService socioService = new SocioServiceImpl(socioRepo);
        PrestamoService prestamoService = new PrestamoServiceImpl(prestamoRepo);

        try {
            System.out.println("\n--- [PASO 1] Registro de Catálogo ---");
            Recurso libro1 = new LibroFisico("978-0134685991", "Effective Java", "Joshua Bloch", 2018, Categoria.TECNOLOGIA);
            Recurso libro2 = new LibroFisico("978-0132350884", "Clean Code", "Robert C. Martin", 2008, Categoria.TECNOLOGIA);
            Recurso ebook1 = new Ebook("978-0201633610", "Design Patterns", "GoF", 1994, Categoria.TECNOLOGIA, "http://downloads.biblio.tech/patterns");
            
            recursoService.guardar(libro1);
            recursoService.guardar(libro2);
            recursoService.guardar(ebook1);
            System.out.println("Catálogo cargado con éxito.");

            System.out.println("\n--- [PASO 2] Registro de Socios ---");
            Socio estudiante = new Estudiante(40123456, "Juan", "Perez", "juan@biblio.edu.ar", "Informática");
            Socio docente = new Docente(20987654, "Carlos", "Gomez", "carlos@biblio.edu.ar", "Ciencias Exactas");
            
            socioService.guardar(estudiante);
            socioService.guardar(docente);
            System.out.println("Socios registrados correctamente.");

            // Prueba de validación de Email duplicado o inválido (opcional)
            // Socio sInvalido = new Estudiante(111, "Error", "Test", "email-malo", "X");
            // socioService.guardar(sInvalido);

            System.out.println("\n--- [PASO 3] Búsquedas Avanzadas ---");
            System.out.println("Buscando por título 'Clean':");
            recursoService.buscarPorTitulo("Clean").forEach(r -> System.out.println(" > Encontrado: " + r.titulo()));
            
            System.out.println("Buscando por categoría 'TECNOLOGIA':");
            recursoService.buscarPorCategoria(Categoria.TECNOLOGIA).forEach(r -> System.out.println(" > Encontrado: " + r.titulo()));

            System.out.println("\n--- [PASO 4] Ciclo de Préstamo ---");
            // Crear préstamo para Estudiante (límite 3)
            Prestamo p1 = new Prestamo(1, estudiante, libro1, LocalDate.now(), LocalDate.now().plusDays(7), null);
            prestamoService.guardar(p1);
            System.out.println("Préstamo registrado: " + libro1.titulo() + " entregado a " + estudiante.getNombre());
            System.out.println("Disponibilidad de " + libro1.titulo() + ": " + libro1.estaDisponible());

            // Intentar prestar el mismo libro a otro socio (debería fallar)
            System.out.println("\nIntentando prestar libro no disponible...");
            try {
                Prestamo pErr = new Prestamo(2, docente, libro1, LocalDate.now(), LocalDate.now().plusDays(7), null);
                prestamoService.guardar(pErr);
            } catch (BibliotechException e) {
                System.out.println("Control de error exitoso: " + e.getMessage());
            }

            System.out.println("\n--- [PASO 5] Devolución y Retraso ---");
            // Simulamos un préstamo viejo que ya debería haber vuelto
            Prestamo pViejo = new Prestamo(3, docente, libro2, LocalDate.now().minusDays(10), LocalDate.now().minusDays(3), null);
            prestamoRepo.guardar(pViejo); // Lo guardamos directo para simular el pasado
            
            System.out.println("Días de retraso del préstamo #" + pViejo.id() + ": " + prestamoService.diasDeRetraso(pViejo.id()) + " días.");
            
            System.out.println("Registrando devolución del préstamo #1...");
            prestamoService.registrarDevolucion(1);
            System.out.println("Libro '" + libro1.titulo() + "' devuelto. Disponibilidad: " + libro1.estaDisponible());

        } catch (BibliotechException e) {
            System.err.println("❌ ERROR DE NEGOCIO: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("⚠️ ERROR INESPERADO: " + e.getMessage());
        }

        System.out.println("\n--- ✅ Fin de la simulación BiblioTech ---");
    }
}
