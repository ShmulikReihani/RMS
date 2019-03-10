package com.hit.server;

import java.io.IOException;

import com.hit.util.CLI;

public class CacheUnitServerDriver {

	public CacheUnitServerDriver() {
		super();
		
	}
	
	public static void main(java.lang.String[] args)
	{
		CLI cli = new CLI(System.in, System.out);
		Server server;
		server = new Server(34567);
		cli.addPropertyChangeListener(server);
		new Thread(cli).start();		
	}
}
