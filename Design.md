# Documentación de Diseño - Sistema BiblioTech

Esta documentación detalla las decisiones arquitectónicas tomadas durante el desarrollo del núcleo del sistema BiblioTech, justificando el uso de diversas características de Java moderno y patrones de diseño.

## 2. Decisiones Técnicas y Justificaciones Arquitectónicas

### A. Interfaz `Recurso`
Se optó por una interfaz para la entidad base de los materiales de la biblioteca por dos razones:
1. **Compatibilidad con Records**: En Java, los `records` (como `Ebook`) no pueden heredar de clases. El uso de una interfaz permite que tanto clases tradicionales (`LibroFisico`) como estructuras modernas e inmutables (`Ebook`) convivan bajo un mismo tipo polimórfico.
2. **Contrato de Comportamiento**: La interfaz permite que objetos con implementaciones internas distintas cumplan con la misma promesa de disponibilidad.

### B. Interfaces de Servicio (`SocioService`, `RecursoService`, `PrestamoService`)
Se implementaron interfaces para todos los servicios por dos razones:
1. **Estandarización**: La interfaz indica a todos los servicios que **deben cumplir** con un conjunto de métodos específicos. Esto asegura que cualquier parte del programa que use un servicio sepa exactamente qué metodos puede realizar sin importar cómo se ejecuten internamente.
2. **Encapsulamiento y Seguridad**: El `Main` solo interactúa con la interfaz (el menú), lo que impide que acceda accidentalmente a la lógica interna o variables privadas de la implementación (`ServiceImpl`), protegiendo la integridad de las reglas de negocio.

### C. Interfaz Genérica `Service<T, ID>` (y `Repository`)
Se utilizó el poder de los Genéricos para estandarizar el comportamiento base del sistema:
1. **Principio DRY (Don't Repeat Yourself)**: Evita definir manualmente los métodos CRUD básicos en cada servicio.
2. **Consistencia Operativa**: Garantiza que todos los servicios y repositorios del sistema tengan los mismos metodos, pero cada uno ejecutandolo como corresponde (`guardar`, `buscarPorId`, `eliminar`).

### D. Clases Abstractas (`Socio`)
Se utilizó una clase abstracta para la jerarquía de usuarios.
- **Justificación**: A diferencia de los servicios, los socios comparten una **identidad clara (ADN común)**: todos tienen nombre, DNI y email. La clase abstracta permite reutilizar este código y atributos, mientras que el método abstracto `getLimiteLibros()` obliga a las subclases (`Estudiante`, `Docente`) a definir su comportamiento específico.

### E. Optional 
- **Optional**: Se utiliza en todos los retornos de búsqueda para eliminar el riesgo de `NullPointerException` y obligar al manejo explícito de la ausencia de datos.

### F. Enums
- **Enums**: Utilizados en `Categoria` para garantizar la seguridad de tipos, evitando errores por cadenas de texto mal escritas y estandarizando el catálogo.

## 3. Inyección de Dependencias
Todos los servicios reciben sus repositorios a través del constructor. Esto permite que el sistema sea **testeable** y **desacoplado**, ya que el servicio no sabe (ni le importa) si los datos están en una memoria RAM o en una base de datos en la nube; solo confía en el contrato definido por la interfaz del repositorio.
