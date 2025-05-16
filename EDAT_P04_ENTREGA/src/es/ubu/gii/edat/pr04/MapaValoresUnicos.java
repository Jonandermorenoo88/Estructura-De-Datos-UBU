package es.ubu.gii.edat.pr04;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Set;

/**
 * Clase MapaValoresUnicos, que extiende la clase AbstractMap y representa un mapa en el que cada valor
 * se corresponde con una única clave y viceversa. Para garantizar esta unicidad, se utiliza tanto un
 * HashMap directo como otro inverso.
 *
 * @param <K> Tipo de la clave
 * @param <V> Tipo del valor
 */
public class MapaValoresUnicos<K, V> extends AbstractMap<K, V> {

	/**
     * HashMap directo que almacena las correspondencias clave-valor.
     */
	private HashMap<K, V> directo;
	
	/**
     * HashMap inverso que almacena las correspondencias valor-clave.
     */
	private HashMap<V, K> inverso;
	
	/**
     * Constructor de la clase que inicializa ambos HashMaps.
     */
	public MapaValoresUnicos() {
		directo = new HashMap<K, V>();
		inverso = new HashMap<V, K>();
	}
	
	/**
     * Inserta una nueva entrada en el mapa, compuesta por una clave y un valor.
     *
     * @param key Clave de la entrada
     * @param value Valor de la entrada
     * @return Valor anterior asociado a la clave, o null si no existía
     * @throws IllegalArgumentException si el valor ya existe en el mapa
     */
	@Override
	public V put(K key, V value) {
		if (inverso.containsKey(value)) {
			throw new IllegalArgumentException("El valor ya existe en el mapa");
		}
		V oldValue = directo.put(key, value);
		if (oldValue != null) {
			inverso.remove(oldValue);
		}
		inverso.put(value, key);
		return oldValue;
	}
	
	/**
     * Elimina la entrada correspondiente a una clave dada.
     *
     * @param key Clave de la entrada a eliminar
     * @return Valor correspondiente a la clave, o null si no existía
     */
	@Override
	public V remove(Object key) {
		V value = directo.remove(key);
		if (value != null) {
			inverso.remove(value);
		}
		return value;
	}
	
	/**
     * Elimina todas las entradas del mapa.
     */
	@Override
	public void clear() {
		directo.clear();
		inverso.clear();
	}
	
	/**
     * Devuelve el conjunto de entradas del mapa.
     *
     * @return Conjunto de entradas del mapa
     */
	@Override
	public Set<Entry<K, V>> entrySet() {
		return directo.entrySet();
	}
	
	/**
     * Devuelve un nuevo mapa cuyas correspondencias son las inversas de las del mapa original.
     *
     * @return Nuevo mapa con correspondencias inversas a las del original
     */
	public MapaValoresUnicos<V, K> inverso() {
		MapaValoresUnicos<V, K> inverseMap = new MapaValoresUnicos<V, K>();
		inverseMap.directo = inverso;
		inverseMap.inverso = directo;
		return inverseMap;
	}

	/**
	 * Asigna un valor a una clave en el mapa, reemplazando el valor anterior si ya existe.
	 * Si el valor ya existe en el mapa, se elimina la clave asociada con el valor anterior.
	 * Si la clave ya existe en el mapa, el valor anterior se elimina y se asigna el nuevo valor a la clave.
	 * 
	 * @param key la clave a la que se asignará el valor
	 * @param value el valor a asignar
	 * @return el valor anterior asociado con la clave, o null si la clave no estaba en el mapa.
	 */
	
	public V forcePut(K key, V value) {
		V oldValue = remove(key);
		if (oldValue != null) {
			inverso.remove(oldValue);
		}
		if (inverso.containsKey(value)) {
			K oldKey = inverso.remove(value);
			directo.remove(oldKey);
		}
		put(key, value);
		return oldValue;
	}
}
