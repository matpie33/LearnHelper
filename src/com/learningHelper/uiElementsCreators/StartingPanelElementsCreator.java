package com.learningHelper.uiElementsCreators;

import com.guimaker.panels.GuiElementsCreator;
import com.learningHelper.application.ResourcesHolder;
import com.learningHelper.panelsUpdaters.StartingPanelUpdater;
import com.learningHelper.uiElementsActionsCreators.StartingPanelActionsCreator;
import com.learningHelper.uiElementsStyles.UIElementsStyles;
import com.learningHelper.uiElementsTexts.Buttons;
import com.learningHelper.uiElementsTexts.Labels;
import com.learningHelper.uiElementsTexts.Titles;

import javax.swing.*;

public class StartingPanelElementsCreator {

	private StartingPanelActionsCreator actionsCreator;
	private JTabbedPane tabPane;
	private JTextField resourcesGroupName;

	public StartingPanelElementsCreator(ResourcesHolder resourcesHolder) {
		actionsCreator = new StartingPanelActionsCreator(
				resourcesHolder, new StartingPanelUpdater(this), this);
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
								.text(Buttons.ADD_GROUP_RESOURCE),
				actionsCreator.createActionAddGroupResource());
	}

	public JTextField getResourcesGroupNameInput() {
		if (resourcesGroupName == null) {
			resourcesGroupName = GuiElementsCreator.createTextField(
					UIElementsStyles.shortTextInputStyle());
		}
		return resourcesGroupName;
	}

}
