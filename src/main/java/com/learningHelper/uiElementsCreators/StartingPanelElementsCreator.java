package com.learningHelper.uiElementsCreators;

import com.guimaker.colors.BasicColors;
import com.guimaker.enums.ButtonType;
import com.guimaker.enums.KeyModifiers;
import com.guimaker.list.myList.ListConfiguration;
import com.guimaker.list.myList.MyList;
import com.guimaker.options.ButtonOptions;
import com.guimaker.panels.GuiElementsCreator;
import com.learningHelper.application.ApplicationController;
import com.learningHelper.listRow.LearningResourceRow;
import com.learningHelper.model.LearningResource;
import com.learningHelper.panels.StartingPanel;
import com.learningHelper.panelsUpdaters.StartingPanelUpdater;
import com.learningHelper.uiElementsActionsCreators.StartingPanelActionsCreator;
import com.learningHelper.uiElementsStyles.UIElementsStyles;
import com.learningHelper.uiElementsTexts.*;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.event.KeyEvent;

public class StartingPanelElementsCreator {

	private StartingPanelActionsCreator actionsCreator;
	private JTabbedPane tabPane;
	private JTextComponent resourcesGroupNameInput;
	private ApplicationController applicationController;
	private StartingPanel startingPanel;
	private AbstractButton buttonRemoveResourcesGroup;
	private AbstractButton buttonAddResourcesGroup;
	private AbstractButton buttonLoadLastUsedFile;
	private AbstractButton buttonOpenAllResourcesFromGroup;
	private AbstractButton buttonSave;
	private AbstractButton buttonLoad;
	private JLabel labelResourcesGroup;
	private JLabel titleLabel;
	private MyList<LearningResource> learningResourcesList;

	public StartingPanelElementsCreator(
			ApplicationController applicationController,
			StartingPanel startingPanel) {
		actionsCreator = new StartingPanelActionsCreator(applicationController,
				new StartingPanelUpdater(this), this);
		this.applicationController = applicationController;
		this.startingPanel = startingPanel;
	}

	public void createElements() {
		createButtons();
		resourcesGroupNameInput = GuiElementsCreator.createTextArea(
				UIElementsStyles.shortTextInputStyle());
		labelResourcesGroup = GuiElementsCreator.createLabel(
				UIElementsStyles.labelForInputStyle()
								.text(Labels.RESOURCES_GROUP_NAME));
		titleLabel = GuiElementsCreator.createLabel(
				UIElementsStyles.titleLabelStyle()
								.text(Titles.APPLICATION_TITLE));
		createTabPane();
	}

	private void createButtons() {

		buttonAddResourcesGroup = GuiElementsCreator.createButtonLikeComponent(
				UIElementsStyles.buttonStyle()
								.text(Buttons.ADD));
		startingPanel.addHotkey(KeyEvent.VK_ENTER,
				actionsCreator.createActionAddGroupResource(),
				buttonAddResourcesGroup, HotkeysDescription.ADD_RESOURCE_GROUP);
		buttonLoadLastUsedFile = GuiElementsCreator.createButtonLikeComponent(
				UIElementsStyles.buttonStyle()
								.text(Buttons.LOAD_LAST_USED_FILE));
		startingPanel.addHotkey(KeyModifiers.CONTROL, KeyEvent.VK_E,
				actionsCreator.createActionOpenLastUsedFile(),
				buttonLoadLastUsedFile, HotkeysDescription.OPEN_LAST_USED_FILE);

		buttonSave = GuiElementsCreator.createButtonLikeComponent(
				UIElementsStyles.buttonStyle()
								.text(Buttons.SAVE));
		startingPanel.addHotkey(KeyModifiers.CONTROL, KeyEvent.VK_S,
				actionsCreator.openSaveDialog(), buttonSave,
				HotkeysDescription.SAVE);
		buttonLoad = GuiElementsCreator.createButtonLikeComponent(
				new ButtonOptions(ButtonType.BUTTON).text(Buttons.LOAD));
		startingPanel.addHotkey(KeyModifiers.CONTROL, KeyEvent.VK_Q,
				actionsCreator.openLoadFileDialog(), buttonLoad,
				HotkeysDescription.LOAD);
	}

	private void createTabPane() {
		tabPane = GuiElementsCreator.createTabbedPane();
		tabPane.setBackground(BasicColors.BLUE_NORMAL_7);
		startingPanel.addHotkey(KeyModifiers.CONTROL, KeyEvent.VK_W,
				actionsCreator.createActionSwitchTabs(tabPane), tabPane,
				HotkeysDescription.SWITCH_TABS);

	}

	public MyList<LearningResource> createLearningResourcesList(
			String groupName) {

		learningResourcesList = new MyList<>(new ListConfiguration<>(
				UserInformation.LEARNING_RESOURCE_DELETE,
				new LearningResourceRow(applicationController, groupName),
				LearningResource.getInitializer(),
				Titles.LEARNING_RESOURCES_LIST,
				applicationController.getApplicationWindow(),
				applicationController).showButtonsLoadNextPreviousWords(false));
		applicationController.addResourcesGroup(groupName,
				learningResourcesList);
		buttonRemoveResourcesGroup = GuiElementsCreator.createButtonLikeComponent(
				UIElementsStyles.buttonStyle()
								.text(Buttons.REMOVE_RESOURCE_GROUP));
		learningResourcesList.addButtonWithHotkey(buttonRemoveResourcesGroup,
				KeyModifiers.CONTROL, KeyEvent.VK_G,
				actionsCreator.createActionRemoveResourceGroup(groupName),
				HotkeysDescription.REMOVE_RESOURCES_GROUP);
		buttonOpenAllResourcesFromGroup = GuiElementsCreator.createButtonLikeComponent(
				UIElementsStyles.buttonStyle()
								.text(Buttons.OPEN_ALL_RESOURCES_IN_GROUP));
		learningResourcesList.addButtonWithHotkey(buttonOpenAllResourcesFromGroup,
				KeyModifiers.CONTROL, KeyEvent.VK_D,
				actionsCreator.createActionBrowseAllResources(
						learningResourcesList),
				HotkeysDescription.OPEN_ALL_RESOURCES_IN_GROUP);
		return learningResourcesList;

	}

	public JTabbedPane getTabPane() {
		return tabPane;
	}

	public JTextComponent getInputResourcesGroupName() {
		return resourcesGroupNameInput;
	}

	public ApplicationController getApplicationController() {
		return applicationController;
	}


	public AbstractButton getButtonAddResourcesGroup() {
		return buttonAddResourcesGroup;
	}

	public AbstractButton getButtonLoadLastUsedFile() {
		return buttonLoadLastUsedFile;
	}

	public AbstractButton getButtonSave() {
		return buttonSave;
	}

	public AbstractButton getButtonLoad() {
		return buttonLoad;
	}

	public JLabel getLabelResourcesGroup() {
		return labelResourcesGroup;
	}

	public JLabel getTitleLabel() {
		return titleLabel;
	}

}
