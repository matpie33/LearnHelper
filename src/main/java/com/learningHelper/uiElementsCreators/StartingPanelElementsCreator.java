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
	private JTextComponent resourcesGroupName;
	private ApplicationController applicationController;
	private StartingPanel startingPanel;

	public StartingPanelElementsCreator(
			ApplicationController applicationController,
			StartingPanel startingPanel) {
		actionsCreator = new StartingPanelActionsCreator(applicationController,
				new StartingPanelUpdater(this), this);
		this.applicationController = applicationController;
		this.startingPanel = startingPanel;
	}

	public JLabel createTitleLabel() {
		return GuiElementsCreator.createLabel(UIElementsStyles.titleLabelStyle()
															  .text(Titles.APPLICATION_TITLE));
	}

	public JTabbedPane getTabPane() {
		if (tabPane == null) {
			tabPane = GuiElementsCreator.createTabbedPane();
			tabPane.setBackground(BasicColors.BLUE_NORMAL_7);
			startingPanel.addHotkey(KeyModifiers.CONTROL, KeyEvent.VK_W,
					actionsCreator.createActionSwitchTabs(tabPane), tabPane,
					HotkeysDescription.SWITCH_TABS);
		}
		return tabPane;

	}

	private void addButtonRemoveResourcesGroup(String groupName,
			MyList<LearningResource> learningResources) {
		learningResources.addButtonWithHotkey(
				GuiElementsCreator.createButtonLikeComponent(
						UIElementsStyles.buttonStyle()
										.text(Buttons.REMOVE_RESOURCE_GROUP)),
				KeyModifiers.CONTROL, KeyEvent.VK_G,
				actionsCreator.createActionRemoveResourceGroup(groupName),
				HotkeysDescription.REMOVE_RESOURCES_GROUP);
	}

	public JLabel createNoResourcesLabel() {
		return GuiElementsCreator.createLabel(
				UIElementsStyles.informationLabelStyle()
								.text(Labels.NO_RESOURCES));
	}

	public AbstractButton createButtonAddResourcesGroup() {
		AbstractButton button = GuiElementsCreator.createButtonLikeComponent(
				UIElementsStyles.buttonStyle()
								.text(Buttons.ADD));
		startingPanel.addHotkey(KeyEvent.VK_ENTER,
				actionsCreator.createActionAddGroupResource(), button,
				HotkeysDescription.ADD_RESOURCE_GROUP);
		return button;
	}

	public AbstractButton createButtonLoadLastUsedFile() {
		AbstractButton button = GuiElementsCreator.createButtonLikeComponent(
				UIElementsStyles.buttonStyle()
								.text(Buttons.LOAD_LAST_USED_FILE));
		startingPanel.addHotkey(KeyModifiers.CONTROL, KeyEvent.VK_E,
				actionsCreator.createActionOpenLastUsedFile(), button,
				HotkeysDescription.OPEN_LAST_USED_FILE);
		return button;
	}

	public JTextComponent getResourcesGroupNameInput() {
		if (resourcesGroupName == null) {
			resourcesGroupName = GuiElementsCreator.createTextArea(
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
		addButtonOpenAllResourcesFromGroup(learningResourcesList);
		addButtonRemoveResourcesGroup(groupName, learningResourcesList);
		return learningResourcesList;

	}

	private void addButtonOpenAllResourcesFromGroup(
			MyList<LearningResource> learningResources) {
		learningResources.addButtonWithHotkey(
				GuiElementsCreator.createButtonLikeComponent(
						UIElementsStyles.buttonStyle()
										.text(Buttons.OPEN_ALL_RESOURCES_IN_GROUP)),
				KeyModifiers.CONTROL, KeyEvent.VK_D,
				actionsCreator.createActionBrowseAllResources(
						learningResources),
				HotkeysDescription.OPEN_ALL_RESOURCES_IN_GROUP);
	}

	public AbstractButton createButtonSave() {
		AbstractButton button = GuiElementsCreator.createButtonLikeComponent(
				UIElementsStyles.buttonStyle()
								.text(Buttons.SAVE));
		startingPanel.addHotkey(KeyModifiers.CONTROL, KeyEvent.VK_S,
				actionsCreator.openSaveDialog(), button,
				HotkeysDescription.SAVE);
		return button;
	}

	public AbstractButton createButtonLoad() {
		AbstractButton button = GuiElementsCreator.createButtonLikeComponent(
				new ButtonOptions(ButtonType.BUTTON).text(Buttons.LOAD));
		startingPanel.addHotkey(KeyModifiers.CONTROL, KeyEvent.VK_Q,
				actionsCreator.openLoadFileDialog(), button,
				HotkeysDescription.LOAD);
		return button;
	}

}
