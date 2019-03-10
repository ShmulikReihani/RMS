package com.hit.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class SecondChance<K, V> extends AbstractAlgoCache<K, V> {
	
	class Node<K, V>{
		
		K key;
		V value;
		Integer bit;
		
		public Node( K key,V value) {
			super();
			this.key = key;
			this.value = value;
			this.bit = 0;
		}
	}
	
	HashMap< K , V > cache;
	ArrayList<Node<K,V>> lastUsedList;
	
	
	
	public SecondChance(int capacity) {
		super(capacity);
		cache = new HashMap<K,V>(capacity);
		
		lastUsedList = new ArrayList<Node<K,V>>();
		
		
	}

	@Override
	public V getElement(K key) {
		if( cache.containsKey(key)){
			V temp=  cache.get(key);
			return temp;
		}
		else
			return null;	
		
	}

	@Override
	public V putElement(K key, V value) {
		Node temp = new Node<K, V>(key, value);
		int index = seeIfHere(key, value);
		if(capacity<CacheSize)
		{
	
			
			if(index != -1)
			{ 
				if(lastUsedList.get(index).bit==0)
					lastUsedList.get(index).bit++;
			}
			else {
				cache.put(key, value);
				lastUsedList.add(temp);
				capacity++;
			}
			
		}
		else {
			if(index != -1)
			{
				if(lastUsedList.get(index).bit==0)
					lastUsedList.get(index).bit++;
			}
			else 
			{	
				for(int i=0; i<lastUsedList.size(); i++)
				{
						if(lastUsedList.get(i).bit == 0)
						{
							cache.remove(value);
							lastUsedList.remove(i);
							cache.put(key, value);
							lastUsedList.add(temp);
							bitZero();
							return value;
						}	
				}
			}
			
		}
		return value;
	}
	
	public void printCache() {
		
		/*last.forEach( (key,node) -> {
			System.out.println("key: "+ key +" value: "+ node.value + " bit: " + node.bit);
		});*/
		lastUsedList.forEach(node ->{
			System.out.println("key: "+ node.key +" value: "+ node.value + " bit: " + node.bit);
		});
		
	}

	
	public int seeIfHere(K key, V value)
	{
		for(int i=0; i< lastUsedList.size(); i++)
			if(lastUsedList.get(i).value == value)
				return i;
		return -1;
	}
	
	public void bitZero()
	{
		for(int i=0; i< lastUsedList.size(); i++)
			lastUsedList.get(i).bit = 0;
	}
	
	@Override
	public void removeElement(K key) {
		for(int i=0; i< lastUsedList.size(); i++)
			if(lastUsedList.get(i).key == key)
				lastUsedList.remove(i);
		cache.remove(key);
	}

}
