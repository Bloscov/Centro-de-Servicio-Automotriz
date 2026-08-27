import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

/**
 * TechSupport Enterprise
 * Sistema de Mesa de Ayuda e Incidentes IT.
 *
 * Equipo 4 - Tecnología & Software
 *
 * Estructuras utilizadas:
 *   - Pila (LIFO)  -> Incidentes Críticos del Sistema
 *   - Cola (FIFO)  -> Tickets de Soporte Regular
 *   - Lista        -> Inventario de Equipos y Licencias
 */
public class Main {

    private static Scanner sc = new Scanner(System.in);
    private static DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    private static GestorIncidentes gestorIncidentes = new GestorIncidentes();
    private static GestorTickets gestorTickets = new GestorTickets();
    private static GestorInventario gestorInventario = new GestorInventario();

    public static void main(String[] args) {
        cargarDatosDePrueba();

        boolean salir = false;
        while (!salir) {
            mostrarMenuPrincipal();
            int opcion = leerEntero("Selecciona una opción: ");
            switch (opcion) {
                case 1:
                    menuIncidentes();
                    break;
                case 2:
                    menuTickets();
                    break;
                case 3:
                    menuInventario();
                    break;
                case 0:
                    salir = true;
                    System.out.println("\n¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción inválida, intenta de nuevo.\n");
            }
        }
        sc.close();
    }

    // ==========================================================
    // MENÚ PRINCIPAL
    // ==========================================================
    private static void mostrarMenuPrincipal() {
        System.out.println("\n==============================================");
        System.out.println("        TECHSUPPORT ENTERPRISE");
        System.out.println("==============================================");
        System.out.println("1. Incidentes Críticos del Sistema   (Pila)");
        System.out.println("2. Tickets de Soporte Regular        (Cola)");
        System.out.println("3. Inventario de Equipos y Licencias (Lista)");
        System.out.println("0. Salir");
        System.out.println("----------------------------------------------");
        System.out.println("Pendientes -> Incidentes: " + gestorIncidentes.totalPendientes()
                + " | Tickets: " + gestorTickets.totalPendientes()
                + " | Equipos: " + gestorInventario.totalEquipos());
    }

