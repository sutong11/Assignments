package com.wust.model;

import java.util.HashSet;
import java.util.Set;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	// Fields

	private Integer userId;
	private String userName;
	private String password;
	private String email;
	private String phone;
	private String signnature;
	private Set albums = new HashSet(0);
	private Set groups = new HashSet(0);
	private Set friends = new HashSet(0);
	private Set comments = new HashSet(0);

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	/** full constructor */
	public User(String userName, String password, String email, String phone,
			String signnature, Set albums, Set groups, Set friends, Set comments) {
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.signnature = signnature;
		this.albums = albums;
		this.groups = groups;
		this.friends = friends;
		this.comments = comments;
	}

	// Property accessors

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSignnature() {
		return this.signnature;
	}

	public void setSignnature(String signnature) {
		this.signnature = signnature;
	}

	public Set getAlbums() {
		return this.albums;
	}

	public void setAlbums(Set albums) {
		this.albums = albums;
	}

	public Set getGroups() {
		return this.groups;
	}

	public void setGroups(Set groups) {
		this.groups = groups;
	}

	public Set getFriends() {
		return this.friends;
	}

	public void setFriends(Set friends) {
		this.friends = friends;
	}

	public Set getComments() {
		return this.comments;
	}

	public void setComments(Set comments) {
		this.comments = comments;
	}

}