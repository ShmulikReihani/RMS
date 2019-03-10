package com.hit.algorithm;

import java.util.HashMap;
import java.util.LinkedList;

public abstract class AbstractAlgoCache<K, V> implements IAlgoCache<K, V> {

	int capacity;
	HashMap<K , V> map;
    LinkedList<K> list;
	
	public AbstractAlgoCache(int capacity) {
		this.capacity = capacity;
		map = new HashMap<K,V>(capacity);
		list = new LinkedList<K>();
	}
	
	public K getKeyFromValue(HashMap<K,V> map, V value) {
        for (K o : map.keySet()) {
          if (map.get(o).equals(value)) {
            return o;
          }
        }
        return null;
    }
	
	public abstract V putElement(K key, V value);

	public abstract V getElement(K key);
	
	public void removeElement(K key)
	{
		map.remove(key);
		list.remove(key);
	}
}
