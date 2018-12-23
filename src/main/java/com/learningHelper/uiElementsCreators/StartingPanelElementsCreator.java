package com.learningHelper.uiElementsCreators;

import com.guimaker.enums.ButtonType;
import com.guimaker.enums.KeyModifiers;
import com.guimaker.list.myList.ListConfiguration;
import com.guimaker.list.myList.MyList;
import com.guimaker.model.HotkeyWrapper;
import com.guimaker.options.ButtonOptions;
import com.guimaker.panels.GuiElementsCreator;
import com.guimaker.utilities.CommonActionsCreator;
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

	public JLabel createTitleLabel() {
		return GuiElementsCreator.createLabel(UIElementsStyles.titleLabelStyle()
															  .text(Titles.APPLICATION_TITLE));
	}

	public JTabbedPane getTabPane() {
		if (tabPane == null) {
			tabPane = new JTabbedPane();
			CommonActionsCreator.addHotkey(
					new HotkeyWrapper(KeyModifiers.CONTROL, KeyEvent.VK_W),
					actionsCreator.createActionSwitchTabs(tabPane), tabPane);
		}
		return tabPane;

	}

	public JLabel createNoResourcesLabel() {
		return GuiElementsCreator.createLabel(
				UIElementsStyles.informationLabelStyle()
								.text(Labels.NO_RESOURCES));
	}


	public AbstractButton createButtonAddResourcesGroup() {
		return GuiElementsCreator.createButtonlikeComponent(
				UIElementsStyles.buttonStyle()
								.text(Buttons.ADD),
				actionsCreator.createActionAddGroupResource(),
				KeyEvent.VK_ENTER);
	}

	public AbstractButton createButtonLoadLastUsedFile() {
		return GuiElementsCreator.createButtonlikeComponent(
				UIElementsStyles.buttonStyle()
								.text(Buttons.LOAD_LAST_USED_FILE),
				actionsCreator.createActionOpenLastUsedFile(),
				new HotkeyWrapper(KeyModifiers.CONTROL, KeyEvent.VK_E));
	}

	public JTextField createResourcesGroupNameInput() {
		if (resourcesGroupName == null) {
			resourcesGroupName = GuiElementsCreator.createTextField(
					UIElementsStyles.shortTextInputStyle());
		}
		return resourcesGroupName;
	}

	public JLabel createResourcesGroupNameLabel() {
		return GuiElementsCreator.createLabel(
				UIElementsStyles.labelForInputStyle()
								.text(Labels.RESOURCES_GROUP_NAME));
	}

	public MyList<LearningResource> createLearningResourcesList(
			String groupName) {

		MyList<LearningResource> learningResourcesList = new MyList<>(
				new ListConfiguration<>(
						UserInformation.LEARNING_RESOURCE_DELETE,
						new LearningResourceRow(applicationController,
								groupName), LearningResource.getInitializer(),
						Titles.LEARNING_RESOURCES_LIST,
						applicationController.getApplicationWindow(),
						applicationController).showButtonsLoadNextPreviousWords(
						false));
		applicationController.addResourcesGroup(groupName,
				learningResourcesList);
		learningResourcesList.addAdditionalNavigationButtons(
				createButtonOpenAllResourcesFromGroup(learningResourcesList));
		return learningResourcesList;

	}

	private AbstractButton createButtonOpenAllResourcesFromGroup(
			MyList<LearningResource> learningResources) {
		return GuiElementsCreator.createButtonlikeComponent(
				new ButtonOptions(ButtonType.BUTTON).text(
						Buttons.OPEN_ALL_RESOURCES_IN_GROUP),
				actionsCreator.createActionBrowseAllResources(
						learningResources),
				new HotkeyWrapper(KeyModifiers.CONTROL, KeyEvent.VK_D));
	}

	public AbstractButton createButtonSave() {
		return GuiElementsCreator.createButtonlikeComponent(
				new ButtonOptions(ButtonType.BUTTON).text(Buttons.SAVE),
				actionsCreator.openSaveDialog(),
				new HotkeyWrapper(KeyModifiers.CONTROL, KeyEvent.VK_S));
	}

	public AbstractButton createButtonLoad() {
		return GuiElementsCreator.createButtonlikeComponent(
				new ButtonOptions(ButtonType.BUTTON).text(Buttons.LOAD),
				actionsCreator.openLoadFileDialog(),
				new HotkeyWrapper(KeyModifiers.CONTROL, KeyEvent.VK_Q));
	}

}
