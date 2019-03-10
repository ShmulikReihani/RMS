package com.hit.algorithm;

import org.junit.jupiter.api.Test;

class IAlgoCacheTest {

	@Test
	void test() {
		System.out.println("************LRU TEST**************");
		LRUAlgoCacheImpl<Integer,Integer> lrutest=new LRUAlgoCacheImpl<Integer,Integer>(0);
		Integer v;
		v=lrutest.putElement(1, 11);
		System.out.println(v);
		v=lrutest.putElement(2, 22);
		System.out.println(v);
		v=lrutest.putElement(3, 33);
		System.out.println(v);
		v=lrutest.putElement(4, 44);
		System.out.println(v);
		v=lrutest.getElement(2);
		System.out.println(v);
		v=lrutest.putElement(5, 55);
		System.out.println(v);
		System.out.println("*************");
		lrutest.print();
		lrutest.removeElement(5);
		System.out.println("*************");
		lrutest.print();
		
		
		System.out.println("************RANDOM TEST**************");
		
		RandomAlgoCacheImpl<Integer,Integer> randtest=new RandomAlgoCacheImpl<Integer,Integer>(0);
		Integer v2;
		v2=randtest.putElement(1, 11);
		System.out.println(v2);
		v2=randtest.putElement(2, 22);
		System.out.println(v2);
		v2=randtest.putElement(3, 33);
		System.out.println(v2);
		v2=randtest.putElement(4, 44);
		System.out.println(v2);
		v2=randtest.getElement(2);
		System.out.println(v2);
		v2=randtest.putElement(5, 55);
		System.out.println(v2);
		
		
		
		System.out.println("************SECOND CHANCE TEST**************");
		SecondChance<Integer, Integer> sec = new SecondChance<>(0);
		
		Integer v3;
		v3= sec.putElement(1, 7);
		System.out.println(v3);
		v3= sec.putElement(2, 0);
		System.out.println(v3);
		v3= sec.putElement(3, 1);
		System.out.println(v3);
		v3= sec.putElement(4, 2);
		System.out.println(v3);
		v3= sec.putElement(5, 0);
		System.out.println(v3);
		v3= sec.putElement(6, 3);
		System.out.println(v3);
		v3= sec.putElement(7, 0);
		System.out.println(v3);
		v3= sec.putElement(8, 4);
		System.out.println(v3);
		v3= sec.putElement(9, 2);
		System.out.println(v3);
		v3= sec.putElement(10, 3);
		System.out.println(v3);
		v3= sec.putElement(11, 0);
		System.out.println(v3);
		v3= sec.putElement(12, 3);
		System.out.println(v3);
		v3= sec.putElement(13, 2);
		System.out.println(v3);
		v3= sec.putElement(14, 1);
		System.out.println(v3);
		v3= sec.putElement(15, 2);
		System.out.println(v3);	
		v3= sec.putElement(16, 0);
		System.out.println(v3);	
		v3= sec.putElement(17, 1);
		System.out.println(v3);
		v3= sec.putElement(18, 7);
		System.out.println(v3);
		v3= sec.putElement(19, 0);
		System.out.println(v3);
		v3= sec.putElement(20, 1);
		System.out.println(v3);
		System.out.println("get: " + sec.getElement(14));
	}

}
