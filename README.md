# TechSupport Enterprise — Mesa de Ayuda e Incidentes IT

Ivan Melendez Mireles
Gustavo Adrian Garcia Cantu
Alan Sifuentes Sanchez

Proyecto en Java para el Equipo 4 (Tecnología & Software). Administra
infraestructura informática usando tres estructuras de datos distintas,
cada una elegida según el tipo de información que maneja.

## Cómo compilar y ejecutar

Necesitas un JDK instalado (11 o superior). Desde la carpeta del proyecto:

```
javac *.java
java Main
```

El programa carga automáticamente algunos datos de ejemplo (2 incidentes,
2 tickets y 3 equipos) para que puedas probar los menús de inmediato.

## Estructura de las clases

| Archivo | Rol |
|---|---|
| `Nodo.java` | Nodo genérico para las listas enlazadas |
| `Pila.java` | Pila (LIFO) implementada **desde cero** con nodos enlazados |
| `Cola.java` | Cola (FIFO) implementada **desde cero** con nodos enlazados |
| `Incidente.java` | Modelo de un incidente crítico |
| `Ticket.java` | Modelo de un ticket de soporte |
| `Equipo.java` | Modelo de un equipo/licencia del inventario |
| `GestorIncidentes.java` | Usa `Pila<Incidente>` y expone las operaciones de negocio |
| `GestorTickets.java` | Usa `Cola<Ticket>` y expone las operaciones de negocio |
| `GestorInventario.java` | Usa `ArrayList<Equipo>` para permitir acceso aleatorio |
| `Main.java` | Menú de consola que conecta todo |

## Por qué cada estructura

- **Pila (LIFO) → Incidentes Críticos.** Una caída de servidor o brecha de
  seguridad exige atención inmediata, así que el incidente más reciente
  (el que está en el tope) siempre se atiende primero, sin importar qué
  tan larga esté la pila de incidentes antiguos sin resolver.

- **Cola (FIFO) → Tickets de Soporte Regular.** Son solicitudes rutinarias
  (configurar correo, cambiar contraseña, instalar software) que se
  atienden por orden de llegada — igual que una fila de atención.

- **Lista (acceso aleatorio) → Inventario de Equipos.** Aquí no hay un
  orden de "atención": se necesita poder buscar, consultar o dar de baja
  cualquier equipo en cualquier momento, por lo que un `ArrayList` (que
  permite acceso directo por índice o búsqueda) es la opción natural,
  a diferencia de la Pila y la Cola que restringen el acceso a un solo
  extremo.

## Posibles extensiones

- Guardar/leer los datos en un archivo (persistencia) para que no se
  pierdan al cerrar el programa.
- Ordenar el inventario por tipo de equipo o marca.
- Agregar una cola de prioridad si algunos tickets deben adelantarse
  a otros (por ejemplo, por urgencia del departamento).
