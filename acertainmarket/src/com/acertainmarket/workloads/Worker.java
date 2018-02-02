package com.acertainmarket.workloads;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Callable;

import com.acertainmarket.AuctionMarket;
import com.acertainmarket.Bid;
import com.acertainmarket.Item;
import com.acertainmarket.utils.AuctionMarketException;

public class Worker implements Callable<WorkerRunResult>{
	private WorkloadConfiguration configuration = null;
	private int numSuccessfulFrequentBuyerInteraction = 0;
	private int numTotalFrequentBuyerInteraction = 0;
	
	
	public Worker(WorkloadConfiguration config) {
		configuration = config;
	}
	
	private boolean runInteraction(float chooseInteraction){
		try{
			if(chooseInteraction < configuration.getPercentFrequentSellerInteraction()){
				runFrequentSellerInteraction();
			} else {
				numTotalFrequentBuyerInteraction++;
				runFrequentBuyerInteraction();
				numSuccessfulFrequentBuyerInteraction++;
			}
		} catch(AuctionMarketException ex){
			return false;
		}
		return true;
	}

	private void runFrequentBuyerInteraction() throws AuctionMarketException {
		AuctionMarket auctionMarket = configuration.getAuctionMarket();
		ItemSetGenerator itemSetGenerator = configuration.getItemSetGenerator();
		
		List<Item> items = auctionMarket.queryItems();
		Collections.shuffle(items);
		
		int j = 0;
		Set<Integer> itemIDs = new HashSet<>();
		
		for(Item i : items){
			if(j < configuration.getTopNumItems()){
				itemIDs.add(i.getItemID());
				j += 1;
			} else{
				break;
			}
		}
		
		// select a sample if these itemIDs to bid
		Set<Integer> itemIDsToBid = itemSetGenerator.sampleFromSetOfItemIDs(itemIDs, configuration.getNumItemsToBid());
		
		//
		Set<Bid> bids = new HashSet<Bid>();
		for(Integer itemid : itemIDsToBid){
			bids.add(new Bid(100, itemid, 300));
		}
		
		auctionMarket.bid(bids);
	}

	private void runFrequentSellerInteraction() throws AuctionMarketException {
		AuctionMarket auctionMarket = configuration.getAuctionMarket();
		ItemSetGenerator itemSetGenerator = configuration.getItemSetGenerator();
		
		// get list of items and extract set of itemIDs
		List<Item> items  = auctionMarket.queryItems();
		Set<Integer> itemIDs = new HashSet<>();
		for(Item i : items){
			itemIDs.add(i.getItemID());
		}
		
		// get a random set of items
		Set<Item> newItems = itemSetGenerator.nextSetOfItems(configuration.getNumItemsToAdd());
		
		// find subset of items not already exist
		for(Item i : newItems){
			if(itemIDs.contains(i.getItemID())){
				newItems.remove(i);
			}
		}
		
		// add new items
		auctionMarket.addItems(newItems);
	}

	@Override
	public WorkerRunResult call() throws Exception {
		int count = 1;
		long startTimeInNanoSecs = 0;
		long endTimeInNanoSecs = 0;
		int successfulInteractions = 0;
		long timeForRunsInNanoSecs = 0;
		
		Random rand = new Random();
		float chooseInteraction;
		
		// Perform the warmup runs
		while (count++ <= configuration.getWarmUpRuns()) {
			chooseInteraction = rand.nextFloat() * 100f;
			runInteraction(chooseInteraction);
		}
		
		count = 1;
		numTotalFrequentBuyerInteraction = 0;
		numSuccessfulFrequentBuyerInteraction = 0;
		
		// Perform the actual runs
		startTimeInNanoSecs = System.nanoTime();
		while (count++ <= configuration.getNumActualRuns()) {
			chooseInteraction = rand.nextFloat() * 100f;
			if (runInteraction(chooseInteraction)) {
				successfulInteractions++;
			}
		}
		
		endTimeInNanoSecs = System.nanoTime();
		timeForRunsInNanoSecs += (endTimeInNanoSecs - startTimeInNanoSecs);
		return new WorkerRunResult(successfulInteractions,
				timeForRunsInNanoSecs, configuration.getNumActualRuns(),
				numSuccessfulFrequentBuyerInteraction,
				numTotalFrequentBuyerInteraction);
	
	}
	

}
