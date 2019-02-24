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
import com.learningHelper.listPropertyManagers.StoppedPlaceVideoMinutePropertyManager;
import com.learningHelper.listPropertyManagers.StoppedPlaceVideoSecondPropertyManager;
import com.learningHelper.model.LearningResource;
import com.learningHelper.panelsUpdaters.LearningResourceRowUpdater;
import com.learningHelper.webBrowsing.WebBrowser;

import javax.swing.*;
import javax.swing.text.JTextComponent;
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
						learningResource.getLearningStoppedPlace()
										.clear();
						learningResource.setType(type);
						if (type.equals(LearningResourceType.WEB_VIDEO)) {
							learningResource.initializeVideoTimePoint();
						}
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

	public JTextComponent addTagChangeListener(
			LearningResource learningResource, JTextComponent textField,
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

	public JTextComponent listenForChangesInStoppedPlaceTextInput(
			JTextComponent textField, LearningResource learningResource,
			CommonListElements<LearningResource> commonListElements) {
		ListPropertyChangeHandler propertyChangeListener = new ListPropertyChangeHandler<>(
				learningResource, commonListElements.getList(),
				applicationController.getApplicationWindow(),
				new StoppedPlaceStringPropertyManager(), InputGoal.EDIT, "");
		textField.addFocusListener(propertyChangeListener);
		return textField;
	}

	public JTextComponent listenForChangesInStoppedPlaceTimeRangeStartInput(
			JTextComponent textField, LearningResource learningResource,
			CommonListElements<LearningResource> commonListElements) {
		ListPropertyChangeHandler tagInputPropertyChangeHandler = new ListPropertyChangeHandler<>(
				learningResource, commonListElements.getList(),
				applicationController.getApplicationWindow(),
				new StoppedPlaceVideoMinutePropertyManager(), InputGoal.EDIT,
				"");
		textField.addFocusListener(tagInputPropertyChangeHandler);
		return textField;
	}

	public JTextComponent listenForChangesInStoppedPlaceTimeRangeEndInput(
			JTextComponent textField, LearningResource learningResource,
			CommonListElements<LearningResource> commonListElements) {
		ListPropertyChangeHandler tagInputPropertyChangeHandler = new ListPropertyChangeHandler<>(
				learningResource, commonListElements.getList(),
				applicationController.getApplicationWindow(),
				new StoppedPlaceVideoSecondPropertyManager(), InputGoal.EDIT,
				"");
		textField.addFocusListener(tagInputPropertyChangeHandler);
		return textField;
	}

	public AbstractAction createActionSwitchResourceType(JComboBox combobox) {
		return new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int itemCount = combobox.getItemCount();
				if (itemCount > 1) {
					combobox.setSelectedIndex(
							(combobox.getSelectedIndex() + 1) % itemCount);
				}
			}
		};
	}

	public ItemListener createActionSkipChoosingVideoPlayerType() {
		return new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				WebBrowser.setSkipVideoPlayerTypeChossingForNaruto(
						e.getStateChange() == ItemEvent.SELECTED);

			}
		};
	}

}
