package com.learningHelper.resources;

public class ResourceChangeHandler {
	
	private Resource resource;
	private ChangeType changeType;
	
	public ResourceChangeHandler (Resource res, ChangeType change){
		this.resource=res;
		changeType = change;
	}
	
	public Resource updateAndReturn(String newExpression){
		switch (changeType){
		case PATH:
			System.out.println("chaning path to: "+newExpression);
			resource.setUrlAddress(newExpression);
			break;
		case STARTING_POINT:
			System.out.println("setting start point: "+newExpression);
			resource.setStartingPlace(newExpression);
			break;
		case YOUTUBE_MINUTES:
			YoutubeResource r = (YoutubeResource) resource;
			r.setMinutes(Integer.parseInt(newExpression));
			break;
		case YOUTUBE_SECONDS:
			YoutubeResource r2 = (YoutubeResource) resource;
			r2.setSeconds(Integer.parseInt(newExpression));
			break;
		default:
			System.err.println("I don't know what I should change: resource change handler");
			break;
		}
		return resource;
	}
	
	public Resource getResource(){
		return resource;
	}
	
	public ChangeType getChangeType(){
		return changeType;
	}

}
