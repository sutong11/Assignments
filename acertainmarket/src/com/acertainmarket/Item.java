package com.acertainmarket;

import java.io.Serializable;

/**
 * An item is a good to be traded in the auction market. It consists of an item
 * identifier, an item description, and a seller organization identifier. For
 * example, an item could have ID 42, description "Peanut butter, 42Kg", and a
 * seller organization ID equal to 84. An item is identified by the item ID.
 *
 * @author vmarcos
 */
public final class Item implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The ID of the item.
	 */
	private final int itemID;

	/**
	 * The description of the item.
	 */
	private final String itemDescription;

	/**
	 * The ID of the organization selling the item.
	 */
	private final int sellerOrganizationID;

	/**
	 * Constructs a new item with the given information.
	 */
	public Item(int itemID, String itemDescription, int sellerOrganizationID) {
		this.itemID = itemID;
		this.itemDescription = itemDescription;
		this.sellerOrganizationID = sellerOrganizationID;
	}
	
	//public Item(Item i){
	//	this.itemID = i.getItemID();
	//	this.itemDescription = i.getItemDescription();
	//	this.sellerOrganizationID = i.getSellerOrganizationID();
	//}
	
	public Item immutableItem(){
		return new Item(this.getItemID(), this.getItemDescription(), this.getSellerOrganizationID());
	}
	/**
	 * @return the itemID
	 */
	public final int getItemID() {
		return itemID;
	}

	/**
	 * @return the itemDescription
	 */
	public final String getItemDescription() {
		return itemDescription;
	}

	/**
	 * @return the sellerOrganizationID
	 */
	public final int getSellerOrganizationID() {
		return sellerOrganizationID;
	}
	
	public boolean equals(Object obj){
		if(!(obj instanceof Item))
			return false;
		Item item = (Item) obj;
		return (this.itemID == item.getItemID()
				&& this.getItemDescription().equals(item.getItemDescription())
				&& this.getSellerOrganizationID() == item.getSellerOrganizationID());
	}
	
	public String toString(){
		String itemString = "ItemID = " + this.getItemID() +" Description = "
				+ this.getItemDescription() + " SellerOrganizationID = " + this.getSellerOrganizationID();
		return itemString;
	}
	
	public int hashCode(){
		return getItemID();
	}

	// TODO: you may add methods here required for any data structures you may
	// need to use, e.g., hashCode or equals.

}
