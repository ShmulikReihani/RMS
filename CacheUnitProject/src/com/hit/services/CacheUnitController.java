package com.hit.services;

import java.util.logging.Logger;

import com.hit.dao.IDao;
import com.hit.dm.DataModel;
import com.hit.memory.CacheUnit;

public class CacheUnitController<T> {

	CacheUnitService<T> cacheUnitService;

	public CacheUnitController() {
		super();
		cacheUnitService = new CacheUnitService<>();
	}

	public boolean update(DataModel<T>[] dataModels)
	{	
		return cacheUnitService.update(dataModels); 
	}
	
	public boolean delete(DataModel<T>[] dataModels)
	{
		return cacheUnitService.delete(dataModels);
	}
	
	public DataModel<T>[] get(DataModel<T>[] dataModels)
	{
		return cacheUnitService.get(dataModels);
	}
	
	// methods below added to support more statistics
		public int getSwapsNumber() {
			return this.cacheUnitService.getSwapsNumber();
		}
		
		
		public int getCapacity() {
			return this.cacheUnitService.getCapacity();
		}
}