    // ==========================================================
    // MÓDULO 1: INCIDENTES CRÍTICOS (PILA - LIFO)
    // ==========================================================
    private static void menuIncidentes() {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n---- INCIDENTES CRÍTICOS (Pila - LIFO) ----");
            System.out.println("1. Reportar nuevo incidente");
            System.out.println("2. Atender incidente más reciente (desapilar)");
            System.out.println("3. Ver incidente más reciente (sin atender)");
            System.out.println("4. Listar incidentes pendientes");
            System.out.println("0. Volver al menú principal");
            int opcion = leerEntero("Selecciona una opción: ");

            switch (opcion) {
                case 1:
                    reportarIncidente();
                    break;
                case 2:
                    atenderIncidente();
                    break;
                case 3:
                    verIncidenteReciente();
                    break;
                case 4:
                    listarIncidentes();
                    break;
                case 0:
                    volver = true;
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    private static void reportarIncidente() {
        System.out.print("Servidor/sistema afectado: ");
        String servidor = sc.nextLine();

        System.out.println("Severidad: 1) Crítica  2) Alta  3) Media");
        int op = leerEntero("Elige severidad: ");
        String severidad;
        if (op == 1) {
            severidad = "Crítica";
        } else if (op == 2) {
            severidad = "Alta";
        } else {
            severidad = "Media";
        }

        String hora = LocalDateTime.now().format(formatoHora);
        String id = gestorIncidentes.reportarIncidente(servidor, severidad, hora);
        System.out.println("Incidente registrado con ID: " + id + " (apilado, será el primero en atenderse)");
    }

    private static void atenderIncidente() {
        if (gestorIncidentes.estaVacia()) {
            System.out.println("No hay incidentes pendientes.");
            return;
        }
        Incidente inc = gestorIncidentes.atenderIncidente();
        System.out.println("Atendiendo (desapilado): " + inc);
    }

    private static void verIncidenteReciente() {
        if (gestorIncidentes.estaVacia()) {
            System.out.println("No hay incidentes pendientes.");
            return;
        }
        System.out.println("Tope de la pila: " + gestorIncidentes.verIncidenteReciente());
    }

    private static void listarIncidentes() {
        List<Incidente> lista = gestorIncidentes.listarIncidentes();
        if (lista.isEmpty()) {
            System.out.println("No hay incidentes pendientes.");
            return;
        }
        System.out.println("\n(Del más reciente al más antiguo)");
        int i = 1;
        for (Incidente inc : lista) {
            System.out.println(i + ". " + inc);
            i++;
        }
    }

    // ==========================================================
    // MÓDULO 2: TICKETS DE SOPORTE (COLA - FIFO)
    // ==========================================================
    private static void menuTickets() {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n---- TICKETS DE SOPORTE (Cola - FIFO) ----");
            System.out.println("1. Registrar nuevo ticket");
            System.out.println("2. Atender siguiente ticket (desencolar)");
            System.out.println("3. Ver siguiente ticket (sin atender)");
            System.out.println("4. Listar tickets en cola");
            System.out.println("0. Volver al menú principal");
            int opcion = leerEntero("Selecciona una opción: ");

            switch (opcion) {
                case 1:
                    registrarTicket();
                    break;
                case 2:
                    atenderTicket();
                    break;
                case 3:
                    verSiguienteTicket();
                    break;
                case 4:
                    listarTickets();
                    break;
                case 0:
                    volver = true;
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    private static void registrarTicket() {
        System.out.print("Nombre del usuario: ");
        String usuario = sc.nextLine();
        System.out.print("Departamento: ");
        String depto = sc.nextLine();
        System.out.print("Descripción del problema: ");
        String descripcion = sc.nextLine();

        String id = gestorTickets.registrarTicket(usuario, depto, descripcion);
        System.out.println("Ticket registrado con ID: " + id + " (encolado)");
    }

    private static void atenderTicket() {
        if (gestorTickets.estaVacia()) {
            System.out.println("No hay tickets pendientes.");
            return;
        }
        Ticket t = gestorTickets.atenderTicket();
        System.out.println("Atendiendo (desencolado): " + t);
    }

    private static void verSiguienteTicket() {
        if (gestorTickets.estaVacia()) {
            System.out.println("No hay tickets pendientes.");
            return;
        }
        System.out.println("Frente de la cola: " + gestorTickets.verSiguienteTicket());
    }

    private static void listarTickets() {
        List<Ticket> lista = gestorTickets.listarTickets();
        if (lista.isEmpty()) {
            System.out.println("No hay tickets pendientes.");
            return;
        }
        System.out.println("\n(Del primero al último en llegar)");
        int i = 1;
        for (Ticket t : lista) {
            System.out.println(i + ". " + t);
            i++;
        }
    }

    // ==========================================================
    // MÓDULO 3: INVENTARIO DE EQUIPOS (LISTA)
    // ==========================================================
    private static void menuInventario() {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n---- INVENTARIO DE EQUIPOS (Lista) ----");
            System.out.println("1. Agregar equipo");
            System.out.println("2. Buscar equipo por número de serie");
            System.out.println("3. Buscar equipos por usuario asignado");
            System.out.println("4. Dar de baja un equipo (obsoleto)");
            System.out.println("5. Ver equipo por posición (acceso aleatorio)");
            System.out.println("6. Listar todo el inventario");
            System.out.println("0. Volver al menú principal");
            int opcion = leerEntero("Selecciona una opción: ");

            switch (opcion) {
                case 1:
                    agregarEquipo();
                    break;
                case 2:
                    buscarEquipoPorSerie();
                    break;
                case 3:
                    buscarEquiposPorUsuario();
                    break;
                case 4:
                    darDeBajaEquipo();
                    break;
                case 5:
                    verEquipoPorIndice();
                    break;
                case 6:
                    listarInventario();
                    break;
                case 0:
                    volver = true;
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    private static void agregarEquipo() {
        System.out.print("Tipo de equipo (Laptop, Monitor, Licencia, etc.): ");
        String tipo = sc.nextLine();
        System.out.print("Marca: ");
        String marca = sc.nextLine();
        System.out.print("Usuario asignado: ");
        String usuario = sc.nextLine();

        String numSerie = gestorInventario.agregarEquipo(tipo, marca, usuario);
        System.out.println("Equipo agregado con número de serie: " + numSerie);
    }

    private static void buscarEquipoPorSerie() {
        System.out.print("Número de serie a buscar: ");
        String serie = sc.nextLine();
        Equipo eq = gestorInventario.buscarPorSerie(serie);
        if (eq != null) {
            System.out.println("Encontrado: " + eq);
        } else {
            System.out.println("No se encontró un equipo con ese número de serie.");
        }
    }

    private static void buscarEquiposPorUsuario() {
        System.out.print("Usuario asignado a buscar: ");
        String usuario = sc.nextLine();
        List<Equipo> resultado = gestorInventario.buscarPorUsuario(usuario);
        if (resultado.isEmpty()) {
            System.out.println("Ese usuario no tiene equipos asignados.");
            return;
        }
        for (Equipo eq : resultado) {
            System.out.println(" - " + eq);
        }
    }

    private static void darDeBajaEquipo() {
        System.out.print("Número de serie del equipo a dar de baja: ");
        String serie = sc.nextLine();
        boolean exito = gestorInventario.darDeBaja(serie);
        if (exito) {
            System.out.println("Equipo dado de baja del inventario.");
        } else {
            System.out.println("No se encontró ese equipo.");
        }
    }

    private static void verEquipoPorIndice() {
        if (gestorInventario.totalEquipos() == 0) {
            System.out.println("El inventario está vacío.");
            return;
        }
        int indice = leerEntero("Posición a consultar (1 a " + gestorInventario.totalEquipos() + "): ") - 1;
        Equipo eq = gestorInventario.verPorIndice(indice);
        if (eq != null) {
            System.out.println(eq);
        } else {
            System.out.println("Posición fuera de rango.");
        }
    }

    private static void listarInventario() {
        List<Equipo> lista = gestorInventario.listarTodo();
        if (lista.isEmpty()) {
            System.out.println("El inventario está vacío.");
            return;
        }
        int i = 1;
        for (Equipo eq : lista) {
            System.out.println(i + ". " + eq);
            i++;
        }
    }

    // ==========================================================
    // UTILIDADES
    // ==========================================================
    private static int leerEntero(String mensaje) {
        System.out.print(mensaje);
        while (!sc.hasNextInt()) {
            System.out.print("Ingresa un número válido: ");
            sc.next();
        }
        int valor = sc.nextInt();
        sc.nextLine(); // limpiar el salto de línea pendiente
        return valor;
    }

    private static void cargarDatosDePrueba() {
        gestorIncidentes.reportarIncidente("SRV-WEB-01", "Crítica",
                LocalDateTime.now().minusMinutes(30).format(formatoHora));
        gestorIncidentes.reportarIncidente("SRV-DB-02", "Alta",
                LocalDateTime.now().minusMinutes(10).format(formatoHora));

        gestorTickets.registrarTicket("Ana López", "Contabilidad", "No puede acceder a su correo");
        gestorTickets.registrarTicket("Carlos Ruiz", "Ventas", "Solicita instalación de Excel");

        gestorInventario.agregarEquipo("Laptop", "Dell", "Ana López");
        gestorInventario.agregarEquipo("Monitor", "LG", "Carlos Ruiz");
        gestorInventario.agregarEquipo("Licencia Office", "Microsoft", "Marketing");
    }
}
