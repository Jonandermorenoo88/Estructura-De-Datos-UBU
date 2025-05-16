package es.ubu.inf.edat.pr09;


import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import es.ubu.inf.edat.pr09.ArbolBB.Nodo;

/**
 * Clase que implementa el interfaz java.util.Set empleando
 * para ello la estructura de un árbol binario de búsqueda 
 * 
 * @author Adrián Ortega Moradillo y Jon Ander Incera Moreno
 *
 * @param <E> Parametro de genericiadad de la estructura
 */
public class ArbolBB<E> extends AbstractSet<E> {

	/**
	 * 
	 * @author Adrián Ortega Moradillo y Jon Ander Incera Moreno
	 *
	 */
	protected class Nodo {      
		/**
		 * Dato incluido en el nodo
		 */
		private E dato;
		/**
		 * Subarbol izquierdo del nodo
		 */
		private Nodo izq;
		/**
		 * Subarbol derecho del nodo
		 */
		private Nodo der;

		/**
		 * Constructor
		 * 
		 * @param Object dato El dato que contendra el nodo   
		 */
		public Nodo (E dato) {
			this.dato = dato;
		}

		/**
		 * Devuelve el objeto que hay en el nodo
		 * 
		 * @return Object El objeto que hay en el nodo   
		 */
		public E getDato(){
			return dato;
		}

		/**
		 * Establece el elemento de un nodo.
		 * 
		 * @param dato El nuevo elemento del Nodo
		 * 	 
		 */
		public void setDato(E dato) {
			this.dato = dato;
		}
		/**
		 * Devuelve el subarbol izquierdo de un nodo
		 *
		 * @return Nodo La raiz del subarbol izquierdo del nodo
		 */
		public Nodo getIzq(){
			return izq;
		}

		/**
		 * Establece el subarbol izquierdo de un nodo
		 * 
		 * @param Nodo El nuevo subarbol izquierdo de ese nodo
		 */
		public void setIzq(Nodo valor) {
			izq = valor;
		}
		/**
		 * Devuelve el subarbol derecho de un nodo
		 * 
		 * @return Nodo La raiz del subarbol derecho del nodo
		 */
		public Nodo getDer(){
			return der;
		}

		/**
		 * Establece el subarbol derecho de un nodo
		 * 
		 * @param Nodo El nuevo subarbol derecho de ese nodo
		 * 	 
		 */
		public void setDer(Nodo valor){
			der = valor;
		}

		public String toString(){
			return this.getDato().toString();
		}

	} // Nodo

	/** 
	 * La raiz del arbol
	 */
	protected Nodo raiz = null; // Un arbol está definido por una referencia a un nodo

	/**
	 * Número de elementos incluidos en el Set
	 */
	protected int numElementos = 0;

	/**
	 * Referencia al comparador empleado para organizar los elementos en
	 * el arbol binario de busqueda
	 */
	protected Comparator<? super E> comparator;

	/**
	 * Constructor por defecto
	 */
	public ArbolBB(){ }

	/** 
	 * Constructor que incluye una coleccion para inicializar el Set
	 * 
	 * @param coleccion que se incluye en el set en el momento de su instanciacion
	 * No se incluiran los elementos duplicados
	 */
	public ArbolBB(Collection<? extends E> coleccion){
		this.addAll(coleccion);
	}

	public ArbolBB(Comparator<? super E> c){
		this.comparator = c;
	}
	
	/** 
	 * Constructor que incluye una coleccion para inicializar el Set
	 * 
	 * @param coleccion que se incluye en el set en el momento de su instanciacion
	 * No se incluiran los elementos duplicados
	 * @param c comparador empleado para determinar el orden de los elementos en el Set
	 */
	public ArbolBB(Collection<? extends E> coleccion, Comparator<? super E> c){
		this.comparator = c;
		this.addAll(coleccion);
	}
	
	/**
	 * Agrega un elemento al conjunto.
	 * 
	 * @param dato el elemento a agregar.
	 * @return true si el elemento se agregó correctamente, false si el elemento ya está presente.
	 */
	@Override
	public boolean add(E dato) {
	    // Buscar el nodo donde se insertará el elemento
	    List<Nodo> encontrado = this.buscar(this.raiz, dato);

	    if (encontrado.get(0) != null) // Si el elemento ya está presente
	        return false;

	    this.insertar(encontrado.get(1), dato); // Insertar el elemento en el nodo encontrado
	    // Aumentar el número de elementos
	    numElementos++;

	    return true;
	}


