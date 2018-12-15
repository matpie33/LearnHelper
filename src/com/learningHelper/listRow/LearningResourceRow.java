package com.learningHelper.listRow;

import com.guimaker.enums.InputGoal;
import com.guimaker.list.ListRowData;
import com.guimaker.list.myList.ListRowCreator;
import com.guimaker.model.CommonListElements;
import com.guimaker.model.PanelConfiguration;
import com.guimaker.panels.MainPanel;
import com.learningHelper.application.ApplicationController;
import com.learningHelper.model.LearningResource;
import com.learningHelper.panelsUpdaters.LearningResourceRowUpdater;

public class LearningResourceRow implements ListRowCreator<LearningResource> {

	private LearningResourceRowUpdater learningResourceRowUpdater;

	public LearningResourceRow(ApplicationController applicationController,
			String groupName) {
		learningResourceRowUpdater = new LearningResourceRowUpdater(
				applicationController, groupName);
	}

	@Override
	public ListRowData<LearningResource> createListRow(
			LearningResource learningResource,
			CommonListElements<LearningResource> commonListElements,
			InputGoal inputGoal) {
		MainPanel panel = new MainPanel(new PanelConfiguration());
		return learningResourceRowUpdater.getResourceRowForType(
				learningResource.getType())
										 .addElementsToPanel(learningResource,
												 panel, commonListElements);
	}

}
