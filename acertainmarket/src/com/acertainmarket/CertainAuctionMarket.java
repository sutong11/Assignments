package com.acertainmarket;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.acertainmarket.utils.AuctionMarketConstants;
import com.acertainmarket.utils.AuctionMarketException;
import com.acertainmarket.utils.AuctionMarketUtility;

public class CertainAuctionMarket implements AuctionMarket {

	private Map<Integer, Item> itemMap;
	private Map<Integer, Map<Integer, Bid>> bidMap;
	
	private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	private final Lock r = rwl.readLock();
	private final Lock w = rwl.writeLock();
	
	public CertainAuctionMarket() {
		itemMap = new HashMap<Integer, Item>();
		bidMap = new HashMap<Integer, Map<Integer, Bid>>();
	}
	
	@Override
	public void addItems(Set<Item> items) throws AuctionMarketException {
		
		if(items == null){
			throw new AuctionMarketException(AuctionMarketConstants.NULL_INPUT);
		}
		
		w.lock();
		try{
			// check if all are there
			for(Item i : items){
				int itemID = i.getItemID();
				String itemDescription = i.getItemDescription();
				int sellerOrganizationID = i.getSellerOrganizationID();
				
				if(AuctionMarketUtility.isInvalidItemID(itemID)
						|| AuctionMarketUtility.isEmpty(itemDescription)
						|| AuctionMarketUtility.isInvalidsellerOrganizationID(sellerOrganizationID)){
					throw new AuctionMarketException(AuctionMarketConstants.ITEM
							+ i.toString() + AuctionMarketConstants.INVALID);
				}else if(itemMap.containsKey(itemID)){
					throw new AuctionMarketException(AuctionMarketConstants.ITEM + itemID
							+ AuctionMarketConstants.DUPLICATED);
				}
			}
			
			for(Item i : items){
				int itemID = i.getItemID();
				itemMap.put(itemID, i);
				
			}
		} finally {
			w.unlock();
		}
	}

	@Override
	public List<Item> queryItems() throws AuctionMarketException {
		List<Item> listItems = new ArrayList<Item>();
		r.lock();
		try{
			Collection<Item> itemMapValues = itemMap.values();
			for(Item i : itemMapValues){
				//listItems.add(new Item(i));
				listItems.add(i);
			}
		} finally{
			r.unlock();
		}
		return listItems;
	}

	@Override
	public void bid(Set<Bid> bids) throws AuctionMarketException {
		if(bids == null)
			throw new AuctionMarketException(AuctionMarketConstants.NULL_INPUT);
		
		int itemID;
		int buyerOrganizationID;
		float bidAmount;
		//Map<Integer, Bid> subbidMap = new HashMap<Integer, Bid>();
		
		w.lock();
		
		try{
			for(Bid b : bids){
				itemID = b.getItemID();
				buyerOrganizationID = b.getBuyerOrganizationID();
				bidAmount = b.getBidAmount();
				
				if(AuctionMarketUtility.isInvalidItemID(itemID)
						|| AuctionMarketUtility.isInvalidbuyerOrganizationID(buyerOrganizationID)
						|| bidAmount < 0.0)
					throw new AuctionMarketException(AuctionMarketConstants.BID
							+ b.toString() + AuctionMarketConstants.INVALID);
				if(!itemMap.containsKey(itemID))
					throw new AuctionMarketException(AuctionMarketConstants.ITEM
							+ itemID + AuctionMarketConstants.NOT_AVAILABLE);
			}
			
			for(Bid b: bids){
				itemID = b.getItemID();
				buyerOrganizationID =b.getBuyerOrganizationID();
				
				Set<Integer> itemIDs = bidMap.keySet();
				if(itemIDs.contains(itemID)){
					Map<Integer, Bid> subbidMap = bidMap.get(itemID);
					subbidMap.put(buyerOrganizationID, b);
					bidMap.put(itemID, subbidMap);
					/*Set<Integer> buyerOrganizationIDs = subbidMap.keySet();
					if(buyerOrganizationIDs.contains(buyerOrganizationID)){
						//subbidMap.remove(buyerOrganizationID);
						subbidMap.put(buyerOrganizationID, b);
						bidMap.put(itemID, subbidMap);
					}else{
						subbidMap.put(buyerOrganizationID, b);
						bidMap.put(itemID, subbidMap);
					}*/
				}else{
					Map<Integer, Bid> subbidMap = new HashMap<Integer, Bid>();
					subbidMap.put(buyerOrganizationID, b);
					bidMap.put(itemID, subbidMap);
				}
			}
		} finally{
			w.unlock();
		}
	}
	

	@Override
	public void switchEpoch() throws AuctionMarketException {
		
		Map<Item, Bid> Results = new HashMap<Item, Bid>(); 
		
		w.lock();
		try{
			//Map<Integer, Bid> subbidMap = new HashMap<Integer, Bid>();
			
			for(Map.Entry<Integer, Map<Integer, Bid>> entry : bidMap.entrySet()){
				float bestBid = -1;
				Bid bid = null;
				int itemID = entry.getKey();
				Item i = itemMap.get(itemID);
				//entry.getValue();
				for(Map.Entry<Integer, Bid> en : entry.getValue().entrySet()){
					float v = en.getValue().getBidAmount();
					if(v > bestBid){
						bestBid = v;
						bid = en.getValue();
					}
					
				}
				Results.put(i, bid);
			}
			/*Collection<Item> itemMapValues = itemMap.values();
			for(Item i : itemMapValues){
				subbidMap = bidMap.get(i.getItemID());
				if (subbidMap == null){
					
				} else{
					Collection<Bid> bidsValues = subbidMap.values();
					for(Bid b : bidsValues){
						if(b.getBidAmount() > bestBid){
							bestBid = b.getBidAmount();
							bid = b;
						}
					}
				}
				Results.put(i, bid);
			}*/
			
			try{
				FileOutputStream fileOut = new FileOutputStream("./Results.ser");
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(Results);
				out.close();
				fileOut.close();
			} catch(IOException e){
				e.printStackTrace();
			}
			
			itemMap.clear();
			bidMap.clear();
			
		} finally{
			w.unlock();
		}
	}

}
