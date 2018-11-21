package com.learningHelper.application;

import com.guimaker.application.ApplicationChangesManager;
import com.guimaker.application.ApplicationWindow;
import com.guimaker.list.myList.MyList;
import com.learningHelper.model.LearningResource;
import com.learningHelper.panels.StartingPanel;

import java.util.HashMap;
import java.util.Map;

public class ApplicationController implements ApplicationChangesManager {

	private ApplicationWindow applicationWindow;
	private ApplicationConfigurationHolder applicationConfigurationHolder;
	private StartingPanel startingPanel;
	private Map<String, MyList<LearningResource>> nameToLearningResourcesGroupMap = new HashMap<>();

	public ApplicationController() {
		startingPanel = new StartingPanel(this);
		applicationConfigurationHolder = new ApplicationConfigurationHolder();
		applicationWindow = new ApplicationWindow(this, startingPanel,
				applicationConfigurationHolder.getApplicationConfiguration());
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

	}

	@Override
	public ApplicationWindow getApplicationWindow() {
		return applicationWindow;
	}

	public void addResource(String groupName,
			MyList<LearningResource> learningResourcesList) {
		nameToLearningResourcesGroupMap.put(groupName, learningResourcesList);
	}

	public MyList<LearningResource> getLearningResourcesGroup(
			String learningResourcesGroupName) {
		return nameToLearningResourcesGroupMap.get(learningResourcesGroupName);
	}
}
