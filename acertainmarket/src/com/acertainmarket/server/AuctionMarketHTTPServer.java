package com.acertainmarket.server;

import com.acertainmarket.CertainAuctionMarket;
import com.acertainmarket.utils.AuctionMarketConstants;

public class AuctionMarketHTTPServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CertainAuctionMarket auctionMarket = new CertainAuctionMarket();
		int listen_on_port = 8081;
		AuctionMarketHTTPMessageHandler handler = new AuctionMarketHTTPMessageHandler(auctionMarket);
		String server_port__string = System.getProperty(AuctionMarketConstants.PROPERTY_KEY_SERVER_PORT);
		if(server_port__string != null){
			try{
				listen_on_port = Integer.parseInt(server_port__string);
			} catch(NumberFormatException ex){
				System.err.println(ex);
			}
		}
		if(AuctionMarketHTTPServerUtility.createServer(listen_on_port, handler)){
			;
		}
	}

}
