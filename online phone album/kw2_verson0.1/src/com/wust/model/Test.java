package com.wust.model;

/**
 * Test entity. @author MyEclipse Persistence Tools
 */

public class Test implements java.io.Serializable {

	// Fields

	private String name;
	private String value;

	// Constructors

	/** default constructor */
	public Test() {
	}

	/** minimal constructor */
	public Test(String name) {
		this.name = name;
	}

	/** full constructor */
	public Test(String name, String value) {
		this.name = name;
		this.value = value;
	}

	// Property accessors

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}