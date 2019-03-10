package com.hit.server;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.hit.services.CacheUnitController;
import com.hit.util.CLI;

public class Server implements PropertyChangeListener, Runnable{

	int port;
	ServerSocket server;
	Thread serverThread;
	boolean isServerRunning = false;
	Socket socket;
	HandleRequest<String> request;
	
	public Server(int port)
	{
		this.port = port;	
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
			
		CLI cli = (CLI) evt.getSource();
		if(evt.getPropertyName().equals("startServer"))
		{
			if(this.isServerRunning)
			{
				cli.write("server is running already");
			}
			else
			{
				cli.write("starting server...");
				this.isServerRunning = true;
				serverThread = new Thread(this);
				serverThread.start();
				cli.write("Server has benn started.");
			}
		}
		else if(evt.getPropertyName().equals("shutdown")) {
			cli.write("Terminating server...");
			cli.write("Waiting for existing requests completion...");

			cli.write("All requests completed.");
			cli.write("Closing sockets.");
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			isServerRunning = false;
			cli.write("Server closed");
		}
	}

	
	@Override
	public void run() {
		
		try {
			server = new ServerSocket(port);
			while(this.isServerRunning)
			{
				Socket socket = this.server.accept();
				CacheUnitController<String> controller = new CacheUnitController<String>();
				HandleRequest<String> hr = new HandleRequest<String>(socket, controller);
				new Thread(hr).start();
			}		
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
