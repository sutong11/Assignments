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

public class AuctionTest {
	
	private static final Integer TEST_ITEMID = 100;
	private static final Integer TEST_SELLERID = 1;
	//private static final Integer TEST_BUYERID = 1;
	private static boolean localTest = true;
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
	
	public Item getDefaultItem(){
		return new Item(TEST_ITEMID, "The first item", TEST_SELLERID);
	}
	
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
		itemsToAdd.add(getDefaultItem());
		client.addItems(itemsToAdd);
	}
	
	@After
	public void cleanup() throws AuctionMarketException{
		client.switchEpoch();
	}

	/**
	 * Checks whether the insertion of items with initialize item worked.
	 */
	@Test
	public void testInitializeItems() throws AuctionMarketException {
		List<Item> addedItems = new ArrayList<Item>();
		addedItems.add(getDefaultItem());
		List<Item> listItems = null;
		listItems = client.queryItems();
		assertTrue(addedItems.containsAll(listItems)
				&& addedItems.size() == listItems.size());
	}
	
	/**
	 * Checks whether an insertion of a items with an invalid ItemID is rejected
	 * 
	 * Also verify All or Nothing
	 * 
	 * @throws AuctionMarketException 
	 */
	@Test
	public void testaddItemInvalidItemID() throws AuctionMarketException{
		List<Item> itemsPre = client.queryItems();
		
		Set<Item> itemsToAdd = new HashSet<Item>();
		itemsToAdd.add(new Item(TEST_ITEMID + 1, "add item 1", TEST_SELLERID + 1));  //valid
		itemsToAdd.add(new Item(-1, "add item 2", TEST_SELLERID + 1));  //invalid
		
		try{
			client.addItems(itemsToAdd);
			fail();
		} catch(AuctionMarketException ex){
			;
		}
		List<Item> itemsPost = client.queryItems();
		assertTrue(itemsPre.containsAll(itemsPost)
				&& itemsPre.size() == itemsPost.size());
	}
	
	/**
	 * Checks whether an insertion of a item with an invalid SellerID is rejected
	 * 
	 * Also verify All or Nothing
	 * 
	 * @throws AuctionMarketException 
	 * 
	 */
	@Test
	public void testaddItemInvalidSellerID() throws AuctionMarketException {
		List<Item> itemsPre = client.queryItems();
		
		Set<Item> itemsToAdd = new HashSet<Item>();
		itemsToAdd.add(new Item(TEST_ITEMID + 1, "add item 1", TEST_SELLERID + 1));  //valid
		itemsToAdd.add(new Item(TEST_ITEMID + 1, "add item 2", -1));  //invalid
		
		try{
			client.addItems(itemsToAdd);
			fail();
		} catch(AuctionMarketException ex){
			;
		}
		List<Item> itemsPost = client.queryItems();
		assertTrue(itemsPre.containsAll(itemsPost)
				&& itemsPre.size() == itemsPost.size());
	}
	
	/**
	 * Tests adding items with correct parameters and add a correct bid 
	 * @throws ClassNotFoundException 
	 */
	@Test
	public void testCorrectItemBid() throws AuctionMarketException, ClassNotFoundException {
		List<Item> itemsPre = client.queryItems();
		
		Set<Item> itemsToAdd = new HashSet<Item>();
		itemsToAdd.add(new Item(TEST_ITEMID + 1, "add item 1", TEST_SELLERID + 1));  //valid
		itemsToAdd.add(new Item(TEST_ITEMID + 2, "add item 2", TEST_SELLERID + 2));  //valid
		
		Set<Bid> bids = new HashSet<Bid>();
		bids.add(new Bid(2, 101, 400));
		bids.add(new Bid(1, 102, 200));
		bids.add(new Bid(1, 101, 600));
		try{
			client.addItems(itemsToAdd);
			client.bid(bids);
		} catch(AuctionMarketException ex){
			;
		}
		List<Item> itemsPost = client.queryItems();
		
		client.switchEpoch();
		Map<Item, Bid> ib = (Map<Item, Bid>)readfile();
		for(Item i : ib.keySet()){
			System.out.println(ib.get(i).getBidAmount());
		}
		assertTrue(itemsPre.size()+2 == itemsPost.size());
	}
	
	
	
}
