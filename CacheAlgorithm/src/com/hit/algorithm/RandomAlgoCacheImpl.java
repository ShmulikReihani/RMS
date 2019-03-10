package com.hit.algorithm;

public class RandomAlgoCacheImpl<K, V> extends AbstractAlgoCache<K, V>{

	public RandomAlgoCacheImpl(int capacity) {
		super(capacity);
	}

	@Override
	public V getElement(K key) {
		if( map.containsKey(key)){
			K temp= list.get(list.indexOf(key));
			return map.get(temp);
		}
		else
			return null;	
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
			int random = (int )(Math.random() * capacity);
			K tempk=list.get(random);
			list.remove(random);
			list.add(random,key);
			V tempv=map.get(tempk);
			map.remove(tempk);
			map.put(key, value);
			return tempv;
		}
		
	}

	

}
