package com.learningHelper.listPropertyManagers;

import com.guimaker.list.ListElementPropertyManager;
import com.learningHelper.listRow.LearningResourceRow;
import com.learningHelper.model.LearningResource;

import javax.swing.text.JTextComponent;

public class ListTagPropertyManager implements
		ListElementPropertyManager<String, LearningResource> {
	@Override
	public String getInvalidPropertyReason() {
		return null;
	}

	@Override
	public boolean isPropertyFound(String property,
			LearningResource learningResource) {
		return learningResource.getTag().equals(property);
	}

	@Override
	public String getPropertyValue(LearningResource learningResource) {
		return learningResource.getTag();
	}

	@Override
	public String validateInputAndConvertToProperty(JTextComponent textInput) {
		return textInput.getText();
	}

	@Override
	public void setProperty(LearningResource learningResource,
			String propertyValue) {
		learningResource.setTag(propertyValue);
	}

	@Override
	public String getPropertyDefinedException(String property) {
		return null;
	}
}
