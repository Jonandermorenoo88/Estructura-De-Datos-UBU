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
		long startTime = System.nanoTime(); // Inicia el temporizador para medir el tiempo de ejecuci�n

		int num = -1; // Inicializa la variable num como -1

		num = busquedaSecuencial(elemento); // Busca la posici�n del elemento con el m�todo de b�squeda secuencial
		// num = busquedaBinaria(elemento); // Si deseas usar el m�todo de b�squeda
		// binaria, comenta la l�nea anterior y descomenta esta

		if (num == -1) { // Si el elemento no se encuentra en la lista
			lista.add(elemento); // Agrega el elemento a la lista
		} else { // Si el elemento ya se encuentra en la lista
			lista.add(num, elemento); // Agrega el elemento en la posici�n correspondiente para mantener el orden
		}

		long endTime = System.nanoTime(); // Finaliza el temporizador para medir el tiempo de ejecuci�n

		long elapsedTime = endTime - startTime; // Calcula el tiempo transcurrido en nanosegundos

		// Imprime el tiempo transcurrido de ejecuci�n del m�todo add()
		System.out.println(
				"El tiempo transucurrido de add() con la b�squedaSecuencial: " + elapsedTime + " nanosegundos.");
		// Si deseas usar el m�todo de b�squeda binaria, comenta la l�nea anterior y
		// descomenta esta
		// System.out.println("El tiempo transucurrido de add() con la b�squedaBinaria:
		// " + elapsedTime + " nanosegundos.");

		return true; // Retorna true para indicar que el elemento fue agregado exitosamente
	}

	/**
	 * M�todo para agregar varios elementos a la lista
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
	 * Realiza una b�squeda secuencial en la lista para encontrar la posici�n donde
	 * deber�a insertarse el nuevo elemento.
	 * 
	 * @param dato El elemento a insertar.
	 * @return La posici�n donde deber�a insertarse el nuevo elemento o -1 si el
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
	 * Realiza una b�squeda binaria en la lista ordenada para encontrar la posici�n
	 * donde se deber�a insertar el nuevo elemento.
	 *
	 * @param dato el elemento a insertar en la lista
	 * @return la posici�n donde se deber�a insertar el nuevo elemento
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
					// Actualizar la posici�n del �ltimo elemento igual
					ultimoIgual = medio;
				}
				ini = medio + 1;
			} else {
				fin = medio - 1;
			}
		}

		// Si se encontr� un elemento igual, se devuelve la posici�n del �ltimo elemento
		// igual
		if (ultimoIgual != -1) {
			return ultimoIgual;
		}
		// Si no se encontr� un elemento igual, se devuelve la posici�n donde deber�a
		// insertarse el nuevo elemento
		return ini;
	}

	/**
	 * Compara dos elementos de la lista seg�n el comparador definido para la
	 * estructura
	 * 
	 * @param dato1 primer elemento a comparar
	 * @param dato2 segundo elemento a comparar
	 * @return 0 si son iguales, un n�mero negativo si el primer elemento es menor
	 *         que el segundo, o un n�mero positivo si el primer elemento es mayor
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
	 * Devuelve el elemento en la posici�n especificada.
	 * 
	 * @param index la posici�n del elemento que se desea obtener
	 * @return el elemento en la posici�n especificada
	 * @throws IndexOutOfBoundsException si el �ndice est� fuera de los l�mites de
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
	 * Devuelve el n�mero de elementos en la lista.
	 * 
	 * @return el tama�o de la lista
	 */
	@Override
	public int size() {
		return lista.size();
	}

}
