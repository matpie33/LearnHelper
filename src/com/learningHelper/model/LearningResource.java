package com.learningHelper.model;

import com.guimaker.list.ListElement;
import com.guimaker.list.ListElementInitializer;
import com.guimaker.utilities.StringUtilities;
import com.learningHelper.enums.LearningResourceType;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

public class LearningResource implements ListElement{

	private LearningResourceType type;
	private String tag = "";
	private List<String> alternativeLocations = new ArrayList<>();
	private final static String MEANINGFUL_NAME = "Learning resource";

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

	public static ListElementInitializer<LearningResource> getInitializer (){
		return ()->{
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
		throw new NotImplementedException();
	}

	@Override
	public String getMeaningfulName() {
		return MEANINGFUL_NAME;
	}
}
