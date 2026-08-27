/**
 * Nodo genérico utilizado para construir las estructuras enlazadas
 * de la Pila y la Cola (implementación desde cero, sin usar las
 * clases Stack/Queue que ya trae Java).
 */
public class Nodo<T> {
    T dato;
    Nodo<T> siguiente;

    public Nodo(T dato) {
        this.dato = dato;
        this.siguiente = null;
    }
}
