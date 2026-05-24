package com.searchapp.entity;

public class Address {

	private String city;
	private String state;
	private String pinCode;
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	@Override
	public String toString() {
		return "Address [city=" + city + ", state=" + state + ", pinCode=" + pinCode + "]";
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPinCode() {
		return pinCode;
	}
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
	
}
