package com.learningHelper.listRow;

import com.guimaker.enums.InputGoal;
import com.guimaker.list.ListRowData;
import com.guimaker.list.myList.ListRowCreator;
import com.guimaker.model.CommonListElements;
import com.guimaker.model.PanelConfiguration;
import com.guimaker.panels.MainPanel;
import com.learningHelper.application.ApplicationController;
import com.learningHelper.model.LearningResource;

public class LearningResourceRow implements ListRowCreator<LearningResource> {

	private ResourceRow defaultLearningResourceRowCreator;

	public LearningResourceRow(ApplicationController applicationController,
			String groupName) {
		defaultLearningResourceRowCreator = new WebHelperResourceRow(
				applicationController, groupName);
	}

	@Override
	public ListRowData<LearningResource> createListRow(
			LearningResource learningResource,
			CommonListElements<LearningResource> commonListElements,
			InputGoal inputGoal) {
		MainPanel panel = new MainPanel(new PanelConfiguration());
		return defaultLearningResourceRowCreator.addElementsToPanel(
				learningResource, panel, commonListElements);
	}

}
