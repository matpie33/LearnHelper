package com.learningHelper.listPropertyManagers;

import com.guimaker.list.ListElementPropertyManager;
import com.learningHelper.model.LearningResource;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.swing.text.JTextComponent;
import java.util.List;

public class ResourceLocationPropertyManager
		implements ListElementPropertyManager<String, LearningResource> {
	@Override
	public String getInvalidPropertyReason() {
		throw new NotImplementedException();
	}

	@Override
	public boolean isPropertyFound(String property,
			LearningResource learningResource) {
		return learningResource.getAlternativeLocations()
							   .contains(property);
	}

	@Override
	public String getPropertyValue(LearningResource learningResource) {
		return learningResource.getTag();
	}

	@Override
	public String validateInputAndConvertToProperty(JTextComponent textInput,
			LearningResource propertyHolder) {
		String text = textInput.getText();
		return propertyHolder.getAlternativeLocations()
							 .contains(text) ? null : text;
	}

	@Override
	public void setProperty(LearningResource learningResource, String newValue,
			String previousValue) {
		List<String> alternativeLocations = learningResource.getAlternativeLocations();
		int previousValueIndex = alternativeLocations.indexOf(previousValue);
		if (previousValueIndex >= 0) {
			alternativeLocations.set(previousValueIndex, newValue);
		}
		else {
			alternativeLocations.add(newValue);
		}

	}

	@Override
	public String getPropertyDefinedException(String property) {
		throw new NotImplementedException();
	}

}
