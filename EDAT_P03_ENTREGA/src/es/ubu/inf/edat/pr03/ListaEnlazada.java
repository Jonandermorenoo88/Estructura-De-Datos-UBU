package es.ubu.inf.edat.pr03;

import java.util.AbstractList;
import java.util.Comparator;
import java.util.ListIterator;

/**
 * Clase ListaEnlazada que implementa la interfaz AbstractList de Java.
 * Representa una lista enlazada simple que contiene objetos de tipo E.
 *
 * @param <E> el tipo de objeto que se almacenará en la lista.
 */
public class ListaEnlazada<E> extends AbstractList<E> {

	/** Contador que almacena el número de elementos en la lista. */
	private int contador;

	/**
	 * Objeto Comparator que se utilizará para comparar los elementos de la lista.
	 */
	private Comparator<E> comparador;

	/** Nodo que representa el inicio de la lista. */
	private Nodo<E> inicio;

	/** Nodo que representa el final de la lista. */
	private Nodo<E> fin;
	
	
	protected class Iterador implements ListIterator<E> {
		/** Elemento actual de la iteración */
		private Nodo<E> lista;
		
		/** Último elemento visitado */
		private Nodo<E> ultimoElemento;
		
		/**
		 * Devuelve `true` si hay más elementos en la lista que aún no han sido visitados.
		 *
		 * @return `true` si hay más elementos en la lista, `false` en caso contrario.
		 */
		public boolean hasNext() {
			return false;
		}
		
		/**
		 * Devuelve el siguiente elemento de la lista y avanza el iterador hacia adelante.
		 *
		 * @return El siguiente elemento de la lista.
		 */
		public E next() {
			return null;
		}
		
		/**
		 * Devuelve `true` si hay un elemento anterior al elemento actual de la lista.
		 *
		 * @return `true` si hay un elemento anterior, `false` en caso contrario.
		 */
		@Override
		public boolean hasPrevious() {
			if(lista != null) {
				return true;
			} else {
				return false;
			} 
		}
		
		/**
		 * Devuelve el elemento anterior de la lista y retrocede el iterador hacia atrás.
		 *
		 * @return El elemento anterior de la lista.
		 */
		@Override
		public E previous() {
			E dato = null;

			Nodo<E> siguiente = ultimoElemento.getSiguiente();
			if (siguiente == null) {
				dato = ultimoElemento.getDato();
			} else {
				siguiente = ultimoElemento.getSiguiente();
			}

			return dato; 
		}
		
		/**
		 * Devuelve el índice del elemento que sería devuelto por una llamada a `next`.
		 *
		 * @return El índice del siguiente elemento de la lista.
		 */
		@Override
		public int nextIndex() {
			int count = 0;
			if(ultimoElemento == null && contador > 0) {
				return contador;
			}
			for(int i = 0; i < contador; i++)	{
				count++;
			}
			return count;
		}
		
		/**
		 * Devuelve el índice del elemento que sería devuelto por una llamada a `previous`.
		 *
		 * @return El índice del elemento anterior de la lista.
		 */
		@Override
		public int previousIndex() {
			int  count = 0;
			if(contador == 0 && ultimoElemento == null) {
				return -1;
			}
			for(int i = 0; i < contador-1; i++)	{
				count++;
			} 
			return count;
		}

		/**
		 * Elimina el elemento actual de la lista (opcional).
		 */
		@Override
		public void remove() {
			if(ultimoElemento == null) {
				return ;
			}
		}

		/**
		 * Reemplaza el último elemento visitado con el elemento especificado (opcional).
		 *
		 * @param e El elemento con el que se reemplazará el último elemento visitado.
		 */
		@Override
		public void set(E e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void add(E e) {
			// TODO Auto-generated method stub

		}

	}

	/**
	 * Agrega un elemento al final de la lista.
	 * 
	 * @param e el elemento que se va a agregar a la lista.
	 * @return true si el elemento se agregó correctamente, false en caso contrario.
	 */
	public boolean add(E e) {
	    boolean comprobar = false; // Se inicializa la variable booleana "comprobar" en "false".
	    Nodo<E> nuevo = new Nodo<E>(e); // Se crea un nuevo nodo que contiene el elemento a agregar.
	    if (contador == 0) { // Si la lista está vacía (contador == 0)...
	        inicio = fin = nuevo; // ...el nuevo nodo se establece como el inicio y el fin de la lista...
	        comprobar = true; // ...y se establece la variable "comprobar" en "true".
	    }
	    else { // Si la lista no está vacía...
	        fin.setSiguiente(nuevo); // ...el siguiente del último nodo de la lista se establece como el nuevo nodo...
	        fin = nuevo; // ...y el nuevo nodo se establece como el último nodo de la lista...
	        comprobar = true; // ...y se establece la variable "comprobar" en "true".
	    }
	    contador++; // Se incrementa el contador de elementos de la lista.
	    return comprobar; // Se devuelve el valor de la variable "comprobar".
	}
	