	/**
	 * Elimina un elemento del conjunto.
	 *
	 * @param o el elemento a eliminar.
	 * @return true si se eliminó correctamente, false si el elemento no está presente.
	 */
	@Override
	public boolean remove(Object o) {
	    E borrado;
	    try {
	        borrado = (E) o; // Realizar un casting seguro a tipo E
	    } catch (ClassCastException cce) {
	        return false; // Si el objeto no se puede convertir a tipo E, devuelve false
	    }

	    List<Nodo> encontrado = this.buscar(this.raiz, borrado); // Buscar el nodo donde se encuentra el elemento a eliminar

	    if (encontrado.get(0) == null) // Si el elemento no está presente
	        return false;

	    eliminar(encontrado.get(0), encontrado.get(1), borrado); // Eliminar el elemento del nodo encontrado
	    numElementos--;

	    return true;
	}


	/**
	 * Devuelve un iterador sobre los elementos del conjunto.
	 *
	 * @return un iterador sobre los elementos del conjunto.
	 */
	@Override
	public Iterator<E> iterator() {
	    List<E> inorden = new ArrayList<E>(this.size()); // Crear una lista para almacenar los elementos en orden
	    this.inOrdenRecursivo(raiz, inorden); // Realizar un recorrido inorden del árbol y almacenar los elementos en la lista
	    return inorden.iterator(); // Devolver un iterador de la lista
	}

	/**
	 * Devuelve el número de elementos en el conjunto.
	 *
	 * @return el número de elementos en el conjunto.
	 */
	@Override
	public int size() {
	    return this.numElementos; // Devolver el número de elementos almacenados en la variable numElementos
	}

	/**
	 * Elimina todos los elementos del conjunto, dejándolo vacío.
	 */
	@Override
	public void clear() {
	    this.raiz = null; // Establecer la raíz del árbol como nula
	    this.numElementos = 0; // Establecer el número de elementos como cero
	}


	//////////////// MÉTODOS AUXILIARES ////////////////	
	/**
	 * Compara dos elementos para determinar su orden.
	 *
	 * @param o1 el primer elemento a comparar.
	 * @param o2 el segundo elemento a comparar.
	 * @return un valor negativo si o1 es menor que o2, cero si son iguales, o un valor positivo si o1 es mayor que o2.
	 * @throws ClassCastException si los elementos no son comparables y no se proporcionó un comparador.
	 */
	@SuppressWarnings("unchecked")
	protected int comparar(E o1, E o2) throws ClassCastException {
	    if (comparator != null) { // Si hay un comparador, se emplea
	        return comparator.compare(o1, o2);
	    } else { // Si no, hay que suponer que sean Comparable
	        return ((Comparable<E>) o1).compareTo(o2);
	    }
	}


	/**
	 * Metodo que permite obtener la referencia del nodo que contiene un dato
	 * que se solicita localizar en el árbol. 
	 * Permite facilitar el punto de partida desde el que se va a proceder a buscar.
	 * No es obligatorio que se trate de la raiz de todo el arbol.
	 * 
	 * La lista que devuelve contiene dos referencias a nodos: 
	 * el primero es el nodo que contiene el dato buscado y (null si el dato no esta en el arbol)
	 * el segundo corresponde al nodo del que cuelga (null si es la raiz).
	 * 
	 * @param raiz del subarbol sobre el que se va a buscar 
	 * @param buscado dato que se ppretende localizar
	 * @return lista con referencias
	 */
	protected List<Nodo> buscar (Nodo raiz, E buscado){

		Nodo actual = null, padre = null;
		LinkedList<Nodo> camino = new LinkedList<Nodo>();

		actual = raiz;
		camino.addFirst(padre);
		camino.addFirst(actual);

		while (actual != null && comparar(buscado, actual.getDato()) != 0) {
			padre = actual;            
			if (comparar(buscado, actual.getDato()) < 0)
				actual = actual.getIzq();
			else
				actual = actual.getDer();
			camino.addFirst(actual);
		}
		
		return camino;
	}

	/**
	 * Metodo para encontrar el elemento mayor en un subarbol
	 * 
	 * @param inicio punto desde el que se va a proceder a buscar
	 * @return referencia al nodo con el mayor valor del arbol
	 */
	protected Nodo mayor(Nodo inicio){

		Nodo actual = inicio;

		while(actual.getDer() != null)
			actual = actual.getDer();

		return actual;
	}

