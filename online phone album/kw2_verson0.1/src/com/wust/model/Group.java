package com.wust.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Group entity. @author MyEclipse Persistence Tools
 */

public class Group implements java.io.Serializable {

	// Fields

	private Integer groupId;
	private User user;
	private String groupName;
	private Set friends = new HashSet(0);

	// Constructors

	/** default constructor */
	public Group() {
	}

	/** minimal constructor */
	public Group(User user, String groupName) {
		this.user = user;
		this.groupName = groupName;
	}

	/** full constructor */
	public Group(User user, String groupName, Set friends) {
		this.user = user;
		this.groupName = groupName;
		this.friends = friends;
	}

	// Property accessors

	public Integer getGroupId() {
		return this.groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Set getFriends() {
		return this.friends;
	}

	public void setFriends(Set friends) {
		this.friends = friends;
	}

}