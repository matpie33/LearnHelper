package com.learningHelper.model;

import com.guimaker.list.ListElement;
import com.guimaker.list.ListElementInitializer;
import com.learningHelper.enums.LearningResourceType;

import java.util.ArrayList;
import java.util.List;

public class LearningResource implements ListElement {

	private LearningResourceType type = LearningResourceType.WEB_HELPER_RESOURCE;
	private String tag = "";
	private List<StringListElement> alternativeLocations = new ArrayList<>();
	//TODO refactor back to List<String> after getting rid of ListElement
	// interface

	private LearningStoppedPlace learningStoppedPlace = LearningStoppedPlace.empty();

	public LearningStoppedPlace getLearningStoppedPlace() {
		return learningStoppedPlace;
	}

	public LearningResourceType getType() {
		return type;
	}

	public void setType(LearningResourceType type) {
		this.type = type;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public List<StringListElement> getAlternativeLocations() {
		return alternativeLocations;
	}

	public void setAlternativeLocations(
			List<StringListElement> alternativeLocations) {
		this.alternativeLocations = alternativeLocations;
	}

	public static ListElementInitializer<LearningResource> getInitializer() {
		return () -> {
			LearningResource learningResource = new LearningResource();
			learningResource.setTag("");
			learningResource.setAlternativeLocations(new ArrayList<>());
			return learningResource;
		};
	}

	@Override
	public boolean isEmpty() {
		return tag.isEmpty() && alternativeLocations.isEmpty();
	}

	@Override
	public String getDisplayedText() {
		return toString();
	}

	@Override
	public String toString() {
		return tag + ", " + alternativeLocations + " " + "stop place: "
				+ learningStoppedPlace;
	}

}