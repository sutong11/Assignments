package com.acertainmarket.utils;

import java.io.IOException;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.jetty.client.ContentExchange;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.HttpExchange;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

public class AuctionMarketUtility {
	
	public static boolean isInvalidItemID(int ItemID){
		return (ItemID < 1);
	}
	
	public static boolean isInvalidsellerOrganizationID(int sellerOrganizationID){
		return (sellerOrganizationID < 1);
	}
	
	public static boolean isEmpty(String str){
		return ((str == null) || str.isEmpty());
	}
	
	/**
	 * Serializes an object to an xml string
	 * 
	 * @param object
	 * @return
	 */
	public static String serializeObjectToXMLString(Object object) {
		String xmlString;
		XStream xmlStream = new XStream(new StaxDriver());
		xmlString = xmlStream.toXML(object);
		return xmlString;
	}
	
	/**
	 * De-serializes an xml string to object
	 * 
	 * @param xmlObject
	 * @return
	 */
	public static Object deserializeXMLStringToObject(String xmlObject) {
		Object dataObject = null;
		XStream xmlStream = new XStream(new StaxDriver());
		dataObject = xmlStream.fromXML(xmlObject);
		return dataObject;
	}
	
	/**
	 * Manages the sending of an exchange through the client, waits for the
	 * response and unpacks the response
	 * 
	 * @param client
	 * @param exchange
	 * @return A List<Book> for a get function, otherwise null
	 * @throws BookStoreException
	 */
	public static List<?> SendAndRecv(HttpClient client,
			ContentExchange exchange) throws AuctionMarketException {
		int exchangeState;
		try {
			client.send(exchange);
		} catch (IOException ex) {
			throw new AuctionMarketException(
					AuctionMarketConstants.strERR_CLIENT_REQUEST_SENDING, ex);
		}

		try {
			exchangeState = exchange.waitForDone(); // block until the response
													// is available
		} catch (InterruptedException ex) {
			throw new AuctionMarketException(
					AuctionMarketConstants.strERR_CLIENT_REQUEST_SENDING, ex);
		}

		if (exchangeState == HttpExchange.STATUS_COMPLETED) {
			try {
				AuctionMarketResponse bookStoreResponse = (AuctionMarketResponse) AuctionMarketUtility
						.deserializeXMLStringToObject(exchange
								.getResponseContent().trim());
				AuctionMarketException ex = bookStoreResponse.getException();
				if (ex != null) {
					throw ex;
				}
				return bookStoreResponse.getList();
				
			} catch (UnsupportedEncodingException ex) {
				throw new AuctionMarketException(
						AuctionMarketConstants.strERR_CLIENT_RESPONSE_DECODING,
						ex);
			}
		} else if (exchangeState == HttpExchange.STATUS_EXCEPTED) {
			throw new AuctionMarketException(
					AuctionMarketConstants.strERR_CLIENT_REQUEST_EXCEPTION);
		} else if (exchangeState == HttpExchange.STATUS_EXPIRED) {
			throw new AuctionMarketException(
					AuctionMarketConstants.strERR_CLIENT_REQUEST_TIMEOUT);
		} else {
			throw new AuctionMarketException(
					AuctionMarketConstants.strERR_CLIENT_UNKNOWN);
		}
	}

	public static boolean isInvalidbuyerOrganizationID(int buyerOrganizationID) {
		return (buyerOrganizationID < 1);
	}

	public static AuctionMarketMessageTag convertURItoMessageTag(
			String requestURI) {
		try{
			AuctionMarketMessageTag messageTag = AuctionMarketMessageTag.valueOf(requestURI.substring(1).toUpperCase());
			return messageTag;
		} catch(IllegalArgumentException ex){
			; // Enum type matching failed so non supported message
		} catch(NullPointerException ex) {
			;  // RequestURI was empty
		}
		return null;
	}

	public static String extractPOSTDataFromRequest(HttpServletRequest request) throws IOException {
		Reader reader = request.getReader();
		int len = request.getContentLength();
		
		// Request must be read into a char[] first
		char res[] = new char[len];
		reader.read(res);
		reader.close();
		return new String(res);
	}

}
