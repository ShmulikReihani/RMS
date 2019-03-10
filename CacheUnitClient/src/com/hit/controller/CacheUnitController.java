package com.hit.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Observable;

import com.hit.model.CacheUnitClient;
import com.hit.model.CacheUnitModel;
import com.hit.model.Model;
import com.hit.view.CacheUnitView;
import com.hit.view.View;

public class CacheUnitController implements Controller{
	
	View view;
	Model model;
	
	CacheUnitClient cacheUnitClient;
	CacheUnitView cacheUnitView;
	
	public CacheUnitController( Model model,View view) {
		super();
		this.view =  view;
		this.model =(CacheUnitModel) model;
		
		
		
		cacheUnitClient = new CacheUnitClient();
	}
	
	public void update(Observable obs,Object obj)
	{
		if(obs instanceof View)
		{
			String[] parts = ((String) obj).split(",");
			String request;
			if(parts[0].equals("fileSelected"))
			{
				try {
					request = new String(Files.readAllBytes(Paths.get(parts[1])));
					model.updateModelData(request);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(parts[0].equals("serverStatus"))
			{
				
				model.updateModelData("{\"requestHeaders\":{\"action\":\"serverStatus\"},\"requestBody\":[]}");
			}
			
			
		}else if(obs instanceof Model )
		{
			
			this.view.updateUIData((String)obj);
		}
	}

}
