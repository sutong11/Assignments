package com.acertainmarket.workloads;

import com.acertainmarket.AuctionMarket;

public class WorkloadConfiguration {

	private float percentFrequentSellerInteraction = 40f;
	private AuctionMarket auctionMarket = null;
	private ItemSetGenerator itemSetGenerator = null;
	private int numItemsToAdd = 5;
	private int TopNumItems = 10;
	private int numItemsToBid = 5;
	private int warmUpRuns = 50;
	private int numActualRuns = 50;
	
	public WorkloadConfiguration(AuctionMarket auctionMarket){
		itemSetGenerator = new ItemSetGenerator();
		this.auctionMarket = auctionMarket;
	}

	public float getPercentFrequentSellerInteraction() {
		return percentFrequentSellerInteraction;
	}
	
	public void getPercentFrequentSellerInteraction(float percentFrequentSellerInteraction) {
		this.percentFrequentSellerInteraction = percentFrequentSellerInteraction;
	}

	public AuctionMarket getAuctionMarket() {
		return auctionMarket;
	}
	
	public void getAuctionMarket(AuctionMarket auctionMarket) {
		this.auctionMarket = auctionMarket;
	}

	public ItemSetGenerator getItemSetGenerator() {
		return itemSetGenerator;
	}
	
	public void setItemSetGenerator(ItemSetGenerator itemSetGenerator) {
		this.itemSetGenerator = itemSetGenerator;
	}

	public int getNumItemsToAdd() {
		return numItemsToAdd;
	}
	
	public void setNumItemsToAdd(int numItemsToAdd) {
		this.numItemsToAdd = numItemsToAdd;
	}

	public int getTopNumItems() {
		return TopNumItems;
	}
	
	public void setTopNumItems(int TopNumItems) {
		this.TopNumItems = TopNumItems;
	}

	public int getNumItemsToBid() {
		return numItemsToBid;
	}
	
	public void setNumItemsToBid(int numItemsToBid) {
		this.numItemsToBid = numItemsToBid;
	}

	public int getWarmUpRuns() {
		return warmUpRuns;
	}
	
	public void setWarmUpRuns(int warmUpRuns) {
		this.warmUpRuns = warmUpRuns;
	}

	public int getNumActualRuns() {
		return numActualRuns;
	}
	
	public void setNumActualRuns(int numActualRuns) {
		this.numActualRuns = numActualRuns;
	}

}