	/**
	 * Metodo para encontrar el elemento menor en un subarbol
	 * 
	 * @param inicio punto desde el que se va a proceder a buscar
	 * @return referencia al nodo con el menor valor del arbol
	 */	protected Nodo menor(Nodo inicio){

		 Nodo actual = inicio;

		 while(actual.getIzq() != null)
			 actual = actual.getIzq();

		 return actual;
	 }

	 /**
	  * Metodo auxiliar que permite enlazar un nuevo nodo en el arbol.
	  * El nuevo nodo colgará de aquel que se pasa como parametro 
	  *  y tendrá como contenido el dato facilitado
	  * 
	  * @param encontrado lista que contiene el nodo sobre el que colgará el nuevo
	  * @param datoInsertar dato que contendrá el nuevo nodo
	  * @return
	  */

	 protected Nodo insertar(Nodo padre, E datoInsertar){

		 Nodo nuevo = new Nodo(datoInsertar);

		 if (padre == null) //Arbol vacio
			 raiz = nuevo; // es la raiz
		 else 
			 if (comparar(datoInsertar, padre.getDato())>0) // Si el padre es menor
				 padre.setDer(nuevo); // se inserta a la derecha
			 else
				 padre.setIzq(nuevo); // se inserta a la izquierda

		 return nuevo;

	 }

	 /**
	  * Metodo auxiliar que permite desenlazar un nodo ya contenido en el arbol, respetando
	  * que el resto de nodos ya incluidos sigan conformando un árbol binario de búsqueda.
	  * 
	  * @param actual Nodo que se va a eliminar del arbol.
	  * @param padre Nodo al que está enlazado el nodo a eliminar
	  * @param datoBorrar dato que se pretende eliminar del arbol
	  * @return true en caso de que se haya procedido a eliminarlo y false en caso contrario
	  */
	 protected List<Nodo> eliminar(Nodo actual, Nodo padre, E datoBorrar){

		 boolean tieneIzq=false, tieneDer=false, esHijoDer=false, esHijoIzq=false;
		 List<Nodo> caminoModificado = new LinkedList<Nodo>();

		 tieneIzq = actual.getIzq() != null;
		 tieneDer = actual.getDer() != null;
		 if(padre!=null){
			 esHijoDer = padre.getDer() != null && padre.getDer().equals(actual);
			 esHijoIzq = padre.getIzq() != null && padre.getIzq().equals(actual);
		 }

		 // Si es una hoja se elimina sin más
		 if( !tieneIzq && !tieneDer){

			 // Si es la raiz
			 if(padre == null)
				 this.raiz = null;

			 // Si no es la raiz
			 if(esHijoDer){
				 padre.setDer(null);
			 } else if(esHijoIzq) {
				 padre.setIzq(null);
			 } 

			 // Si tiene 2 hijos, se sustituye por el menor de la derecha o por el mayor de la izquierda
		 } else if (tieneIzq && tieneDer){

			 Nodo sustituto;
			 double menor = Math.random();

			 if(menor > 0.5){
				 sustituto = menor(actual.getDer());
			 } else {
				 sustituto = mayor(actual.getIzq());
			 }

			 // Se intercambian los datos de los dos nodos
			 E aux = sustituto.getDato();
			 sustituto.setDato(actual.getDato());
			 actual.setDato(aux);

			 // Una vez sustituido, se pasa a eliminar del arbol 
			 // (al sustituirlo, pasa a ser una hoja)
			 List<Nodo> encontrado2;
			 if(menor > 0.5)
				 encontrado2 = buscar(actual.getDer(), datoBorrar);
			 else
				 encontrado2 = buscar(actual.getIzq(), datoBorrar);

			 padre = encontrado2.get(1);
			 if(padre == null)
				 padre = actual;
			 actual = encontrado2.get(0);

			 caminoModificado.addAll(encontrado2);
			 eliminar(actual, padre, datoBorrar);
			 return caminoModificado;

			 // Si sólo tiene un hijo se sustituye por su hijo
		 } else if (tieneDer){ // Sustitucion por el hijo derecho

			 if(padre == null)
				 this.raiz = actual.getDer();

			 if(esHijoDer){
				 padre.setDer(actual.getDer());
			 }else if(esHijoIzq){
				 padre.setIzq(actual.getDer());
			 }

		 } else if (tieneIzq){ // Sustitucion por el hijo izquierdo

			 if(padre == null)
				 this.raiz = actual.getIzq();

			 if(esHijoDer){
				 padre.setDer(actual.getIzq());
			 }else if(esHijoIzq){
				 padre.setIzq(actual.getIzq());
			 }

		 }

		 caminoModificado.add(actual);
		 return caminoModificado;

	 }
	 
