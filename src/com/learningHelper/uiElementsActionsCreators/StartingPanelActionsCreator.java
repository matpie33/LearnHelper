package com.learningHelper.uiElementsActionsCreators;

import com.learningHelper.application.ResourcesHolder;
import com.learningHelper.model.GrouppedLearningSources;
import com.learningHelper.panelsUpdaters.StartingPanelUpdater;
import com.learningHelper.uiElementsCreators.StartingPanelElementsCreator;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class StartingPanelActionsCreator {
	private ResourcesHolder resourcesHolder;
	private StartingPanelUpdater panelUpdater;
	private StartingPanelElementsCreator elementsCreator;

	public StartingPanelActionsCreator(ResourcesHolder resourcesHolder,
			StartingPanelUpdater panelUpdater,
			StartingPanelElementsCreator elementsCreator) {
		this.resourcesHolder = resourcesHolder;
		this.panelUpdater = panelUpdater;
		this.elementsCreator = elementsCreator;
	}

	public AbstractAction createActionAddGroupResource() {
		return new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resourcesHolder.addResource(new GrouppedLearningSources());
				JTextField groupResourceNameInput = elementsCreator.getResourcesGroupNameInput();
				panelUpdater.addLearningSourcesGroup(
						groupResourceNameInput.getText());
			}
		};
	}
}
