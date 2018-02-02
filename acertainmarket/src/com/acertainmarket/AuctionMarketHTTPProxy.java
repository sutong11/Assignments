package com.acertainmarket;

import java.util.List;
import java.util.Set;

import org.eclipse.jetty.client.ContentExchange;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.io.Buffer;
import org.eclipse.jetty.io.ByteArrayBuffer;
import org.eclipse.jetty.util.thread.QueuedThreadPool;

import com.acertainmarket.utils.AuctionMarketConstants;
import com.acertainmarket.utils.AuctionMarketException;
import com.acertainmarket.utils.AuctionMarketMessageTag;
import com.acertainmarket.utils.AuctionMarketUtility;

public class AuctionMarketHTTPProxy implements AuctionMarket{
	protected HttpClient client;
	protected String serverAddress;

	/**
	 * @param args
	 * @throws Exception 
	 */
	public AuctionMarketHTTPProxy(String serverAddress) throws Exception{
		setServerAddress(serverAddress);
		client = new HttpClient();
		client.setConnectorType(HttpClient.CONNECTOR_SELECT_CHANNEL);
		client.setMaxConnectionsPerAddress(AuctionMarketConstants.CLIENT_MAX_CONNECTION_ADDRESS);
		client.setThreadPool(new QueuedThreadPool(AuctionMarketConstants.CLIENT_MAX_THREADSPOOL_THREADS));
		client.setTimeout(AuctionMarketConstants.CLIENT_MAX_TIMEOUT_MILLISECS);
		
		client.start();
	}
	
	public void setServerAddress(String serverAddress){
		this.serverAddress = serverAddress;
	}
	
	

	@Override
	public void addItems(Set<Item> items) throws AuctionMarketException {
		ContentExchange exchange = new ContentExchange();
		String urlString;
		urlString = serverAddress + "/" + AuctionMarketMessageTag.ADDITEMS;
		
		String listItemsxmlString = AuctionMarketUtility.serializeObjectToXMLString(items);
		exchange.setMethod("POST");
		exchange.setURL(urlString);
		Buffer requestContent = new ByteArrayBuffer(listItemsxmlString);
		exchange.setRequestContent(requestContent);
		
		AuctionMarketUtility.SendAndRecv(this.client, exchange);
	}

	@SuppressWarnings("unchecked")
	public List<Item> queryItems() throws AuctionMarketException {
		ContentExchange exchange = new ContentExchange();
		String usString = serverAddress + "/" + AuctionMarketMessageTag.QUERYITEMS;
		
		exchange.setURL(usString);
	
		return (List<Item>) AuctionMarketUtility.SendAndRecv(this.client, exchange);
	}

	@Override
	public void bid(Set<Bid> bids) throws AuctionMarketException {
		ContentExchange exchange = new ContentExchange();
		String urlString;
		urlString = serverAddress + "/" + AuctionMarketMessageTag.BID;
		
		String listItemsxmlString = AuctionMarketUtility.serializeObjectToXMLString(bids);
		exchange.setMethod("POST");
		exchange.setURL(urlString);
		Buffer requestContent = new ByteArrayBuffer(listItemsxmlString);
		exchange.setRequestContent(requestContent);
		
		AuctionMarketUtility.SendAndRecv(this.client, exchange);
	
	}
	
	public void stop() {
		try {
			client.stop();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Override
	public void switchEpoch() throws AuctionMarketException {
		ContentExchange exchange = new ContentExchange();
		String urlString = serverAddress + "/" + AuctionMarketMessageTag.SWITCHEPOCH;
		
		String test = "test";
		exchange.setMethod("POST");
		exchange.setURL(urlString);
		Buffer requestContent = new ByteArrayBuffer(test);
		exchange.setRequestContent(requestContent);
		AuctionMarketUtility.SendAndRecv(this.client, exchange);
	}


}
