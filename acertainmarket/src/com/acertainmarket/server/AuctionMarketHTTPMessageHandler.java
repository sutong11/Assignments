package com.acertainmarket.server;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import com.acertainmarket.Bid;
import com.acertainmarket.CertainAuctionMarket;
import com.acertainmarket.Item;
import com.acertainmarket.utils.AuctionMarketException;
import com.acertainmarket.utils.AuctionMarketMessageTag;
import com.acertainmarket.utils.AuctionMarketResponse;
import com.acertainmarket.utils.AuctionMarketUtility;

public class AuctionMarketHTTPMessageHandler extends AbstractHandler{
	
	private CertainAuctionMarket myAuctionMarket = null;
	
	public AuctionMarketHTTPMessageHandler(CertainAuctionMarket auctionMarket){
		myAuctionMarket = auctionMarket;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void handle(String target, Request baseRequest, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		AuctionMarketMessageTag messageTag;
		
		String requestURI;
		AuctionMarketResponse auctionMarketReponse = null;
		
		response.setContentType("text/html;charset=utf-8");
		response.setStatus(HttpServletResponse.SC_OK);
		requestURI = request.getRequestURI();
		
		// Need to do request multi-plexing
		if(!AuctionMarketUtility.isEmpty(requestURI)
				&& requestURI.toLowerCase().startsWith("/stock")){
			messageTag = AuctionMarketUtility.convertURItoMessageTag(requestURI.substring(6));
		} else{
			messageTag = AuctionMarketUtility.convertURItoMessageTag(requestURI);
		}
		
		// the RequestURI before the switch
		if(messageTag == null){
			System.out.println("Unknown message tag");
		}else{
			switch(messageTag){
			case ADDITEMS:
				String xml = AuctionMarketUtility.extractPOSTDataFromRequest(request);
				Set<Item> newItems = (Set<Item>) AuctionMarketUtility.deserializeXMLStringToObject(xml);
				
				auctionMarketReponse = new AuctionMarketResponse();
				try{
					myAuctionMarket.addItems(newItems);
				} catch(AuctionMarketException ex){
					auctionMarketReponse.setException(ex);
				}
				String listAuctionsxmlString = AuctionMarketUtility.serializeObjectToXMLString(auctionMarketReponse);
				response.getWriter().println(listAuctionsxmlString);
				break;
				
			case QUERYITEMS:
				auctionMarketReponse = new AuctionMarketResponse();
				try {
					auctionMarketReponse.setList(myAuctionMarket.queryItems());
				} catch (AuctionMarketException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				listAuctionsxmlString = AuctionMarketUtility.serializeObjectToXMLString(auctionMarketReponse);
				response.getWriter().println(listAuctionsxmlString);
				break;
				
			case BID:
				xml = AuctionMarketUtility.extractPOSTDataFromRequest(request);
				
				Set<Bid> newBids = (Set<Bid>) AuctionMarketUtility.deserializeXMLStringToObject(xml);
				
				auctionMarketReponse = new AuctionMarketResponse();
				try{
					myAuctionMarket.bid(newBids);
				} catch(AuctionMarketException ex){
					auctionMarketReponse.setException(ex);
				}
				listAuctionsxmlString = AuctionMarketUtility.serializeObjectToXMLString(auctionMarketReponse);
				response.getWriter().println(listAuctionsxmlString);
				break;
				
			case SWITCHEPOCH:
				xml = AuctionMarketUtility.extractPOSTDataFromRequest(request);
				auctionMarketReponse = new AuctionMarketResponse();
				try{
					myAuctionMarket.switchEpoch();
				} catch(AuctionMarketException ex){
					auctionMarketReponse.setException(ex);
				}
				listAuctionsxmlString = AuctionMarketUtility.serializeObjectToXMLString(auctionMarketReponse);
				response.getWriter().println(listAuctionsxmlString);
				break;
				
			default:
				System.out.println("Unhandled message tag");
			}
		}
		// Mark the request as handled so that the HTTP response can be sent
        baseRequest.setHandled(true);
	}
	

}
