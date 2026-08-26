import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de una Cola (FIFO - First In, First Out) desde cero,
 * usando nodos enlazados. El primer elemento que entra es el primero
 * en salir, como una fila de personas.
 *
 * En este proyecto se usa para los Tickets de Soporte Regular: se
 * atienden en el mismo orden en que llegaron.
 */
public class Cola<T> {
    private Nodo<T> frente;
    private Nodo<T> finalCola;
    private int tamano;

    public Cola() {
        this.frente = null;
        this.finalCola = null;
        this.tamano = 0;
    }

    /** Agrega un elemento al final de la cola. Costo O(1). */
    public void encolar(T dato) {
        Nodo<T> nuevoNodo = new Nodo<T>(dato);
        if (estaVacia()) {
            frente = nuevoNodo;
        } else {
            finalCola.siguiente = nuevoNodo;
        }
        finalCola = nuevoNodo;
        tamano++;
    }

    /** Retira y devuelve el elemento al frente de la cola. Costo O(1). */
    public T desencolar() {
        if (estaVacia()) {
            throw new RuntimeException("No se puede desencolar: la cola está vacía");
        }
        T dato = frente.dato;
        frente = frente.siguiente;
        if (frente == null) {
            finalCola = null;
        }
        tamano--;
        return dato;
    }

    /** Devuelve el elemento al frente sin retirarlo. Costo O(1). */
    public T verFrente() {
        if (estaVacia()) {
            throw new RuntimeException("La cola está vacía");
        }
        return frente.dato;
    }

    public boolean estaVacia() {
        return frente == null;
    }

    public int tamano() {
        return tamano;
    }

    /** Devuelve los elementos del frente hacia el final, sin modificar la cola. */
    public List<T> listarElementos() {
        List<T> lista = new ArrayList<T>();
        Nodo<T> actual = frente;
        while (actual != null) {
            lista.add(actual.dato);
            actual = actual.siguiente;
        }
        return lista;
    }
}
