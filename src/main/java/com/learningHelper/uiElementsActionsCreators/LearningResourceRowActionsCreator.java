package com.learningHelper.uiElementsActionsCreators;

import com.guimaker.enums.InputGoal;
import com.guimaker.list.ListElementPropertyManager;
import com.guimaker.list.myList.ListPropertyChangeHandler;
import com.guimaker.model.CommonListElements;
import com.guimaker.panels.MainPanel;
import com.learningHelper.application.ApplicationController;
import com.learningHelper.enums.LearningResourceType;
import com.learningHelper.listPropertyManagers.ResourceTagPropertyManager;
import com.learningHelper.listPropertyManagers.StoppedPlaceStringPropertyManager;
import com.learningHelper.listPropertyManagers.StoppedPlaceTimeRangeEndPropertyManager;
import com.learningHelper.listPropertyManagers.StoppedPlaceTimeRangeStartPropertyManager;
import com.learningHelper.model.LearningResource;
import com.learningHelper.panelsUpdaters.LearningResourceRowUpdater;
import com.learningHelper.webBrowsing.WebBrowser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class LearningResourceRowActionsCreator {

	private LearningResourceRowUpdater rowUpdater;
	private ApplicationController applicationController;
	private ListElementPropertyManager<?, LearningResource> tagInputPropertyManager;

	public LearningResourceRowActionsCreator(
			ApplicationController applicationController,
			String learningResourcesGroupName) {
		this.rowUpdater = new LearningResourceRowUpdater(applicationController,
				learningResourcesGroupName);
		this.applicationController = applicationController;
	}

	public ListElementPropertyManager<?, LearningResource> getTagInputPropertyManager() {
		return tagInputPropertyManager;
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
						learningResource.getLearningStoppedPlace().clear();
						learningResource.setType(type);
						rowUpdater.changeResourceRowType(learningResource,
								panel, type, commonListElements);
						Window windowAncestor = SwingUtilities.getWindowAncestor(
								panel.getPanel());
						if (!(windowAncestor instanceof JFrame)) {
							windowAncestor.pack();
						}

					}

				}

			}
		};
	}

	public JTextField addTagChangeListener(LearningResource learningResource,
			JTextField textField,
			CommonListElements<LearningResource> commonListElements) {
		tagInputPropertyManager = new ResourceTagPropertyManager();
		ListPropertyChangeHandler tagInputPropertyChangeHandler = new ListPropertyChangeHandler<>(
				learningResource, commonListElements.getList(),
				applicationController.getApplicationWindow(),
				tagInputPropertyManager, InputGoal.EDIT, "");
		textField.addFocusListener(tagInputPropertyChangeHandler);
		return textField;

	}

	public AbstractAction createActionGoToResource(
			LearningResource learningResource) {
		return new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				WebBrowser.browseResource(learningResource);
			}
		};
	}

	public JTextField listenForChangesInStoppedPlaceTextInput(
			JTextField textField, LearningResource learningResource,
			CommonListElements<LearningResource> commonListElements) {
		ListPropertyChangeHandler propertyChangeListener = new ListPropertyChangeHandler<>(
				learningResource, commonListElements.getList(),
				applicationController.getApplicationWindow(),
				new StoppedPlaceStringPropertyManager(), InputGoal.EDIT, "");
		textField.addFocusListener(propertyChangeListener);
		return textField;
	}

	public JTextField listenForChangesInStoppedPlaceTimeRangeStartInput(
			JTextField textField, LearningResource learningResource,
			CommonListElements<LearningResource> commonListElements) {
		ListPropertyChangeHandler tagInputPropertyChangeHandler = new ListPropertyChangeHandler<>(
				learningResource, commonListElements.getList(),
				applicationController.getApplicationWindow(),
				new StoppedPlaceTimeRangeStartPropertyManager(), InputGoal.EDIT,
				"");
		textField.addFocusListener(tagInputPropertyChangeHandler);
		return textField;
	}
	public JTextField listenForChangesInStoppedPlaceTimeRangeEndInput(
			JTextField textField, LearningResource learningResource,
			CommonListElements<LearningResource> commonListElements) {
		ListPropertyChangeHandler tagInputPropertyChangeHandler = new ListPropertyChangeHandler<>(
				learningResource, commonListElements.getList(),
				applicationController.getApplicationWindow(),
				new StoppedPlaceTimeRangeEndPropertyManager(), InputGoal.EDIT,
				"");
		textField.addFocusListener(tagInputPropertyChangeHandler);
		return textField;
	}
}
