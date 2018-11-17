package com.learningHelper.application;

import com.guimaker.application.ApplicationChangesManager;
import com.guimaker.application.ApplicationWindow;
import com.learningHelper.panels.StartingPanel;

public class ApplicationController implements ApplicationChangesManager {

	private ApplicationWindow applicationWindow;
	private ApplicationConfigurationHolder applicationConfigurationHolder;
	private StartingPanel startingPanel;
	private ResourcesHolder resourcesHolder;

	public ApplicationController() {
		resourcesHolder = new ResourcesHolder();
		startingPanel = new StartingPanel(resourcesHolder, this);
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
}