	/**
	 * Elimina el elemento en el índice especificado de esta lista. 
	 * Devuelve el elemento eliminado de la lista, si lo encuentra.
	 *
	 * @param index el índice del elemento a eliminar
	 * @return el elemento eliminado de la lista, o null si la lista está vacía o el índice está fuera del rango
	 * @throws IndexOutOfBoundsException si el índice es negativo o mayor o igual que el tamaño de la lista
	 */
	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
	    E dato = null; // Valor del elemento eliminado
	    if (contador == 0) {
	        return null; // La lista está vacía, no hay nada que eliminar
	    } else if (contador == 1) {
	        // La lista tiene un solo elemento, eliminamos ese elemento actualizando el inicio de la lista
	        contador--;
	        dato = inicio.getDato();
	        inicio = inicio.getSiguiente();
	        return dato;
	    } else if (index == 0) {
	        // El índice es cero, eliminamos el primer elemento de la lista actualizando el inicio de la lista
	        dato = inicio.getDato();
	        inicio = inicio.getSiguiente();
	        contador--;
	        return dato;
	    } else {
	        // Recorremos la lista hasta el elemento correspondiente al índice especificado
	        Nodo<E> anterior = inicio;
	        Nodo<E> actual = inicio.getSiguiente();
	        for (int i = 1; i < size(); i++) {
	            if (i == index) {
	                // Encontramos el elemento correspondiente al índice, actualizamos la lista
	                dato = actual.getDato();
	                anterior.setSiguiente(actual.getSiguiente());
	                contador--;
	                if (fin == actual) {
	                    fin = anterior;
	                }
	                return dato;
	            } else {
	                anterior = actual;
	                actual = actual.getSiguiente();
	            }
	        }
	    }
	    return null; // El índice está fuera del rango, no hay nada que eliminar
	}


	/**
	 * Devuelve el elemento en el índice especificado de esta lista.
	 *
	 * @param index el índice del elemento a devolver
	 * @return el elemento en el índice especificado de la lista, o null si la lista está vacía o el índice está fuera del rango
	 * @throws IndexOutOfBoundsException si el índice es negativo o mayor o igual que el tamaño de la lista
	 */
	public E get(int index) throws IndexOutOfBoundsException {
	    Nodo<E> actual = inicio; // Nodo actual en la iteración
	    E dato = null; // Valor del elemento encontrado
	    for (int i = 0; i < contador; i++) {
	        if (i == index) {
	            // Encontramos el elemento correspondiente al índice, devolvemos su valor
	            dato = actual.getDato();
	            break;
	        } else {
	            actual = actual.getSiguiente();
	        }
	    }
	    return dato; // Devolvemos el valor del elemento encontrado, o null si el índice está fuera del rango
	}


	/**
	 * Devuelve el número de elementos en esta lista.
	 *
	 * @return el número de elementos en esta lista
	 */
	public int size() {
	    int count = 0; // contador inicializado en 0
	    Nodo<E> actual = inicio; // nodo actual se inicializa con el primer nodo de la lista

	    while (actual != null) { // mientras existan nodos en la lista
	        count++; // aumentar el contador
	        actual = actual.getSiguiente(); // avanzar al siguiente nodo
	    }

	    return count; // devuelve el contador final
	}


	/**
	 * Elimina todos los elementos de esta lista.
	 */
	public void clear() {
	    this.contador = 0; // Reiniciar el contador de elementos a 0
	    this.inicio = null; // Establecer el primer nodo de la lista a null
	    this.fin = null; // Establecer el último nodo de la lista a null
	    return; // Salir del método
	}


	/**
	 * Compara dos elementos y devuelve un valor entero que indica su orden relativo.
	 *
	 * @param dato1 el primer elemento a comparar
	 * @param dato2 el segundo elemento a comparar
	 * @return un valor entero negativo, cero o positivo si el primer elemento es menor, igual o mayor que el segundo elemento, respectivamente
	 * @throws ClassCastException si el primer elemento no es comparable con el segundo elemento y no se proporcionó un comparador externo
	 */
	private int comparar(E dato1, E dato2) throws ClassCastException {
	    if (comparador == null) {
	        // Si no se proporcionó un comparador externo, utilizar el método compareTo de la interfaz Comparable
	        return ((Comparable<E>) dato1).compareTo(dato2);
	    } else {
	        // Si se proporcionó un comparador externo, utilizar su método compare
	        return comparador.compare(dato1, dato2);
	    }
	}
}
