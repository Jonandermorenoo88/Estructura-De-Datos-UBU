package es.ubu.gii.edat.p02;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ListaOrdenada<E> extends AbstractList<E> {
	// Atributos//1-comparador//2-lista
	// constructor//1-sin comparador//2-con comparador
	// public boolean add(E elemento) --> usar//busqueda secuencial o busqueda
	// binaria
	// public boolean addAll(ArrayList lista)
	// private int busqueda(E dato)

	// atributos de comparador
	private Comparator<E> comparador;
	// atributos de lista, donde se va almacenar los datos
	private ArrayList<E> lista;

	// contructor con comparador
	public ListaOrdenada() {
		super();
		lista = new ArrayList<E>();
		comparador = null;
	}

	// contructor sin comparador
	public ListaOrdenada(Comparator<E> comparador) {
		super();
		lista = new ArrayList<E>();
		this.comparador = comparador;
	}

	@Override
	public boolean add(E elemento) {
		long startTime = System.nanoTime(); // Inicia el temporizador para medir el tiempo de ejecución

		int num = -1; // Inicializa la variable num como -1

		num = busquedaSecuencial(elemento); // Busca la posición del elemento con el método de búsqueda secuencial
		// num = busquedaBinaria(elemento); // Si deseas usar el método de búsqueda
		// binaria, comenta la línea anterior y descomenta esta

		if (num == -1) { // Si el elemento no se encuentra en la lista
			lista.add(elemento); // Agrega el elemento a la lista
		} else { // Si el elemento ya se encuentra en la lista
			lista.add(num, elemento); // Agrega el elemento en la posición correspondiente para mantener el orden
		}

		long endTime = System.nanoTime(); // Finaliza el temporizador para medir el tiempo de ejecución

		long elapsedTime = endTime - startTime; // Calcula el tiempo transcurrido en nanosegundos

		// Imprime el tiempo transcurrido de ejecución del método add()
		System.out.println(
				"El tiempo transucurrido de add() con la búsquedaSecuencial: " + elapsedTime + " nanosegundos.");
		// Si deseas usar el método de búsqueda binaria, comenta la línea anterior y
		// descomenta esta
		// System.out.println("El tiempo transucurrido de add() con la búsquedaBinaria:
		// " + elapsedTime + " nanosegundos.");

		return true; // Retorna true para indicar que el elemento fue agregado exitosamente
	}

	/**
	 * Método para agregar varios elementos a la lista
	 * 
	 * @param elementos lista de elementos que se desean agregar
	 * 
	 * @return true si todos los elementos se agregaron exitosamente
	 */
	public boolean addAll(ArrayList<E> elementos) {

		for (E elemento : elementos) {
			add(elemento);
		}
		return true;
	}

	/**
	 * Realiza una búsqueda secuencial en la lista para encontrar la posición donde
	 * debería insertarse el nuevo elemento.
	 * 
	 * @param dato El elemento a insertar.
	 * @return La posición donde debería insertarse el nuevo elemento o -1 si el
	 *         elemento ya se encuentra en la lista.
	 */
	private int busquedaSecuencial(E dato) {
		int com;

		for (int i = 0; i < lista.size(); i++) {
			com = comparar(dato, lista.get(i));
			if (com <= 0) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Realiza una búsqueda binaria en la lista ordenada para encontrar la posición
	 * donde se debería insertar el nuevo elemento.
	 *
	 * @param dato el elemento a insertar en la lista
	 * @return la posición donde se debería insertar el nuevo elemento
	 */
	private int busquedaBinaria(E dato) {
		int ini = 0;
		int fin = lista.size() - 1;
		int ultimoIgual = -1;

		while (ini <= fin) {
			int medio = (ini + fin) / 2;

			int comparacion = comparar(lista.get(medio), dato);

			if (comparacion <= 0) {
				if (comparacion == 0) {
					// Actualizar la posición del último elemento igual
					ultimoIgual = medio;
				}
				ini = medio + 1;
			} else {
				fin = medio - 1;
			}
		}

		// Si se encontró un elemento igual, se devuelve la posición del último elemento
		// igual
		if (ultimoIgual != -1) {
			return ultimoIgual;
		}
		// Si no se encontró un elemento igual, se devuelve la posición donde debería
		// insertarse el nuevo elemento
		return ini;
	}

	/**
	 * Compara dos elementos de la lista según el comparador definido para la
	 * estructura
	 * 
	 * @param dato1 primer elemento a comparar
	 * @param dato2 segundo elemento a comparar
	 * @return 0 si son iguales, un número negativo si el primer elemento es menor
	 *         que el segundo, o un número positivo si el primer elemento es mayor
	 *         que el segundo
	 */
	private int comparar(E dato1, E dato2) {
		if (comparador == null) {
			// Comparamos con el compareTo de Comparable
			if (dato1 instanceof Comparable && dato2 instanceof Comparable)
				return ((Comparable<? super E>) dato1).compareTo(dato2);
		} else {
			// Comparamos con el atributo "comparador"
			return comparador.compare(dato1, dato2);
		}
		return 0;
	}

	/**
	 * Devuelve el elemento en la posición especificada.
	 * 
	 * @param index la posición del elemento que se desea obtener
	 * @return el elemento en la posición especificada
	 * @throws IndexOutOfBoundsException si el índice está fuera de los límites de
	 *                                   la lista
	 */
	@Override
	public E get(int index) {
		if (index < 0 || index >= lista.size()) {
			throw new IndexOutOfBoundsException();
		}
		return lista.get(index);
	}

	/**
	 * Devuelve el número de elementos en la lista.
	 * 
	 * @return el tamaño de la lista
	 */
	@Override
	public int size() {
		return lista.size();
	}

}
