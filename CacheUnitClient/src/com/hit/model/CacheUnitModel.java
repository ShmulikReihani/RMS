package com.hit.model;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Observable;

public class CacheUnitModel extends Observable implements Model{

	CacheUnitClient client;
	
	public CacheUnitModel()
	{
		client = new CacheUnitClient();
	}
	
	@Override
	public <T> void updateModelData(T t) {
			
		try {

			String response = client.send((String) t);
			setChanged();
	        notifyObservers(response);
						
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	
	
}
