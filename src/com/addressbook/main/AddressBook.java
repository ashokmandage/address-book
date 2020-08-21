package com.addressbook.main;

public class AddressBook {
	private String fname;
	private String lname;
	private String address;
	private String city;
	private String state;
	private String zip;
	private String telNo;

	public AddressBook() {
		this.fname = "";
		this.lname = "";
		this.address = "";
		this.city = "";
		this.state = "";
		this.zip = "";
		this.telNo = "";
	}

	public AddressBook(String fname, String lname, String address, String city, String state, String zip,
			String telNo) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.telNo = telNo;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getTelNo() {
		return telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

}