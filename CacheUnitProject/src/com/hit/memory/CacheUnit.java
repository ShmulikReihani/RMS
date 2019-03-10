package com.hit.memory;

import java.util.ArrayList;

import com.hit.algorithm.IAlgoCache;
import com.hit.algorithm.SecondChance;
import com.hit.dao.IDao;
import com.hit.dm.DataModel;

public class CacheUnit<T>{

	IAlgoCache<Long,DataModel<T>> algo;
	IDao<Long,DataModel<T>> dao;

	public CacheUnit(IAlgoCache<Long, DataModel<T>> algo, IDao<Long, DataModel<T>> dao){
		super();
		this.algo = algo;
		this.dao = dao;
	}
	
	
	public DataModel<T>[] getDataModels(Long[] ids) 
	{
		DataModel<T>[] temp = new DataModel[1];
		int top = -1;
		DataModel<T> tt;
		for(int i=0; i < ids.length; i++)
		{		
			tt =  dao.find(ids[i]);	
			if(tt != null)
			{
				if(top == temp.length - 1)
				{
					int length = temp.length;
					DataModel<T>[] tempNew = new DataModel[temp.length+1];
					System.arraycopy(temp, 0, tempNew, 0, length);
					temp = tempNew;
				}
				top++;
				temp[top] = tt;
			}
		}
		return temp;
	}
	
	public DataModel<T>[] putDataModels(DataModel<T>[] datamodels)
	{
		DataModel<T> temp;
		ArrayList<DataModel<T> > array = new ArrayList<DataModel<T>>();
		for(int i=0; i < datamodels.length; i++)
		{
			temp = algo.putElement(datamodels[i].getDataModelId(), datamodels[i]);
			if(!(array.contains(temp)))
			{
				array.add(temp);
			}
		}	
		for(int i=0; i < array.size(); i++)
		{
			dao.save(array.get(i));
		}
		return datamodels;
	}
	
	public void removeDataModels(java.lang.Long[] ids) 
	{
		DataModel<T> temp;
		for(int i=0; i < ids.length; i++)
		{
			temp = dao.find(ids[i]);
			if(temp != null)
			{
				algo.removeElement(ids[i]);
				dao.delete(temp);
			}		
		}
	}
	
	public void printfile()
	{
		dao.printAll();
		System.out.println("algo - "+algo.toString());
	}
}
