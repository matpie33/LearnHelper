package com.learningHelper.uiElementsActionsCreators;

import com.guimaker.enums.FillType;
import com.guimaker.enums.InputGoal;
import com.guimaker.list.myList.ListPropertyChangeHandler;
import com.guimaker.model.CommonListElements;
import com.guimaker.panels.MainPanel;
import com.guimaker.row.SimpleRowBuilder;
import com.learningHelper.application.ApplicationController;
import com.learningHelper.enums.LearningResourceType;
import com.learningHelper.listPropertyManagers.ListTagPropertyManager;
import com.learningHelper.model.LearningResource;
import com.learningHelper.panelsUpdaters.LearningResourceRowUpdater;
import com.learningHelper.uiElementsCreators.LearningResourceRowElementsCreator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class LearningResourceRowActionsCreator {

	private String learningResourcesGroupName;
	private LearningResourceRowUpdater rowUpdater;
	private ApplicationController applicationController;

	public LearningResourceRowActionsCreator(
			LearningResourceRowElementsCreator elementsCreator,
			ApplicationController applicationController,
			String learningResourcesGroupName) {
		this.rowUpdater = new LearningResourceRowUpdater(applicationController,
				learningResourcesGroupName, elementsCreator);
		this.applicationController = applicationController;
		this.learningResourcesGroupName = learningResourcesGroupName;
	}


	public AbstractAction createIncreaseVideoNumberAction() {
		return new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		};
	}

	public ItemListener createActionChangeResourceType(
			LearningResource learningResource, MainPanel panel,
			CommonListElements commonListElements) {
		return new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					String newValue = e.getItem()
									   .toString();
					LearningResourceType type = LearningResourceType.getTypeByString(
							newValue);
					if (type != null) {
						learningResource.setType(type);
						rowUpdater.changeResourceRowType(learningResource,
								panel, type, commonListElements);

					}

				}

			}
		};
	}

	public JTextField addTagChangeListener(LearningResource learningResource,
			JTextField textField) {
		ListPropertyChangeHandler listPropertyChangeHandler = new ListPropertyChangeHandler<>(
				learningResource,
				applicationController.getLearningResourcesGroup(
						learningResourcesGroupName),
				applicationController.getApplicationWindow(),
				new ListTagPropertyManager(), InputGoal.EDIT);
		textField.addFocusListener(listPropertyChangeHandler);
		return textField;

	}

	public AbstractAction createActionGoToResource() {
		return null;
	}

	public AbstractAction createActionIncreaseVideoNumber() {
		return null;
	}
}
