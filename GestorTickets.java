import java.util.List;

/**
 * Administra la Cola (FIFO) de Tickets de Soporte Regular.
 * Los tickets se atienden en el mismo orden en que llegaron.
 */
public class GestorTickets {
    private Cola<Ticket> cola;
    private int contador;

    public GestorTickets() {
        this.cola = new Cola<Ticket>();
        this.contador = 1;
    }

    public String registrarTicket(String usuario, String departamento, String descripcionProblema) {
        String id = "TCK-" + String.format("%04d", contador);
        contador++;
        Ticket ticket = new Ticket(id, usuario, departamento, descripcionProblema);
        cola.encolar(ticket);
        return id;
    }

    public Ticket atenderTicket() {
        return cola.desencolar();
    }

    public Ticket verSiguienteTicket() {
        return cola.verFrente();
    }

    public List<Ticket> listarTickets() {
        return cola.listarElementos();
    }

    public boolean estaVacia() {
        return cola.estaVacia();
    }

    public int totalPendientes() {
        return cola.tamano();
    }
}
