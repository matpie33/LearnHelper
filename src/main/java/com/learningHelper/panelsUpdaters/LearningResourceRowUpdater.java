package com.learningHelper.panelsUpdaters;

import com.guimaker.model.CommonListElements;
import com.guimaker.panels.mainPanel.MainPanel;
import com.learningHelper.application.ApplicationController;
import com.learningHelper.enums.LearningResourceType;
import com.learningHelper.listRow.ResourceRow;
import com.learningHelper.listRow.WebHelperResourceRow;
import com.learningHelper.listRow.WebTextResourceRow;
import com.learningHelper.listRow.WebVideoResourceRow;
import com.learningHelper.model.LearningResource;

import java.util.HashMap;
import java.util.Map;

public class LearningResourceRowUpdater {
	private Map<LearningResourceType, ResourceRow> resourceTypeToRowMap = new HashMap<>();

	public LearningResourceRowUpdater(
			ApplicationController applicationController,
			String learningResourcesGroupName) {
		resourceTypeToRowMap.put(LearningResourceType.WEB_HELPER_RESOURCE,
				new WebHelperResourceRow(applicationController,
						learningResourcesGroupName));
		resourceTypeToRowMap.put(LearningResourceType.WEB_VIDEO,
				new WebVideoResourceRow(applicationController,
						learningResourcesGroupName));
		resourceTypeToRowMap.put(LearningResourceType.WEB_TEXT_RESOURCE,
				new WebTextResourceRow(applicationController,
						learningResourcesGroupName));
	}

	public void changeResourceRowType(LearningResource learningResource,
			MainPanel panel, LearningResourceType type,
			CommonListElements<LearningResource> commonListElements) {
		panel.clear();
		resourceTypeToRowMap.get(type)
							.addElementsToPanel(learningResource, panel,
									commonListElements);
		panel.updateView();

	}

	public ResourceRow getResourceRowForType (LearningResourceType
			learningResourceType){
		return resourceTypeToRowMap.get(learningResourceType);
	}

}
