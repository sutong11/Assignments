package com.wust.util;

import java.util.List;

public class PageModel {
	private int allLine;// 鎬昏鏁�
	private int allPage;// 鎬婚〉鏁�
	private int prevLine;// 鍓嶄竴椤�
	private int onLine;// 褰撳墠椤�
	private int nextLine;// 涓嬩竴椤�
	private List entityList;// 鏁版嵁灏佽

	public int getAllLine() {
		return allLine;
	}

	public void setAllLine(int allLine) {
		this.allLine = allLine;
	}

	public int getAllPage() {
		return allPage;
	}

	public void setAllPage(int allPage) {
		this.allPage = allPage;
	}

	public int getPrevLine() {
		return prevLine;
	}

	public void setPrevLine(int prevLine) {
		this.prevLine = prevLine;
	}

	public int getOnLine() {
		return onLine;
	}

	public void setOnLine(int onLine) {
		this.onLine = onLine;
	}

	public int getNextLine() {
		return nextLine;
	}

	public void setNextLine(int nextLine) {
		this.nextLine = nextLine;
	}

	public List getEntityList() {
		return entityList;
	}

	public void setEntityList(List entityList) {
		this.entityList = entityList;
	}
}
