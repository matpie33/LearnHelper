package com.learningHelper.uiElementsActionsCreators;

import com.guimaker.list.myList.MyList;
import com.learningHelper.application.ApplicationController;
import com.learningHelper.model.LearningResource;
import com.learningHelper.panelsUpdaters.StartingPanelUpdater;
import com.learningHelper.uiElementsCreators.StartingPanelElementsCreator;
import com.learningHelper.webBrowsing.WebBrowser;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class StartingPanelActionsCreator {
	private ApplicationController applicationController;
	private StartingPanelUpdater panelUpdater;
	private StartingPanelElementsCreator elementsCreator;

	public StartingPanelActionsCreator(
			ApplicationController applicationController,
			StartingPanelUpdater panelUpdater,
			StartingPanelElementsCreator elementsCreator) {
		this.panelUpdater = panelUpdater;
		this.elementsCreator = elementsCreator;
		this.applicationController = applicationController;
	}

	public AbstractAction createActionAddGroupResource() {
		return new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String groupName = elementsCreator.createResourcesGroupNameInput()
												  .getText();
				MyList<LearningResource> learningResourcesList = elementsCreator.createLearningResourcesList(
						groupName);
				applicationController.addResourcesGroup(groupName,
						learningResourcesList);
				panelUpdater.addLearningResourcesGroupToTabPane(groupName,
						learningResourcesList);
				learningResourcesList.addWord(new LearningResource());
			}
		};
	}

	public AbstractAction createActionBrowseAllResources(
			MyList<LearningResource> resources) {
		return new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				WebBrowser.browseAllResources(resources.getWords());
			}
		};
	}

	public AbstractAction openSaveDialog() {
		return new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				applicationController.openSaveDialog();
			}
		};
	}

	public AbstractAction openLoadFileDialog() {
		return new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				applicationController.openLoadFileDialog();
			}
		};
	}
}
