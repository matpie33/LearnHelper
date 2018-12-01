package com.learningHelper.listPropertyManagers;

import com.guimaker.list.ListElementPropertyManager;
import com.learningHelper.model.StringListElement;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.swing.text.JTextComponent;

public class ResourceLocationPropertyManager
		implements ListElementPropertyManager<String, StringListElement> {
	@Override
	public String getInvalidPropertyReason() {
		throw new NotImplementedException();
	}

	@Override
	public boolean isPropertyFound(String property,
			StringListElement wordToCheck,
			StringListElement propertyHolder) {
		return wordToCheck.getValue()
						  .equals(property);
	}

	@Override
	public String getPropertyValue(StringListElement learningResource) {
		return learningResource.getValue();
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
