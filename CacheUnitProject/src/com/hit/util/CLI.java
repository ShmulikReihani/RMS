package com.hit.util;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

import com.hit.server.Server;

public class CLI implements Runnable{

	InputStream in;
	OutputStream out;
	PropertyChangeSupport changeSupport;
	
	
	public CLI(InputStream in, OutputStream out)
	{
		this.in = in;
		this.out = out;
		
		this.changeSupport = new PropertyChangeSupport(this);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener pcl)
	{
		changeSupport.addPropertyChangeListener(pcl);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener pcl)
	{
		changeSupport.removePropertyChangeListener(pcl);
	}
	
	public void write(String string)
	{
		System.out.println(string);
	}
	
	@Override
	public void run() {
		
		String availableComamnds = "Available commands:\n start - to start the server\n shutdown - to terminate the server";
		
		
		Scanner in = new Scanner(System.in);
	    System.out.println(availableComamnds);
	    String input = in.nextLine();
	    
	    while(!input.equals("shutdown"))
	    {
	    	if(input.equals("start"))
	    	{
	    		changeSupport.firePropertyChange("startServer", null, null);
	    	}
	    	else {
	    		System.out.println("Unknown command");
	    		System.out.println(availableComamnds);
	    	}
	    	input = in.nextLine();
	    }
	    
	    changeSupport.firePropertyChange("shutdownServer", null, null);
	}
	
	

}
