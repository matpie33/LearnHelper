package com.learningHelper.listPropertyManagers;

import com.guimaker.list.ListElementPropertyManager;
import com.learningHelper.model.LearningResource;

import javax.swing.text.JTextComponent;

public class ResourceTagPropertyManager implements
		ListElementPropertyManager<String, LearningResource> {
	@Override
	public String getInvalidPropertyReason() {
		return null;
	}

	@Override
	public boolean isPropertyFound(String property,
			LearningResource wordToCheck,
			LearningResource propertyHolder) {
		return wordToCheck.getTag().equals(property);
	}

	@Override
	public String getPropertyValue(LearningResource learningResource) {
		return learningResource.getTag();
	}

	@Override
	public String validateInputAndConvertToProperty(JTextComponent textInput,
			LearningResource propertyHolder) {
		return textInput.getText();
	}

	@Override
	public void setProperty(LearningResource learningResource,
			String newValue, String previousValue) {
		learningResource.setTag(newValue);
	}

	@Override
	public String getPropertyDefinedException(String property) {
		return null;
	}
}
