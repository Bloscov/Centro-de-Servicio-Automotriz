import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de una Pila (LIFO - Last In, First Out) desde cero,
 * usando nodos enlazados. El último elemento que entra es el primero
 * en salir, como una pila de platos.
 *
 * En este proyecto se usa para los Incidentes Críticos: el incidente
 * más reciente debe atenderse primero.
 */
public class Pila<T> {
    private Nodo<T> tope;
    private int tamano;

    public Pila() {
        this.tope = null;
        this.tamano = 0;
    }

    /** Agrega un elemento al tope de la pila. Costo O(1). */
    public void apilar(T dato) {
        Nodo<T> nuevoNodo = new Nodo<T>(dato);
        nuevoNodo.siguiente = tope;
        tope = nuevoNodo;
        tamano++;
    }

    /** Retira y devuelve el elemento en el tope de la pila. Costo O(1). */
    public T desapilar() {
        if (estaVacia()) {
            throw new RuntimeException("No se puede desapilar: la pila está vacía");
        }
        T dato = tope.dato;
        tope = tope.siguiente;
        tamano--;
        return dato;
    }

    /** Devuelve el elemento en el tope sin retirarlo. Costo O(1). */
    public T verTope() {
        if (estaVacia()) {
            throw new RuntimeException("La pila está vacía");
        }
        return tope.dato;
    }

    public boolean estaVacia() {
        return tope == null;
    }

    public int tamano() {
        return tamano;
    }

    /** Devuelve los elementos del tope hacia el fondo, sin modificar la pila. */
    public List<T> listarElementos() {
        List<T> lista = new ArrayList<T>();
        Nodo<T> actual = tope;
        while (actual != null) {
            lista.add(actual.dato);
            actual = actual.siguiente;
        }
        return lista;
    }
}
