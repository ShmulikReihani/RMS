package com.hit.server;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hit.dm.DataModel;
import com.hit.services.CacheUnitController;

public class HandleRequest<T> implements Runnable{

	Socket s;
	CacheUnitController<T> controller;
	Scanner scanner;
	PrintWriter printWriter;
	Gson gson;
	boolean processing;
	
	private int requestsNumber = 0;
	private int numberOfDataModelsUpdates = 0;
	private int numDelete = 0;
	
	public HandleRequest(Socket s, CacheUnitController<T> controller) throws IOException
	{
		this.s = s;
		this.controller = controller;
		
		scanner = new Scanner(s.getInputStream());
		printWriter = new PrintWriter(s.getOutputStream());
		gson = new Gson();
		this.processing = true;
	}
	
	
	@Override
	public void run() {
		
		while(processing)
		{
			String jsonRequest;
			try {
				processing = false;
				jsonRequest = scanner.nextLine();
				processing = true;		
			}
			catch(Exception e) {
				processing = false;
				return;
			}
			
			Type ref = new TypeToken<Request<DataModel<T>[]>>(){}.getType();
			Request<DataModel<T>[]> request = this.gson.fromJson(jsonRequest, ref);
		
			DataModel<T>[] requestBody = request.getBody();
				
			switch (request.getHeaders().get("action"))
			{
				case "GET" : 
					DataModel<T>[] dataModels = this.controller.get(requestBody);
					this.printWriter.println(this.gson.toJson(dataModels));
					requestsNumber++;
					break;
				case "UPDATE" : 
					if (this.controller.update(requestBody)) {
						this.printWriter.println("UPDATE Done!");
						numberOfDataModelsUpdates+=requestBody.length;
					}
					else {
						this.printWriter.println("UPDATE FAILED!");
					}
					requestsNumber++;
					break;
				case "DELETE" : 
					if(this.controller.delete(requestBody)) {
						this.printWriter.println("DELETE Done!");
						this.numDelete+=requestBody.length;
					}
					else {
						this.printWriter.println("DELETE FAILED!");
					}
					requestsNumber++;
					break;
				case "serverStatus":
					this.printWriter.println(
					 ". Swaps number: " + this.controller.getSwapsNumber()  
					+ ". - Total number of requests: " + this.requestsNumber 
					+ ". - Number of UPDATE DataModels : " + this.numberOfDataModelsUpdates 
					+ ". - Number of DELETE DataModels : " + this.numDelete + "."); 
					break;
				
			}
			printWriter.flush();
		}
	}
}
