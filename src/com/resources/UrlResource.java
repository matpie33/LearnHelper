package com.resources;

public class UrlResource implements Resource {
	
	private String urlAddress = "";
	private String startingPlace = "";
	
	public UrlResource(){
		urlAddress="";
		startingPlace="";
	}
	
	public UrlResource (String address, String startingPoint){
		urlAddress = address;
		startingPlace = startingPoint;
	}
	
	public String getUrlAddress() {
		return urlAddress;
	}
	public void setUrlAddress(String urlAddress) {
		this.urlAddress = urlAddress;
	}
	public String getStartingPlace() {
		return startingPlace;
	}
	public void setStartingPlace(String startingPlace) {
		this.startingPlace = startingPlace;
	}

}
