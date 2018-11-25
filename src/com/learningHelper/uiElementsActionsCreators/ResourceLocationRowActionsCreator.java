package com.learningHelper.uiElementsActionsCreators;

import com.guimaker.enums.InputGoal;
import com.guimaker.list.myList.ListPropertyChangeHandler;
import com.guimaker.list.myList.MyList;
import com.learningHelper.application.ApplicationController;
import com.learningHelper.listPropertyManagers.ResourceLocationPropertyManager;
import com.learningHelper.model.LearningResource;
import com.learningHelper.model.StringListElement;

import javax.swing.*;
import javax.swing.text.JTextComponent;

public class ResourceLocationRowActionsCreator {
	private ApplicationController applicationController;
	private String learningResourceGroup;

	public ResourceLocationRowActionsCreator(
			ApplicationController applicationController,
			String learningResourceGroup) {
		this.applicationController = applicationController;
		this.learningResourceGroup = learningResourceGroup;
	}

	public JTextComponent withPropertyChangeListener(
			LearningResource learningResource, JTextComponent component) {
		component.addFocusListener(
				new ListPropertyChangeHandler<>
						(learningResource,applicationController
								.getLearningResourcesGroup(learningResourceGroup),
								applicationController
								.getApplicationWindow(), new
								ResourceLocationPropertyManager(), InputGoal.EDIT));
		return component;
	}

}
