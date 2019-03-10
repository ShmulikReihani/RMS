package com.hit.algorithm;

public interface IAlgoCache<K,V> {
	
	public final int CacheSize= 3;
	
	public V getElement(K key);
	public V putElement(K key, V value);
	public void removeElement(K key);
}