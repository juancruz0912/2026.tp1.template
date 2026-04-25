package com.bibliotech;

import com.bibliotech.model.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Inicializando BiblioTech ---");

        Recurso libro1 = new LibroFisico("111", "Java para Ingenieros", "Deitel", 2024, Categoria.TECNOLOGIA);
        Recurso ebook1 = new Ebook("222", "Pensar en Objetos", "Eckel", 2022, Categoria.TECNOLOGIA, "http://bibliotech.com/download/222");

        Socio socio1 = new Estudiante(40123456, "Juan", "Perez", "juan@um.edu.ar", "Ingeniería Informática");
        Socio socio2 = new Docente(20987654, "Carlos", "Rodriguez", "carlos@um.edu.ar", "Departamento de Tecnología");

        List<Socio> socios = List.of(socio1, socio2);
        List<Recurso> catalogo = List.of(libro1, ebook1);

        System.out.println("\nCatálogo de Libros:");
        for (Recurso r : catalogo) {
            System.out.println("- " + r.titulo() + " [" + (r instanceof Ebook ? "EBOOK" : "FÍSICO") + "] - Disponible: " + r.estaDisponible());
        }

        System.out.println("\nListado de Socios:");
        for (Socio s : socios) {
            System.out.println("- " + s.getLimiteLibros() + " libros permitidos para " + (s instanceof Estudiante ? "Estudiante" : "Docente"));
        }

        Prestamo prestamo1 = new Prestamo(1, socio1, libro1, LocalDate.now(), LocalDate.now().plusDays(7));
        System.out.println("\nNuevo Préstamo Registrado:");
        System.out.println("ID: " + prestamo1.id() + " | Socio: " + prestamo1.socio().getClass().getSimpleName());
    }
}
