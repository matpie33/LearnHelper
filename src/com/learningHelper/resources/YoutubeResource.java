package com.learningHelper.resources;

public class YoutubeResource extends Resource{
	
	private int seconds;
	private int minutes;
	
	public YoutubeResource (){
		super(ResourceType.YOUTUBE);
	}
	
	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}
	
	public int getSeconds(){
		return seconds;
	}
	
	public int getMinutes(){
		return minutes;
	}

}
