package com.acertainmarket.utils;

public class AuctionMarketException extends Exception {
	private static final long serialVersionUID = 1L;

	public AuctionMarketException() {
		super();
	}

	public AuctionMarketException(String message) {
		super(message);
	}

	public AuctionMarketException(String message, Throwable cause) {
		super(message, cause);
	}

	public AuctionMarketException(Throwable ex) {
		super(ex);
	}
}
