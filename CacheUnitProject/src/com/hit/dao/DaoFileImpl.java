package com.hit.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.hit.dm.DataModel;

public class DaoFileImpl<T> implements IDao< Long , DataModel<T> >{

	String filePath;
	int capacity = 10;
	
	ArrayList< DataModel<T> > arrayOfDataModel;
	
	
	public DaoFileImpl(String filePath, int capacity) {
		super();
		this.filePath = filePath;
		this.capacity = capacity;
		
		arrayOfDataModel = new ArrayList<DataModel<T>>(capacity);
	}

	public DaoFileImpl(String filePath) {
		super();
		this.filePath = filePath;
		
		arrayOfDataModel = new ArrayList<DataModel<T>>(capacity);
		
	}

	

	@Override
	public void save(DataModel<T> entity) {
		
		try {
			
			
			ObjectOutputStream out= new ObjectOutputStream(new FileOutputStream(filePath));
			arrayOfDataModel.add(entity);
			out.writeObject(arrayOfDataModel);
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void delete(DataModel<T> entity) {
		
		ObjectInputStream in;
		try {
			
			File file = new File(filePath);
			if(file.length() != 0)
			{
				
				in = new ObjectInputStream(new FileInputStream(filePath));
				
				ArrayList< DataModel<T> > temp =(ArrayList<DataModel<T>>) in.readObject();
				
				for(int i=0; i< temp.size(); i++)
				{
					if(entity.equals(temp.get(i)))
					{
						if(arrayOfDataModel.contains(temp.get(i)))
							arrayOfDataModel.remove(i);
						temp.remove(i);
					}
				}
			
				ObjectOutputStream out= new ObjectOutputStream(new FileOutputStream(filePath));
				out.writeObject(temp);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public DataModel<T> find(Long id) {
		
		try {
			
			File file = new File(filePath);
			if(file.length() != 0)
			{
				
				ObjectInputStream in= new ObjectInputStream(new FileInputStream(filePath));
				ArrayList< DataModel<T> > temp =(ArrayList<DataModel<T>>) in.readObject();
				
				for(int i=0; i< temp.size(); i++)
					if(id.equals(temp.get(i).getDataModelId()))
						return temp.get(i);
			}
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public void printAll() {
		
		ObjectInputStream in;
		try {
			
			File file = new File(filePath);
			if(file.length() != 0)
			{
			
				in = new ObjectInputStream(new FileInputStream(filePath));
				ArrayList< DataModel<T> > temp =(ArrayList<DataModel<T>>) in.readObject();
				
				if(temp.size() == 0)
					System.out.println("file is emty");
				else {
					System.out.println("print all: size() = " + temp.size());
					for(int i=0; i< temp.size(); i++)
						System.out.println(temp.get(i).getContent() + " =value - id= " + temp.get(i).getDataModelId());
				}
			}
			
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				
	}

	

}
