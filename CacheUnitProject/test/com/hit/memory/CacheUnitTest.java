package com.hit.memory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.hit.algorithm.IAlgoCache;
import com.hit.algorithm.LRUAlgoCacheImpl;
import com.hit.algorithm.SecondChance;
import com.hit.dao.DaoFileImpl;
import com.hit.dao.IDao;
import com.hit.dm.DataModel;

class CacheUnitTest {

	@Test
	void test() {
		
		IAlgoCache<Long, DataModel<Integer>> algo = new SecondChance<Long, DataModel<Integer>>(10);
		IDao<Long, DataModel<Integer>> dao = new DaoFileImpl<>("resources//datasource.txt");
		
		CacheUnit<Integer> cache = new CacheUnit<>(algo, dao);
		
		
		
		DataModel<Integer>[] datamodels = new DataModel[12];
		
	
	
		
		datamodels[0] =  new DataModel<Integer>(1l, 1);
		datamodels[1] =  new DataModel<Integer>(2l, 2);
		datamodels[2] =  new DataModel<Integer>(3l, 3);
		datamodels[3] =  new DataModel<Integer>(4l, 4);
		datamodels[4] =  new DataModel<Integer>(1l, 1);
		datamodels[5] =  new DataModel<Integer>(2l, 2);
		datamodels[6] =  new DataModel<Integer>(7l, 5);
		datamodels[7] =  new DataModel<Integer>(1l, 1);
		datamodels[8] =  new DataModel<Integer>(2l, 2);
		datamodels[9] =  new DataModel<Integer>(3l, 3);
		datamodels[10] =  new DataModel<Integer>(4l, 4);
		datamodels[11] =  new DataModel<Integer>(7l, 5);
		
		
		cache.putDataModels(datamodels);
		System.out.println("---print file---");
		cache.printfile();
		
		
		
		Long[] ids = new Long[2];
		ids[0] = 3l;
		ids[1] = 7l;
		
		DataModel<Integer>[] tempID = cache.getDataModels(ids);
		System.out.println("=====get data===========\n");
		for(int i=0; i< tempID.length; i++)
			if(tempID!=null)
				System.out.println("content = "+tempID[i].getContent()+"  ---  id = "+tempID[i].getDataModelId()+"\n");
		System.out.println("================\n");
		
		System.out.println("---print file---");
		cache.printfile();
	
		
		cache.removeDataModels(ids);
		System.out.println("====delete all data==========");
		cache.printfile();
		System.out.println("==============");
		
		
		
	
		
		
	}

}
