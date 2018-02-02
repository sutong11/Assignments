package com.acertainmarket.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;

public class AuctionMarketHTTPServerUtility {
	
	/**
     * Creates a server on the port and blocks the calling thread
     */
	public static boolean createServer(int port,
			AbstractHandler handler) {
		Server server = new Server(port);
		if(handler != null){
			server.setHandler(handler);
		}
		
		try{
			server.start();
			server.join();
		} catch(Exception ex){
			ex.printStackTrace();
		}
		return true;
	}

}
