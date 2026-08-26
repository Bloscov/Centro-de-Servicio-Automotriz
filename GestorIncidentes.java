import java.util.List;

/**
 * Administra la Pila (LIFO) de Incidentes Críticos.
 * El incidente más reciente siempre se atiende primero.
 */
public class GestorIncidentes {
    private Pila<Incidente> pila;
    private int contador;

    public GestorIncidentes() {
        this.pila = new Pila<Incidente>();
        this.contador = 1;
    }

    public String reportarIncidente(String servidorAfectado, String severidad, String horaReporte) {
        String id = "INC-" + String.format("%03d", contador);
        contador++;
        Incidente incidente = new Incidente(id, servidorAfectado, severidad, horaReporte);
        pila.apilar(incidente);
        return id;
    }

    public Incidente atenderIncidente() {
        return pila.desapilar();
    }

    public Incidente verIncidenteReciente() {
        return pila.verTope();
    }

    public List<Incidente> listarIncidentes() {
        return pila.listarElementos();
    }

    public boolean estaVacia() {
        return pila.estaVacia();
    }

    public int totalPendientes() {
        return pila.tamano();
    }
}
