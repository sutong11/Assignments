package com.wust.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Album entity. @author MyEclipse Persistence Tools
 */

public class Album implements java.io.Serializable {

	// Fields

	private Integer albumId;
	private User user;
	private String albumName;
	private String albumInfo;
	private Date albumDate;
	private Integer albumUserid;
	private Integer albumAuthority;
	private Integer photoNum;
	private Set photos = new HashSet(0);

	// Constructors

	/** default constructor */
	public Album() {
	}

	/** minimal constructor */
	public Album(User user, String albumName, Date albumDate,
			Integer albumAuthority) {
		this.user = user;
		this.albumName = albumName;
		this.albumDate = albumDate;
		this.albumAuthority = albumAuthority;
	}

	/** full constructor */
	public Album(User user, String albumName, String albumInfo, Date albumDate,
			Integer albumAuthority, Integer photoNum, Set photos) {
		this.user = user;
		this.albumName = albumName;
		this.albumInfo = albumInfo;
		this.albumDate = albumDate;
		this.albumAuthority = albumAuthority;
		this.photoNum = photoNum;
		this.photos = photos;
	}

	// Property accessors

	public Integer getAlbumId() {
		return this.albumId;
	}

	public void setAlbumId(Integer albumId) {
		this.albumId = albumId;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getAlbumName() {
		return this.albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}
	public Integer getAlbumUserid() {
		return albumUserid;
	}

	public void setAlbumUserid(Integer albumUserid) {
		this.albumUserid = albumUserid;
	}


	public String getAlbumInfo() {
		return this.albumInfo;
	}

	public void setAlbumInfo(String albumInfo) {
		this.albumInfo = albumInfo;
	}

	public Date getAlbumDate() {
		return this.albumDate;
	}

	public void setAlbumDate(Date albumDate) {
		this.albumDate = albumDate;
	}

	public Integer getAlbumAuthority() {
		return this.albumAuthority;
	}

	public void setAlbumAuthority(Integer albumAuthority) {
		this.albumAuthority = albumAuthority;
	}

	public Integer getPhotoNum() {
		return this.photoNum;
	}

	public void setPhotoNum(Integer photoNum) {
		this.photoNum = photoNum;
	}

	public Set getPhotos() {
		return this.photos;
	}

	public void setPhotos(Set photos) {
		this.photos = photos;
	}

}