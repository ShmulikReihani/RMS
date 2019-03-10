package com.hit.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class CacheUnitClient {
	
	
	Socket socket;
	private PrintWriter pw;
	private Scanner sc;
	private String address;
	
	public CacheUnitClient() {
		this.address = "localhost";
	}
	
	
	public String send(String request) throws UnknownHostException, IOException {
		
		if(socket == null)
		{
			socket = new Socket(address, 34567);
			pw = new PrintWriter(socket.getOutputStream());
			sc = new Scanner(socket.getInputStream());
		}
		pw.println(request);
		pw.flush();
		String response = sc.nextLine();
		return response;
	}

}
