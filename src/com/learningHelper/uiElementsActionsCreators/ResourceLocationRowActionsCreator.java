package com.learningHelper.uiElementsActionsCreators;

import com.guimaker.enums.InputGoal;
import com.guimaker.list.myList.ListPropertyChangeHandler;
import com.guimaker.list.myList.MyList;
import com.learningHelper.urlHandling.VideoSeriesNumberIncrementer;
import com.learningHelper.application.ApplicationController;
import com.learningHelper.listPropertyManagers.ResourceLocationPropertyManager;
import com.learningHelper.model.StringListElement;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.event.ActionEvent;

public class ResourceLocationRowActionsCreator {
	private ApplicationController applicationController;

	public ResourceLocationRowActionsCreator(
			ApplicationController applicationController) {
		this.applicationController = applicationController;
	}

	public JTextComponent withPropertyChangeListener(
			StringListElement stringListElement, JTextComponent component,
			MyList<StringListElement> list) {
		component.addFocusListener(
				new ListPropertyChangeHandler<>(stringListElement, list,
						applicationController.getApplicationWindow(),
						new ResourceLocationPropertyManager(), InputGoal.EDIT));
		return component;
	}

	public AbstractAction createIncreaseVideoNumberAction(
			JTextComponent resourceLocationInput) {
		return new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String resourceLocation = resourceLocationInput.getText();
				resourceLocationInput.setText(VideoSeriesNumberIncrementer.increment
						(resourceLocation));
			}
		};
	}

}
