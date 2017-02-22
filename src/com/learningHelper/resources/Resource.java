package com.learningHelper.resources;

public class Resource {
	
	private String location = "";
	private String startingPlace = "";
	private ResourceType type;
	
	public Resource(ResourceType type){
		this(type, "", "");
	}
	
	public Resource (ResourceType type, String address, String startingPlace){
		location = address;
		this.startingPlace = startingPlace;
		this.type=type;
	}
	
	public String getUrlAddress() {
		return location;
	}
	public void setUrlAddress(String urlAddress) {
		this.location = urlAddress;
	}
	public String getStartingPlace() {
		return startingPlace;
	}
	public void setStartingPlace(String startingPlace) {
		this.startingPlace = startingPlace;
	}
	
	public ResourceType getType(){
		return type;
	}
	
	public String toString(){
		return "type: "+getType()+" path: "+getUrlAddress()+" start "+getStartingPlace();
	}

}
