package com.learningHelper.uiElementsCreators;

import com.guimaker.list.myList.ListConfiguration;
import com.guimaker.list.myList.MyList;
import com.guimaker.panels.GuiElementsCreator;
import com.learningHelper.application.ApplicationController;
import com.learningHelper.application.ResourcesHolder;
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

public class StartingPanelElementsCreator {

	private StartingPanelActionsCreator actionsCreator;
	private JTabbedPane tabPane;
	private JTextField resourcesGroupName;
	private ApplicationController applicationController;

	public StartingPanelElementsCreator(ResourcesHolder resourcesHolder,
			ApplicationController applicationController) {
		actionsCreator = new StartingPanelActionsCreator(resourcesHolder,
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

	public MyList<LearningResource> createLearningResourcesList() {
		MyList<LearningResource> learningResourcesList = new MyList<>(
				new ListConfiguration<>(
						UserInformation.LEARNING_RESOURCE_DELETE,
						new LearningResourceRow(),
						LearningResource.getInitializer(),
						Titles.LEARNING_RESOURCES_LIST,
						applicationController.getApplicationWindow(),
						applicationController)
						.showButtonsLoadNextPreviousWords(false));
		learningResourcesList.addWord(new LearningResource());
		return learningResourcesList;
	}

}
