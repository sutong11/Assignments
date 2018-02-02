package com.wust.util;

public class DataChart {
	private String typeName;
	private float watchTimes;

	public DataChart() {
		super();
	}

	public DataChart(String typeName, float watchTimes) {
		super();
		this.typeName = typeName;
		this.watchTimes = watchTimes;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public float getWatchTimes() {
		return watchTimes;
	}

	public void setWatchTimes(float watchTimes) {
		this.watchTimes = watchTimes;
	}

}
