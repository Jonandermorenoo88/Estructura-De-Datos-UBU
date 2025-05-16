package es.ubu.gii.edat.pr01;



/**
 * Clase que alberga el código completo para la Práctica 01
 * 
 * @author Adrian Ortega Moradillo
 * @author Jon Ander Icera Moreno
 *
 */
public class ElementoMayoritario {

	/**
	 * Para poder devolver dos valores diferentes por parte de un mismo método, se necesita una clase sencilla con dos atributos.
	 * En este caso, la clase RespuestaMayoritaria permite encapsular en el mismo objeto:
	 * - elemento mayoritario encontrado
	 * - el numero de veces que se repite (o frecuencia) como un entero
	 * 
	 * Al devolver la respuesta de cada metodo, se deberán incluir los dos datos encontrados dentro de un objeto de este tipo y
	 * devolverlo como respuesta.
	 *  
	 * @author bbaruque
	 *
	 * @param <E>
	 */
	public static class RespuestaMayoritaria <E>{
		
		private E elemento;
		private int frecuencia;
		
		public RespuestaMayoritaria(E elemento, int frecuencia){
			this.elemento = elemento;
			this.frecuencia = frecuencia;
		}

		public E getElemento() {
			return elemento;
		}

		public int getFrecuencia() {
			return frecuencia;
		}

		
	}
	/**
	 * 
	 * @param <E>
	 * @param array
	 * @return
	 * @Algoritmo tiene una complejidad O(n^2), por los bucles for ,lo que hará
	 * que su tiempo de ejcución aumnete
	 */
	public static <E> RespuestaMayoritaria<E> mayoritarioIterativo(E[] array) {
		int frecuenciamax = 0;
		int contador = 0;
		E num = null;
		
		//En el primer bucle seleccionamos el elemnto del arreglo y va a contar cuantas veces aparecera
		for(int i = 0; i < array.length; i++) {	
			contador = 0;
			//En este bucle comprueba si la frecuencia del elemento mayoritoria es mayor que la mitad del tamaño del arreglo
			for(int j = 0; j < array.length; j++) {
				if(array[i]==array[j]) {
				contador++;
				}
				if(contador > frecuenciamax) {
					num=array[i];
					frecuenciamax = contador;	
				}
			}
		}
		/**Realizará una comprobación final , si la frecuencia máxima es mayor
		 * que la mitad del tamaño del arreglo entonces hay un elemnto mayoritario y se crea una instancia 
		 * en caso contrario se devuelve en el objeto RespuestaMayoritaria como null y el elemnto
		 * mayoritario como 0
		 */
		if (frecuenciamax > array.length/2) {
			return new RespuestaMayoritaria<E>(num,frecuenciamax);
		}else{
			return new RespuestaMayoritaria<E>(null,0);
		}
		
	}

	/**
	 * 
	 * @param <E>
	 * @param array
	 * @return un obejto RespuestaMayoritaria que encapsula el elemento y la frecuencia
	 * @Algoritmo usado divide y vencerás llamando al metodo RespuestaMayoritaria2  nos dará el elemento mayoritario
	 * @Objetivbo del algoritmo encontrar el elemnto mayoritario combiando los subarreglos para encontrarlo
	 * si es mayor cojerá dicho elemnto
	 * 
	 */
	public static <E> RespuestaMayoritaria <E> mayoritarioRecursivo (E[] array){
		
		//Definimos las variables
		E m;
		//Declaramos la variable m para almacenar el elemento mayoritario
		m = RespuestaMayoritaria2 (array, 0,array.length-1);
		
		//Conatremos la frecuencia del elemento mayoritario
		int r = frecuencia(array, m, 0, array.length-1);
		
		//Encapsusalomos el elemneto mayoritario y su frecuencia y lo devolvemos
		return  new RespuestaMayoritaria<E>(m, r); 

	}
	// Es posible emplear métodos auxiliares para la ayuda a la implementación de los métodos obligatorios.
	
	/**
	 * 
	 * @param <E>
	 * @param a
	 * @param inicio
	 * @param fin
	 * @return m1 o m2 que es el elmento mayoritario , o null
	 * @Algoritmo divide y vencerás 
	 * se cojerá un arreglo de un conjunto de elementos y se definirán los índices fin y inicio
	 * m1 y m2 será donde se almacenará los elementos mayoritarios
	 */
	private static <E> E  RespuestaMayoritaria2 (E[] a, int inicio, int fin){
		
		E m1=null, m2=null;
		
		// Si ambos subarreglos tienen el mismo elemento mayoritario, ese elemento es el mayoritario de la matriz completa.
		if(inicio == fin) {
			return a[inicio];
		}
		//lo que hago es dividir la matriz en dos
		int n = (inicio + fin)/2;
		//busca el elemento mayoritario en la izquierda
		m1 =  RespuestaMayoritaria2 (a , inicio, n);
		//busca el mayoritario en la derecha
		m2 = RespuestaMayoritaria2 (a, n+1, fin);
		
			//Comprueba si el elemento mayoritario de la parte izquierda es el mayoritario de la matriz completa
			if(m1 != null && frecuencia(a, m1, inicio, fin) > ((fin - inicio + 1) / 2)) {
				return m1;
				}
			// Si ninguno de los subarreglos tiene un elemento mayoritario, la matriz completa tampoco lo tiene.
			if(m2 != null && frecuencia(a, m2, inicio, fin) > ((fin - inicio + 1) / 2)) {
				return m2;
		
				}
	//Si no encuentra ningún elemento mayoritario en las submatrices 
		return null;
	}
	/**
	 * 
	 * @param <E>
	 * @param a
	 * @param elemento
	 * @param izq
	 * @param der
	 * @return
	 * Recorrerá de inicio a fin y contará si el elemnto actual es igual a elemnto buscado y si es así incrementará el contador
	 * @Algoritmo "división y conquista" que es paar buscar un arreglo genérico
	 * O(n)
	 */
	private static <E> int frecuencia(E[] a, E elemento, int izq, int der) {
		int contador = 0;
		//Recorremos para encontrar si el elemnto en el índice actual es igual al que se busca
	    for (int i = izq; i <= der; i++) {    
	        if (a[i].equals(elemento)) {
	            contador++;
	        }
	    }
	    //Devolvemos el conatdor que dice la veces que dicho elemnto se encontró
	    return contador;
	
	}
}

