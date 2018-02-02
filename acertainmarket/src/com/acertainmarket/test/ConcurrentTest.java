package com.acertainmarket.test;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.acertainmarket.AuctionMarket;
import com.acertainmarket.AuctionMarketHTTPProxy;
import com.acertainmarket.Bid;
import com.acertainmarket.CertainAuctionMarket;
import com.acertainmarket.Item;
import com.acertainmarket.utils.AuctionMarketConstants;
import com.acertainmarket.utils.AuctionMarketException;

public class ConcurrentTest {
	
	private static final Integer TEST_ITEMID = 100;
	private static final Integer TEST_SELLERID = 1;
	private static boolean localTest = true;
	
	private static final int NUM = 500;
	private static AuctionMarket client;
	
	//private static Random random = new Random();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try{
			String localTestProperty = System.getProperty(AuctionMarketConstants.PROPERTY_KEY_LOCAL_TEST);
			localTest = (localTestProperty != null) ? Boolean.parseBoolean(localTestProperty) : localTest;
			if(localTest){
				CertainAuctionMarket market = new CertainAuctionMarket();
				client = market;	
			} else{
				client = new AuctionMarketHTTPProxy("http://localhost:8081");
			}
			client.switchEpoch();
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		client.switchEpoch();
		if(!localTest){
			((AuctionMarketHTTPProxy) client).stop();
		}
	}
	
	//public Item getDefaultItem(){
	//	return new Item(TEST_ITEMID, "The first item", TEST_SELLERID);
	//}
	
	public Map<?, ?> readfile() throws ClassNotFoundException{
		try{
			FileInputStream fileIn = new FileInputStream("./Results.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			Object o = in.readObject();
			in.close();
			fileIn.close();
			if(o instanceof Map<?, ?>){
				Map<?, ?> tmp = (Map<?, ?>) o;
				return tmp;
			}
		} catch(IOException e){
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	/**
     * Method to add a book, executed before every test case is run
	 * @throws AuctionMarketException 
     */
	@Before
	public void initializaItems() throws AuctionMarketException{
		Set<Item> itemsToAdd = new HashSet<Item>();
		itemsToAdd.add(new Item(1, "The first item", TEST_SELLERID));
		//itemsToAdd.add(new Item(5001, "The 2nd item", TEST_SELLERID + 5001));
		//itemsToAdd.add(new Item(5002, "The 3rd item", TEST_SELLERID + 5002));
		
		Set<Bid> bidsToAdd = new HashSet<Bid>();
		bidsToAdd.add(new Bid(1, 1, 500));
		//bidsToAdd.add(new Bid(5002, 5001, 200));
		//bidsToAdd.add(new Bid(5003, 5002, 300));
		
		client.addItems(itemsToAdd);
		client.bid(bidsToAdd);
	}
	
	@After
	public void cleanup() throws AuctionMarketException{
		client.switchEpoch();
	}
	
	/**
	 * 
	 * @throws InterruptedException 
	 * @throws AuctionMarketException 
	 *
	 */

	@Test
	public void testMultiThreadADDItem() throws InterruptedException, AuctionMarketException{
		//final int numOperations = NUM;
		
		List<Item> itemsPre = client.queryItems();
		
		final Set<Item> itemsToAdd1 = new HashSet<Item>();
		//final Set<Bid> bidsToAdd = new HashSet<Bid>();
		final Set<Item> itemsToAdd2 = new HashSet<Item>();

		
		Thread c1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try{
					for(int i = 1; i< NUM + 1; ++i){
						itemsToAdd1.add(new Item(TEST_ITEMID + i, "description", TEST_SELLERID + i));
					} 
					client.addItems(itemsToAdd1);
				} catch(AuctionMarketException e){
	                System.out.println("thread running 'addItem 1' threw AuctionMarketException");
	                e.printStackTrace();
				}
			}
		});
		
		Thread c2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try{
					for(int i = 1; i< NUM + 1; ++i){
						itemsToAdd2.add(new Item(TEST_ITEMID + 1000 + i, "description", TEST_SELLERID + 1000 + i));
					} 
					client.addItems(itemsToAdd2);
				} catch(AuctionMarketException e){
					System.out.println("thread running 'addItem' threw AuctionMarketException");
	                e.printStackTrace();
				}
			}
		});
		
		c1.start();
		c2.start();
		
		c1.join();
		c2.join();
		
		List<Item> itemsPost = client.queryItems();
		
		assertTrue(itemsPre.size() + 2*NUM == itemsPost.size());
		
	}
	
	
	/**
	 * @throws AuctionMarketException 
	 * @throws InterruptedException 
	 * 
	 * 
	 *
	 */

	@Test
	public void testAddGetItem() throws AuctionMarketException, InterruptedException{
		
		List<Item> itemsPre = client.queryItems();
		
		final Set<Item> itemsToAdd = new HashSet<Item>();
		
		final ArrayList<String> threadErrors = new ArrayList<String>();
		
		Thread c1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try{
					// add two items each time
					for(int i = 1; i< NUM + 1; ++i){
						itemsToAdd.clear();
						itemsToAdd.add(new Item(TEST_ITEMID + 3000 + i, "description", TEST_SELLERID + 3000 + i));
						itemsToAdd.add(new Item(TEST_ITEMID + 2000 + i, "description", TEST_SELLERID + 2000 + i));
						client.addItems(itemsToAdd);    
					} 
				} catch(AuctionMarketException e){
					threadErrors.add("error");
					e.printStackTrace();
				}
			}
		});
		
		Thread c2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try{
					for(int i = 1; i < NUM + 1; ++i){
						List<Item> itemsNow = client.queryItems();
						if(itemsNow.size() % 2 != 1){
							fail();
						}
					}
				} catch(AuctionMarketException e){
					threadErrors.add("error");
					e.printStackTrace();
				}
			}
		});
		
		c1.start();
		c2.start();
		
		c1.join();
		c2.join();
		
		List<Item> itemsPost = client.queryItems();
		assertTrue(itemsPre.size() + 2*NUM == itemsPost.size());
		assertTrue(threadErrors.size() == 0);
		
	}
	
	
	
}
