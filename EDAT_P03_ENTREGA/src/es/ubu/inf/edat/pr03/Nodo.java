package es.ubu.inf.edat.pr03;

/**
 * Clase Nodo genérica utilizada en estructuras de datos como listas enlazadas.
 *
 * @param <E> el tipo de dato que se almacenará en el nodo.
 */
public class Nodo<E> {

	/** El dato almacenado en el nodo. */
	private E dato;

	/** El siguiente nodo en la lista. */
	private Nodo<E> siguiente;

	/**
	 * Constructor que crea un nuevo nodo con el dato especificado.
	 *
	 * @param dato el dato que se almacenará en el nodo.
	 */
	public Nodo(E dato) {
		this.dato = dato;
	}

	/**
	 * Obtiene el dato almacenado en el nodo.
	 *
	 * @return el dato almacenado en el nodo.
	 */
	public E getDato() {
		return dato;
	}

	/**
	 * Establece el dato que se almacenará en el nodo.
	 *
	 * @param dato el dato que se almacenará en el nodo.
	 */
	public void setDato(E dato) {
		this.dato = dato;
	}

	/**
	 * Obtiene el siguiente nodo en la lista.
	 *
	 * @return el siguiente nodo en la lista.
	 */
	public Nodo<E> getSiguiente() {
		return siguiente;
	}

	/**
	 * Establece el siguiente nodo en la lista.
	 *
	 * @param siguiente el siguiente nodo en la lista.
	 */
	public void setSiguiente(Nodo<E> siguiente) {
		this.siguiente = siguiente;
	}

}