	 /**
		 * Metodo que permitir� conocer la altura dentro del �rbol a la que se encuentra el elemento solicitado. 
		 * En caso de que el elemento no se encuentre en el �rbol devolver� -1.
		 * 
		 * @param elemento el elemento del cu�l queremos saber su altura en el �rbol
		 * @return la altura correspondiente a la del elemento solicitado y en caso de que no se encuentre 
		 * 			en el �rbol devolver� -1
		 */
		public int altura(E elemento) {
			if (raiz == null) {
	            return -1;
	        }

	        return retornarAltura(raiz, elemento, 0);
	    }

		/**
		 * Metodo que complementa al metodo altura que sirve para encontrar el nodo del elemento pasado por parametro
		 * en el metodo altura y hallar la altura m�xima del nodo.
		 * 
		 * @param nodo el nodo raiz del arbol
		 * @param elemento el elemento del cu�l queremos saber su altura en el arbol
		 * @param nivel el nivel en el que se encuentra el nodo
		 * @return 
		 */
		private int retornarAltura(Nodo nodo, E elemento, int nuevaaltura) {
			if(nodo == null) {
				return -1;
			}
			
			if(comparator != null) {
				int comparacion1 = comparator.compare(elemento, nodo.getDato());
				if(comparacion1 < 0) {
					return retornarAltura(nodo.getIzq(), elemento, nuevaaltura+1);
				}else if (comparacion1 > 0) {
					return retornarAltura(nodo.getDer(), elemento, nuevaaltura+1);
				}else {
					return nuevaaltura;
				}
			}else {
				Comparable<? super E> comparable = (Comparable<? super E>) elemento;
				int comparacion = comparar(elemento, nodo.getDato());
				if(comparacion < 0) {
					return retornarAltura(nodo.getIzq(), elemento, nuevaaltura+1);
				}else if(comparacion > 0) {
					return retornarAltura(nodo.getDer(), elemento, nuevaaltura+1);
				}else {
					return nuevaaltura;
				}
				}
			}
		
		/**
		 * Método que retorna la profundidad de un nodo con un valor específico en el árbol.
		 *
		 * @param elemento El valor del nodo cuya profundidad se quiere encontrar.
		 * @return Retorna la profundidad del nodo. Si el árbol está vacío o el nodo no se encuentra en el árbol, retorna -1.
		 */
		public int profundidad(E elemento) {
			if (raiz == null) {
			    return -1;
			}

			return retornarProfundidad(raiz, elemento, 0);
		}


		/**
		 * Metodo que complementa al metodo profundidad que sirve para encontrar el nodo del elemento pasado por parametro
		 * en el metodo profundidad y hallar la profundidad maxima del nodo.
		 * 
		 * @param nodo el nodo raiz del arbol
		 * @param elemento el elemento del cual queremos saber su profundidad en el arbol
		 */
		private int retornarProfundidad(Nodo nodo, E elemento, int nuevaprofundidad) {
			if (nodo != null) {
				if (this.comparar(nodo.getDato(), elemento) > 0) {

					nuevaprofundidad++;
					retornarProfundidad(nodo.getIzq(), elemento, nuevaprofundidad+1);
				}
				if (this.comparar(nodo.getDato(), elemento) < 0) {

					nuevaprofundidad++;
					retornarProfundidad(nodo.getDer(), elemento, nuevaprofundidad+1);
				}
			}
			return nuevaprofundidad;
		}
			

	/**
	  * Metodo que permite obtener el listado ordenado de datos contenidos
	  * en el Set. Permite obtener un subconjunto del mismo, al admitir en la
	  * raiz el punto en el que se comienza a recuperar datos.
	  *   
	  * Se emplea como metodo auxiliar para realizar el iterador
	  * Resulta poco eficiente, pero se emplea como muestra.
	  * 
	  * @param actual nodo a partir del que se realiza el recorrido del subarbol
	  * @param listaRecorridos recorrido en forma de lista (ordenado)
	  */
	 protected void inOrdenRecursivo(Nodo actual, List<E> listaRecorridos) {
		 if (actual != null) {            
			 inOrdenRecursivo(actual.getIzq(), listaRecorridos);            
			 listaRecorridos.add(actual.getDato());        
			 inOrdenRecursivo(actual.getDer(), listaRecorridos);
		 }
	 }
}