package com.acertainmarket.workloads;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.acertainmarket.AuctionMarket;
import com.acertainmarket.AuctionMarketHTTPProxy;
import com.acertainmarket.CertainAuctionMarket;
import com.acertainmarket.Item;
import com.acertainmarket.utils.AuctionMarketConstants;
import com.acertainmarket.utils.AuctionMarketException;

public class CertainWorkload {
	// initial number of different items in the market
	private static final int NUM_ITEMS = 500;
	private static final int NUM_THREADS = 30;

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		for(int numThreads = 1; numThreads <= NUM_THREADS; ++numThreads){
			int numConcurrentWorkloadThreads = numThreads;
			String serverAddress = "http://localhost:8081";
			boolean localTest = false;
			List<WorkerRunResult> workerRunResults = new ArrayList<>();
			List<Future<WorkerRunResult>> runResults = new ArrayList<>();
			
			// Initialize the RPC interfaces if its not a localTest, the variable is
			// overriden if the property is set
			String localTestProperty = System.getProperty(AuctionMarketConstants.PROPERTY_KEY_LOCAL_TEST);
			localTest = (localTestProperty != null) ? Boolean.parseBoolean(localTestProperty) : localTest;
			
			AuctionMarket auctionMarket = null;
			if(localTest){
				CertainAuctionMarket market = new CertainAuctionMarket();
				auctionMarket = market;
			} else{
				auctionMarket = new AuctionMarketHTTPProxy(serverAddress);
			}
			
			// Generate data in the auctionmarket before running the workload
			initializeAuctionMarketData(auctionMarket);
			
			ExecutorService exec = Executors.newFixedThreadPool(numConcurrentWorkloadThreads);
			
			for(int i = 0; i < numConcurrentWorkloadThreads; i++){
				WorkloadConfiguration config = new WorkloadConfiguration(auctionMarket);
				Worker workerTask = new Worker(config);
				// Keep the futures to wait for the result from the thread
				runResults.add(exec.submit(workerTask));
			}
			
			// Get the results from the threads using the futures returned
			for (Future<WorkerRunResult> futureRunResult : runResults) {
				WorkerRunResult runResult = futureRunResult.get(); // blocking call
				workerRunResults.add(runResult);
			}
			
			exec.shutdownNow(); // shutdown the executor
			
			// Finished initialization, stop the clients if not localTest
			if(!localTest){
				((AuctionMarketHTTPProxy) auctionMarket).stop();
			}
			
			reportMetric(workerRunResults, numConcurrentWorkloadThreads);
		}
	}
	
	/**
	 * Computes the metrics and prints them.
	 * 
	 */
	public static void reportMetric(List<WorkerRunResult> workerRunResults, int j){
		long totalSuccess=0;
		long totalTime=0;
		double totalTimeMS=0;
		double aggThroughput=0;
		double throughput=0;
		//double latency=0;
		//double totalLatency =0;
		//double avgLatency=0;
		long totalSuccessfulInteractions=0;
		long totalInteractions=0 ;
		double goodputRatio=0;
		long totalTotalSuccess=0;
		double customerInteractionRatio=0;
		
		for (WorkerRunResult wResult : workerRunResults){
			// gather data
			totalSuccess = wResult.getSuccessfulFrequentBuyerInteractionRuns();
			totalTime = wResult.getElapsedTimeInNanoSecs();
			totalTimeMS  = (double)totalTime/(1_000_000);
			
			// calculate throughput
			throughput = totalSuccess/totalTimeMS;
			aggThroughput += throughput;
			
			// calculate latency
			//latency = totalTimeMS/totalSuccess;			
			//totalLatency  += latency;
			
			// calculate interaction values
			totalSuccessfulInteractions += wResult.getSuccessfulInteractions();
			totalInteractions += wResult.getTotalRuns();
			totalTotalSuccess+=totalSuccess ;
		}
		
		// calculate average latency accross all workers
		//avgLatency = totalLatency/workerRunResults.size();
		
		// calculate goodput ratio
		goodputRatio = (double)totalSuccessfulInteractions / totalInteractions;
		
		// calculate customer interaction ratio
		customerInteractionRatio = (double)totalTotalSuccess / totalInteractions;
		
		// throughput is Transactions/time in ms
		// latency is time in ms
		//System.out.println(j + " " + aggThroughput + " " + avgLatency + " " + goodputRatio + " " + customerInteractionRatio);
		System.out.println(j + " " + aggThroughput + " " + goodputRatio + " " + customerInteractionRatio);
				
	}
	
	/**
	 * Generate the data in auctionmarket before the workload interactions are run.
	 *
	 * Ignores the serverAddress if it's a localTest.
	 * @throws AuctionMarketException 
	 */

	private static void initializeAuctionMarketData(AuctionMarket auctionMarket) throws AuctionMarketException {
		ItemSetGenerator isg = new ItemSetGenerator();
		Set<Item> itemSet = isg.nextSetOfItems(NUM_ITEMS);
		auctionMarket.addItems(itemSet);
	}

}
