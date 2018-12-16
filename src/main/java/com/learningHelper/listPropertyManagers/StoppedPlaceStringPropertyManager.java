package com.learningHelper.listPropertyManagers;

import com.guimaker.list.ListElementPropertyManager;
import com.learningHelper.model.LearningResource;

public class StoppedPlaceStringPropertyManager implements
		ListElementPropertyManager<String, LearningResource> {
	@Override
	public String getInvalidPropertyReason() {
		return null;
	}

	@Override
	public boolean isPropertyFound(String property,
			LearningResource wordToCheck, LearningResource learningResource) {
		return false;
	}

	@Override
	public String getPropertyValue(LearningResource learningResource) {
		return learningResource.getLearningStoppedPlace().getTextFragmentPlace();
	}

	@Override
	public String getPropertyDefinedException(String property) {
		return null;
	}

	@Override
	public void setProperty(LearningResource learningResource, String newValue,
			String previousValue) {
		learningResource.getLearningStoppedPlace().setTextFragmentPlace(newValue);
	}
}
