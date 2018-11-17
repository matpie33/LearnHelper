package com.learningHelper.model;

import com.learningHelper.enums.LearningResourceType;

import java.util.List;

public class LearningResource {

	private LearningResourceType type;
	private String tag;
	private List<String> alternativeLocations;

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

	public List<String> getAlternativeLocations() {
		return alternativeLocations;
	}

	public void setAlternativeLocations(List<String> alternativeLocations) {
		this.alternativeLocations = alternativeLocations;
	}
}
