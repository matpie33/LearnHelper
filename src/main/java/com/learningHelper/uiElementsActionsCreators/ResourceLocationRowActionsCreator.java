package com.learningHelper.uiElementsActionsCreators;

import com.guimaker.enums.InputGoal;
import com.guimaker.list.ListElementPropertyManager;
import com.guimaker.list.myList.ListPropertyChangeHandler;
import com.guimaker.list.myList.MyList;
import com.learningHelper.application.ApplicationController;
import com.learningHelper.listPropertyManagers.ResourceLocationPropertyManager;
import com.learningHelper.model.StringListElement;
import com.learningHelper.urlHandling.VideoSeriesNumberIncrementer;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.event.ActionEvent;

public class ResourceLocationRowActionsCreator {
	private ApplicationController applicationController;
	private ListElementPropertyManager<String, StringListElement> propertyChangeHandler;

	public ResourceLocationRowActionsCreator(
			ApplicationController applicationController) {
		this.applicationController = applicationController;
	}

	public JTextComponent withPropertyChangeListener(
			StringListElement stringListElement, JTextComponent component,
			MyList<StringListElement> list) {
		propertyChangeHandler = new ResourceLocationPropertyManager();
		component.addFocusListener(
				new ListPropertyChangeHandler<>(stringListElement, list,
						applicationController.getApplicationWindow(),
						propertyChangeHandler, InputGoal.EDIT, ""));
		return component;
	}

	public AbstractAction createIncreaseVideoNumberAction(
			JTextComponent resourceLocationInput,
			StringListElement stringListElement) {
		return new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String resourceLocation = resourceLocationInput.getText();
				String newLocation = VideoSeriesNumberIncrementer.increment(
						resourceLocation);
				resourceLocationInput.setText(newLocation);
				propertyChangeHandler.setProperty(stringListElement, newLocation,
						resourceLocation);
			}
		};
	}

}
