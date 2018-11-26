package com.learningHelper.uiElementsActionsCreators;

import com.guimaker.enums.InputGoal;
import com.guimaker.list.myList.ListPropertyChangeHandler;
import com.guimaker.list.myList.MyList;
import com.learningHelper.application.ApplicationController;
import com.learningHelper.listPropertyManagers.ResourceLocationPropertyManager;
import com.learningHelper.model.StringListElement;

import javax.swing.text.JTextComponent;

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

}
