package com.learningHelper.panelsUpdaters;

import com.guimaker.model.CommonListElements;
import com.guimaker.panels.MainPanel;
import com.learningHelper.enums.LearningResourceType;
import com.learningHelper.listRow.ResourceRow;
import com.learningHelper.listRow.WebHelperResourceRow;
import com.learningHelper.listRow.WebVideoResourceRow;

import java.util.HashMap;
import java.util.Map;

public class LearningResourceRowUpdater {
	private Map<LearningResourceType, ResourceRow> resourceTypeToRowMap = new HashMap<>();

	public LearningResourceRowUpdater() {
		resourceTypeToRowMap.put(LearningResourceType.WEB_HELPER_RESOURCE,
				new WebHelperResourceRow());
		resourceTypeToRowMap.put(LearningResourceType.WEB_VIDEO,
				new WebVideoResourceRow());
	}

	public void changeResourceRowType(MainPanel panel, LearningResourceType type,
			CommonListElements commonListElements) {
		panel.clear();
		resourceTypeToRowMap.get(type)
							.addElementsToPanel(panel, commonListElements);
		panel.updateView();

	}
}
