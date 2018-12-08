package com.learningHelper.uiElementsCreators;

import com.guimaker.enums.ButtonType;
import com.guimaker.enums.KeyModifiers;
import com.guimaker.list.myList.ListConfiguration;
import com.guimaker.list.myList.MyList;
import com.guimaker.model.HotkeyWrapper;
import com.guimaker.options.ButtonOptions;
import com.guimaker.panels.GuiElementsCreator;
import com.learningHelper.application.ApplicationController;
import com.learningHelper.listRow.LearningResourceRow;
import com.learningHelper.model.LearningResource;
import com.learningHelper.panelsUpdaters.StartingPanelUpdater;
import com.learningHelper.uiElementsActionsCreators.StartingPanelActionsCreator;
import com.learningHelper.uiElementsStyles.UIElementsStyles;
import com.learningHelper.uiElementsTexts.Buttons;
import com.learningHelper.uiElementsTexts.Labels;
import com.learningHelper.uiElementsTexts.Titles;
import com.learningHelper.uiElementsTexts.UserInformation;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class StartingPanelElementsCreator {

	private StartingPanelActionsCreator actionsCreator;
	private JTabbedPane tabPane;
	private JTextField resourcesGroupName;
	private ApplicationController applicationController;

	public StartingPanelElementsCreator(
			ApplicationController applicationController) {
		actionsCreator = new StartingPanelActionsCreator(applicationController,
				new StartingPanelUpdater(this), this);
		this.applicationController = applicationController;
	}

	public JLabel getTitleLabel() {
		return GuiElementsCreator.createLabel(UIElementsStyles.titleLabelStyle()
															  .text(Titles.APPLICATION_TITLE));
	}

	public JTabbedPane getTabPane() {
		if (tabPane == null) {
			tabPane = new JTabbedPane();
		}
		return tabPane;

	}

	public JLabel getNoResourcesLabel() {
		return GuiElementsCreator.createLabel(
				UIElementsStyles.informationLabelStyle()
								.text(Labels.NO_RESOURCES));
	}

	public AbstractButton getButtonAddResourcesGroup() {
		return GuiElementsCreator.createButtonlikeComponent(
				UIElementsStyles.buttonStyle()
								.text(Buttons.ADD),
				actionsCreator.createActionAddGroupResource());
	}

	public JTextField getResourcesGroupNameInput() {
		if (resourcesGroupName == null) {
			resourcesGroupName = GuiElementsCreator.createTextField(
					UIElementsStyles.shortTextInputStyle());
		}
		return resourcesGroupName;
	}

	public JLabel getResourcesGroupNameLabel() {
		return GuiElementsCreator.createLabel(
				UIElementsStyles.labelForInputStyle()
								.text(Labels.RESOURCES_GROUP_NAME));
	}

	public MyList<LearningResource> createLearningResourcesList(
			String groupName) {

		MyList<LearningResource> learningResourceMyList = new MyList<>(
				new ListConfiguration<>(
						UserInformation.LEARNING_RESOURCE_DELETE,
						new LearningResourceRow(applicationController,
								groupName), LearningResource.getInitializer(),
						Titles.LEARNING_RESOURCES_LIST,
						applicationController.getApplicationWindow(),
						applicationController).showButtonsLoadNextPreviousWords(
						false));
		learningResourceMyList.addAdditionalNavigationButtons(
				createButtonOpenAllResourcesFromGroup(learningResourceMyList));
		return learningResourceMyList;

	}

	public AbstractButton createButtonOpenAllResourcesFromGroup(
			MyList<LearningResource> learningResources) {
		return GuiElementsCreator.createButtonlikeComponent(
				new ButtonOptions(ButtonType.BUTTON).text(
						Buttons.OPEN_ALL_RESOURCES_IN_GROUP),
				actionsCreator.createActionBrowseAllResources(
						learningResources),
				new HotkeyWrapper(KeyModifiers.CONTROL, KeyEvent.VK_D));
	}

}
