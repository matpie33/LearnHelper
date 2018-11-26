package com.learningHelper.listPropertyManagers;

import com.guimaker.list.ListElementPropertyManager;
import com.learningHelper.model.LearningResource;
import com.learningHelper.model.StringListElement;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.swing.text.JTextComponent;
import java.util.List;

public class ResourceLocationPropertyManager
		implements ListElementPropertyManager<String, StringListElement> {
	@Override
	public String getInvalidPropertyReason() {
		throw new NotImplementedException();
	}

	@Override
	public boolean isPropertyFound(String property,
			StringListElement stringListElement) {
		return stringListElement.getValue()
							   .equals(property);
	}

	@Override
	public String getPropertyValue(StringListElement learningResource) {
		return learningResource.getValue();
	}

	@Override
	public String validateInputAndConvertToProperty(JTextComponent textInput,
			StringListElement stringListElement) {
		return textInput.getText();
	}

	@Override
	public void setProperty(StringListElement stringListElement, String newValue,
			String previousValue) {
		stringListElement.setValue(newValue);
	}

	@Override
	public String getPropertyDefinedException(String property) {
		throw new NotImplementedException();
	}

}
