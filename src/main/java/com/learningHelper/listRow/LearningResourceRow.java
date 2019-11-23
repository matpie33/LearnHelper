package com.learningHelper.listRow;

import com.guimaker.enums.InputGoal;
import com.guimaker.list.ListRowData;
import com.guimaker.list.myList.ListRowCreator;
import com.guimaker.model.CommonListElements;
import com.guimaker.model.PanelConfiguration;
import com.guimaker.panels.mainPanel.MainPanel;
import com.learningHelper.application.ApplicationController;
import com.learningHelper.model.LearningResource;
import com.learningHelper.panelsUpdaters.LearningResourceRowUpdater;

import java.awt.*;

public class LearningResourceRow implements ListRowCreator<LearningResource> {

	private LearningResourceRowUpdater learningResourceRowUpdater;
	private ApplicationController applicationController;

	public LearningResourceRow(ApplicationController applicationController,
			String groupName) {
		learningResourceRowUpdater = new LearningResourceRowUpdater(
				applicationController, groupName);
		this.applicationController = applicationController;
	}

	@Override
	public ListRowData<LearningResource> createListRow(
			LearningResource learningResource,
			CommonListElements<LearningResource> commonListElements,
			InputGoal inputGoal) {
		Color contentPanelColor = applicationController.getApplicationConfigurationHolder()
													   .getApplicationConfiguration()
													   .getContentPanelColor();
		MainPanel panel = new MainPanel(
				new PanelConfiguration().setColorToUse(contentPanelColor));

		return learningResourceRowUpdater.getResourceRowForType(
				learningResource.getType())
										 .addElementsToPanel(learningResource,
												 panel, commonListElements);
	}

}
