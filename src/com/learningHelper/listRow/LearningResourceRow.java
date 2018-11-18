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

	private WebHelperResourceRow webHelperResourceRow;

	public LearningResourceRow() {
		webHelperResourceRow = new WebHelperResourceRow();
	}

	@Override
	public ListRowData<LearningResource> createListRow(
			LearningResource learningResource,
			CommonListElements commonListElements, InputGoal inputGoal) {
		MainPanel panel = new MainPanel(new PanelConfiguration());
		webHelperResourceRow.addElementsToPanel(panel, commonListElements);
		return new ListRowData<>(panel);
	}

	@Override
	public void addValidationListener(
			InputValidationListener<LearningResource> inputValidationListener) {

	}
}
