package com.acertainmarket.workloads;

import java.util.*;

import com.acertainmarket.Item;

public class ItemSetGenerator {
	private static final int MAX_ITEMID = Integer.MAX_VALUE;
	private Random rand;
	
	public ItemSetGenerator() {
		rand = new Random();
	}
	
	public Set<Integer> sampleFromSetOfItemIDs(Set<Integer> itemIDs, int num){
		// initialize list of itemIDs and randomly permute list
		ArrayList<Integer> itemidList = new ArrayList<>(itemIDs);
		Collections.shuffle(itemidList);
		
		// select up to first num itemIDs from permutation and return set of these
		Set<Integer> sampleSet = new HashSet<>();
		for(int i = 0; i <num && i < itemidList.size(); ++i)
			sampleSet.add(itemidList.get(i));
		return sampleSet;
	}
	
	public Set<Item> nextSetOfItems(int num){
		Set<Item> items = new HashSet<>();
		Set<Integer> itemids = new HashSet<>();
		
		// generate and add a new item until we have num unique books
		while(items.size() < num){
			int randItemID = rand.nextInt(MAX_ITEMID);
			Item item = new Item(randItemID, "description", randItemID);
			if(itemids.contains(randItemID)){
				continue;
			} else{
				items.add(item);
				itemids.add(randItemID);
			}
		}
		
		return items;
	}
	
}
