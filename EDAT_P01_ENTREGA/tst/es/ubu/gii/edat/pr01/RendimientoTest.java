package es.ubu.gii.edat.pr01;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.junit.Test;

/**
 * 
 * Clase que pretende servir como ejemplo para la realización de análisis
 * de la complejidad algortimica de un programa (o parte de él).
 * 
 * Las salidas de datos se han formateado de manera que cumplen con el formato .csv
 * Basta con almacenarlas en un fichero de texto para luego poder importar esos datos
 * a una hoja de cálculo y realizar las operaciones y gráficas que sean necesarias. 
 * 
 * @author Jon Ander Incera Moreno y Adrián Ortega Moradillo
 *
 */

public class RendimientoTest {

	// Numero maximo de elementos que se quiere probar a trabajar
	int limiteElementos = 10000;
	// Numero de elementos en el que se quiere aunmentar el size en cada paso
	// Se propone obtener 10 mediciones diferentes por cada experimento
	int paso = limiteElementos/10;
	
	
	Integer[] listaenteros;
	Character [] listacaracteres;
	
	
	/*Este método imprime los tiempos de ejecución para encontrar un elemento mayoritario en un array de enteros usando un método iterativo.
	 * Genera diferentes sizes de arrays y mide el tiempo que tarda el método iterativo en encontrar un elemento mayoritario, si lo hay.
	 * Imprime el número de elementos y el tiempo en milisegundos para cada size de array.
	 * 
	 */
	
	public void compruebaIntegerIterativo(){

		double inicio, fin;
		
		System.out.println("Tiempos de ejecución para comprobación de mayoritarios con el método iterativo");
		System.out.println("Array de Enteros  Iterativo: ");
		
		for(int i=1; i<limiteElementos; i=i+paso){
			
			// Generamos el array de enteros
			listaenteros = this.arrayDeEnteros(i);
			
			inicio = System.currentTimeMillis();
			ElementoMayoritario.mayoritarioIterativo(listaenteros);
			fin = System.currentTimeMillis(); 
			
			System.out.println("Num.Elem,"+i+",tiempo(ms),"+(fin-inicio));

		}
		
	}
	
	/*
	 * Este método usa el algoritmo recursivo para encontrar el elemento mayoritario en un array de enteros
	 * 
	 * 
	 * */
	
	public void comprobarIntegerRecursivo(){

		double inicio, fin; // Variables para medir el tiempo de ejecución
		
		System.out.println("Tiempos de ejecución para comprobación de mayoritarios con el método recursivo");
		System.out.println("Array de Enteros Recursivo: ");
		
		for(int i=1; i<limiteElementos; i=i+paso){// Bucle que incrementa el size del array
			
			// Generamos el array de enteros
			listaenteros = this.arrayDeEnteros(i);
			
			inicio = System.currentTimeMillis();// Guardamos el tiempo inicial
			ElementoMayoritario.mayoritarioRecursivo(listaenteros);// Llamamos al método recursivo
			fin = System.currentTimeMillis(); 
			
			System.out.println("Num.Elem,"+i+",tiempo(ms),"+(fin-inicio));// Mostramos los resultados

		}
		
	}
	
	public void comprobarCharacterIterativo(){

		double inicio, fin;// Variables para medir el tiempo de ejecución
		
		System.out.println("Tiempos de ejecución para comprobación de mayoritarios con el método iterativo");
		System.out.println("Array de caracteres Iterativo: ");
		
		for(int i=1; i<limiteElementos; i=i+paso){
			
			// Generamos el array de enteros
			listaenteros = this.arrayDeEnteros(i);
			
			inicio = System.currentTimeMillis();
			ElementoMayoritario.mayoritarioIterativo(listaenteros);
			fin = System.currentTimeMillis(); 
			
			System.out.println("Num.Elem,"+i+",tiempo(ms),"+(fin-inicio));

		}
		
	}
	
	public void comprobarCharacterRecursivo(){

		double inicio, fin;// Variables para medir el tiempo de ejecución
		
		System.out.println("Tiempos de ejecución para comprobación de mayoritarios con el método recursivo");
		System.out.println("Array de caracteres Recursivo: ");
		
		for(int i=1; i<limiteElementos; i=i+paso){
			
			// Generamos el array de enteros
			listacaracteres = this.generarArrayCaracteres(i);
			
			inicio = System.currentTimeMillis();
			ElementoMayoritario.mayoritarioRecursivo(listaenteros);
			fin = System.currentTimeMillis(); 
			
			System.out.println("Num.Elem,"+i+",tiempo(ms),"+(fin-inicio));

		}
		
	}
	
	
	@Test
	public void testIncremental(){

        limiteElementos = 100;

        /* El test va aumentando el tama�o del problema en un bucle infinito.
         Es necesario interrumpir la ejecuci�n manualmente para que finalice la prueba.
         Esto solo se hace cuando no se conoce bien el tama�o de problema en el que se 
         empiecen a apreciar claramente las diferencias en tiempo.
         (Ese tama�o puede ser diferente por cada m�todo) */

        while(true && limiteElementos <=100000 ){

            limiteElementos = limiteElementos * 10;
            paso = limiteElementos/10;
            
            System.out.println("------------------------------------");
            System.out.println(String.format("Realizando prueba con %d elementos",limiteElementos));
            
            System.out.println("------------------------------------");
            compruebaIntegerIterativo();
            comprobarIntegerRecursivo();
            System.out.println("------------------------------------");
            comprobarCharacterIterativo();
            comprobarCharacterRecursivo();
            System.out.println("Prueba finalizada");
            System.out.println("------------------------------------");

        }

    }
	// Este método genera un array de enteros de un tamaño dado
	private Integer[] arrayDeEnteros(int size) {
		
		Integer[] array = new Integer[size];// Creamos el array
		Random random = new Random();// Creamos un objeto para generar números aleatorios
		
		for(int i = 0; i < size; i++) {// Recorremos el array
			array[i] = random.nextInt(9) + 1;// Asignamos un valor aleatorio entre 1 y 9 a cada posición		
		}
		return array;// Devolvemos el array
		
	}
	// Este método genera un array de caracteres de un tamaño dado
	private Character[] generarArrayCaracteres(int size) {
		
		Character[] array = new Character[size];// Creamos el array
		Random random = new Random(); // Creamos un objeto para generar caracteres aleatorios
		
		for(int i = 0; i < size; i++) { // Recorremos el array
			 char caracteres = (char) (random.nextInt(7) + 'b'); // Asignamos un valor aleatorio entre 'b' y 'g' a cada posición
			 array[i] = caracteres;
		}
		return array;// Devolvemos el array
	}
	
}
