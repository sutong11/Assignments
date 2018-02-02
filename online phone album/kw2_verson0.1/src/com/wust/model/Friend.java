package com.wust.model;

/**
 * Friend entity. @author MyEclipse Persistence Tools
 */

public class Friend implements java.io.Serializable {

	// Fields

	private Integer friendId;
	private Group group;
	private User user;

	// Constructors

	/** default constructor */
	public Friend() {
	}

	/** full constructor */
	public Friend(Integer friendId, Group group, User user) {
		this.friendId = friendId;
		this.group = group;
		this.user = user;
	}

	// Property accessors

	public Integer getFriendId() {
		return this.friendId;
	}

	public void setFriendId(Integer friendId) {
		this.friendId = friendId;
	}

	public Group getGroup() {
		return this.group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}