package com.learningHelper.listPropertyManagers;

import com.guimaker.list.ListElementPropertyManager;
import com.learningHelper.model.LearningResource;

import javax.swing.text.JTextComponent;

public class StoppedPlaceTimeRangeStartPropertyManager
		implements ListElementPropertyManager<Integer, LearningResource> {
	@Override
	public String getInvalidPropertyReason() {
		return null;
	}

	@Override
	public boolean isPropertyFound(Integer property,
			LearningResource wordToCheck, LearningResource learningResource) {
		return false;
	}

	@Override
	public String getPropertyValue(LearningResource learningResource) {
		return "" + learningResource.getLearningStoppedPlace()
									.getVideoTimeRangePlace()
									.getRangeStart();
	}

	@Override
	public Integer convertToProperty(JTextComponent input){
		return input.getText()
					.isEmpty() ? 0 : Integer.parseInt(input.getText());
	}

	@Override
	public String getPropertyDefinedException(Integer property) {
		return null;
	}

	@Override
	public void setProperty(LearningResource learningResource, Integer newValue,
			Integer previousValue) {
		learningResource.getLearningStoppedPlace()
						.setVideoTimeRangeStart(newValue);
	}
}
