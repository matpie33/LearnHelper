package com.learningHelper.listRow;

import com.guimaker.enums.InputGoal;
import com.guimaker.list.ListRowData;
import com.guimaker.list.myList.ListRowCreator;
import com.guimaker.listeners.InputValidationListener;
import com.guimaker.model.CommonListElements;
import com.guimaker.model.PanelConfiguration;
import com.guimaker.panels.MainPanel;
import com.learningHelper.model.LearningResource;

public class LearningResourceRow implements ListRowCreator<LearningResource> {
	@Override
	public ListRowData<LearningResource> createListRow(
			LearningResource learningResource,
			CommonListElements commonListElements, InputGoal inputGoal) {
		return new ListRowData<>(new MainPanel(new PanelConfiguration()));
	}

	@Override
	public void addValidationListener(
			InputValidationListener<LearningResource> inputValidationListener) {

	}
}
