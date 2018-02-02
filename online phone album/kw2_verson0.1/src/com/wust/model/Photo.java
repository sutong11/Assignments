package com.wust.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Photo entity. @author MyEclipse Persistence Tools
 */

public class Photo implements java.io.Serializable {

	// Fields

	private Integer photoId;
	private Album album;
	private String photoName;
	private String photoUrl;
	private String photoInfo;
	private Date photoDate;
	private Set comments = new HashSet(0);

	// Constructors

	/** default constructor */
	public Photo() {
	}

	/** minimal constructor */
	public Photo(Album album, String photoName, String photoUrl, Date photoDate) {
		this.album = album;
		this.photoName = photoName;
		this.photoUrl = photoUrl;
		this.photoDate = photoDate;
	}

	/** full constructor */
	public Photo(Album album, String photoName, String photoUrl,
			String photoInfo, Date photoDate, Set comments) {
		this.album = album;
		this.photoName = photoName;
		this.photoUrl = photoUrl;
		this.photoInfo = photoInfo;
		this.photoDate = photoDate;
		this.comments = comments;
	}

	// Property accessors

	public Integer getPhotoId() {
		return this.photoId;
	}

	public void setPhotoId(Integer photoId) {
		this.photoId = photoId;
	}

	public Album getAlbum() {
		return this.album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public String getPhotoName() {
		return this.photoName;
	}

	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

	public String getPhotoUrl() {
		return this.photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public String getPhotoInfo() {
		return this.photoInfo;
	}

	public void setPhotoInfo(String photoInfo) {
		this.photoInfo = photoInfo;
	}

	public Date getPhotoDate() {
		return this.photoDate;
	}

	public void setPhotoDate(Date photoDate) {
		this.photoDate = photoDate;
	}

	public Set getComments() {
		return this.comments;
	}

	public void setComments(Set comments) {
		this.comments = comments;
	}

}