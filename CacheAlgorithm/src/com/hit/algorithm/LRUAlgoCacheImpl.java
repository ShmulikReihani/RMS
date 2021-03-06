package com.hit.algorithm;


import java.util.Set;

public class LRUAlgoCacheImpl<K,V> extends AbstractAlgoCache<K, V> {

	public LRUAlgoCacheImpl(int capacity) {
		super(capacity);
	}

	@Override
	public V putElement(K key, V value) {
		if(capacity<CacheSize)
		{
			list.addFirst(key);
			map.put(key, value);
			capacity++;	
			return value;
		}

		else {
			list.addFirst(key);
			list.addFirst(list.pollLast());
			K tempk=list.getFirst();;
			V tempv=map.get(tempk);
			map.remove(tempk);
			map.put(key, value);
			return tempv;		
		}
		
	}

	@Override
	public V getElement(K key) {
		if( map.containsKey(key)){
			K temp= list.get(list.indexOf(key));
			list.remove(list.get(list.indexOf(key)));
			list.addFirst(temp);
			return map.get(temp);
		}
		return null;
	}

	public void print()
	{
		@SuppressWarnings("unchecked")
		Set<Integer> keys = (Set<Integer>) map.keySet();  //get all keys
		for(Integer i: keys)
		{
		    System.out.println(map.get(i));
		}
	}

	
	

}
