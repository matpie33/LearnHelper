package com.learningHelper.application;

import com.guimaker.application.ApplicationChangesManager;
import com.guimaker.application.ApplicationWindow;
import com.guimaker.list.myList.MyList;
import com.learningHelper.model.GroupOfLearningResources;
import com.learningHelper.model.LearningResource;
import com.learningHelper.modelConversion.GrouppedResourcesConverter;
import com.learningHelper.panels.StartingPanel;
import com.learningHelper.saving.LoadAndSave;
import com.learningHelper.uiElementsTexts.Exceptions;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ApplicationController implements ApplicationChangesManager {

	private ApplicationWindow applicationWindow;
	private ApplicationConfigurationHolder applicationConfigurationHolder;
	private StartingPanel startingPanel;
	private Map<String, MyList<LearningResource>> nameToLearningResourcesGroupMap = new LinkedHashMap<>();
	private LoadAndSave loadAndSave;

	public ApplicationController() {
		startingPanel = new StartingPanel(this);
		applicationConfigurationHolder = new ApplicationConfigurationHolder();
		applicationWindow = new ApplicationWindow(this, startingPanel,
				applicationConfigurationHolder.getApplicationConfiguration());
		loadAndSave = new LoadAndSave(applicationWindow.getContainer());
	}

	public void start() {
		applicationWindow.initiate();
	}

	@Override
	public boolean isClosingSafe() {
		return true;
	}

	@Override
	public void save() {
		try {
			loadAndSave.save(
					GrouppedResourcesConverter.convertMapOfResourcesToListOfGroups(
							nameToLearningResourcesGroupMap));
		}
		catch (IOException e) {
			e.printStackTrace();
			applicationWindow.showMessageDialog(Exceptions.FAILED_TO_SAVE_FILE);
		}
	}

	@Override
	public ApplicationWindow getApplicationWindow() {
		return applicationWindow;
	}

	public void addResourcesGroup(String groupName,
			MyList<LearningResource> learningResourcesList) {
		nameToLearningResourcesGroupMap.put(groupName, learningResourcesList);
	}

	public MyList<LearningResource> getLearningResourcesGroup(
			String learningResourcesGroupName) {
		return nameToLearningResourcesGroupMap.get(learningResourcesGroupName);
	}

	public void openSaveDialog() {
		try {
			loadAndSave.createNewFileAndSave(
					GrouppedResourcesConverter.convertMapOfResourcesToListOfGroups(
							nameToLearningResourcesGroupMap));
		}
		catch (IOException e) {
			e.printStackTrace();
			applicationWindow.showMessageDialog(Exceptions.FAILED_TO_SAVE_FILE);
		}
	}

	public void openLoadFileDialog() {
		List<GroupOfLearningResources> groupsOfLearningResources = loadAndSave.showLoadFileDialog();
		if (!groupsOfLearningResources.isEmpty()) {
			startingPanel.clearResourcesGroupTabs();
			nameToLearningResourcesGroupMap.clear();
			startingPanel.addLearningResourcesGroups(groupsOfLearningResources);
		}

	}

}
