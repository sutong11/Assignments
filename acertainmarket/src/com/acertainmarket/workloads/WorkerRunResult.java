package com.acertainmarket.workloads;

public class WorkerRunResult {
	private int successfulInteractions; 
	private int totalRuns; 
	private long elapsedTimeInNanoSecs; 					
	private int successfulFrequentBuyerInteractionRuns; 
	private int totalFrequentBuyerInteractionRuns;
	
	public WorkerRunResult(int successfulInteractions, long elapsedTimeInNanoSecs,
			int totalRuns, int successfulFrequentBuyerInteractionRuns,
			int totalFrequentBuyerInteractionRuns) {
		this.setSuccessfulInteractions(successfulInteractions);
		this.setElapsedTimeInNanoSecs(elapsedTimeInNanoSecs);
		this.setTotalRuns(totalRuns);
		this.setSuccessfulFrequentBuyerInteractionRuns(successfulFrequentBuyerInteractionRuns);
		this.setTotalFrequentBuyerInteractionRuns(totalFrequentBuyerInteractionRuns);
	}
	
	public int getTotalRuns() {
		return totalRuns;
	}

	public void setTotalRuns(int totalRuns) {
		this.totalRuns = totalRuns;
	}

	public long getElapsedTimeInNanoSecs() {
		return elapsedTimeInNanoSecs;
	}

	public void setElapsedTimeInNanoSecs(long elapsedTimeInNanoSecs) {
		this.elapsedTimeInNanoSecs = elapsedTimeInNanoSecs;
	}

	private void setSuccessfulInteractions(int successfulInteractions) {
		this.successfulInteractions = successfulInteractions;
	}
	
	public int getSuccessfulInteractions() {
		return successfulInteractions;
	}
	
	public int getSuccessfulFrequentBuyerInteractionRuns() {
		return successfulFrequentBuyerInteractionRuns;
	}

	public void setSuccessfulFrequentBuyerInteractionRuns(
			int successfulFrequentBuyerInteractionRuns) {
		this.successfulFrequentBuyerInteractionRuns = successfulFrequentBuyerInteractionRuns;
	}
	
	public int getTotalFrequentBuyerInteractionRuns() {
		return totalFrequentBuyerInteractionRuns;
	}

	public void setTotalFrequentBuyerInteractionRuns(
			int totalFrequentBuyerInteractionRuns) {
		this.totalFrequentBuyerInteractionRuns = totalFrequentBuyerInteractionRuns;
	}

}
