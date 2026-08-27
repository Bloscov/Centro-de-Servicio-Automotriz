import java.util.ArrayList;
import java.util.List;

/**
 * Administra la Lista de Inventario de Equipos y Licencias.
 *
 * A diferencia de la Pila y la Cola, aquí no hay un orden estricto
 * de atención: se necesita poder consultar y dar de baja cualquier
 * equipo en cualquier momento, por eso se usa una lista con acceso
 * aleatorio (ArrayList) en vez de una estructura restringida.
 */
public class GestorInventario {
    private List<Equipo> inventario;
    private int contador;

    public GestorInventario() {
        this.inventario = new ArrayList<Equipo>();
        this.contador = 1;
    }

    public String agregarEquipo(String tipoEquipo, String marca, String usuarioAsignado) {
        String numSerie = "EQ-" + String.format("%04d", contador);
        contador++;
        Equipo equipo = new Equipo(numSerie, tipoEquipo, marca, usuarioAsignado);
        inventario.add(equipo);
        return numSerie;
    }

    public Equipo buscarPorSerie(String numSerie) {
        for (Equipo eq : inventario) {
            if (eq.getNumSerie().equalsIgnoreCase(numSerie)) {
                return eq;
            }
        }
        return null;
    }

    public List<Equipo> buscarPorUsuario(String usuario) {
        List<Equipo> resultado = new ArrayList<Equipo>();
        for (Equipo eq : inventario) {
            if (eq.getUsuarioAsignado().equalsIgnoreCase(usuario)) {
                resultado.add(eq);
            }
        }
        return resultado;
    }

    /** Elimina un equipo obsoleto del inventario. Devuelve true si existía. */
    public boolean darDeBaja(String numSerie) {
        Equipo eq = buscarPorSerie(numSerie);
        if (eq == null) {
            return false;
        }
        inventario.remove(eq);
        return true;
    }

    /** Acceso aleatorio por posición (índice 0 a tamaño-1). */
    public Equipo verPorIndice(int indice) {
        if (indice < 0 || indice >= inventario.size()) {
            return null;
        }
        return inventario.get(indice);
    }

    public List<Equipo> listarTodo() {
        return inventario;
    }

    public int totalEquipos() {
        return inventario.size();
    }
}
