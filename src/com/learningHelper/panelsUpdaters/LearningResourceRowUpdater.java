package com.learningHelper.panelsUpdaters;

import com.guimaker.enums.FillType;
import com.guimaker.model.CommonListElements;
import com.guimaker.panels.MainPanel;
import com.guimaker.row.SimpleRowBuilder;
import com.learningHelper.application.ApplicationController;
import com.learningHelper.enums.LearningResourceType;
import com.learningHelper.listRow.ResourceRow;
import com.learningHelper.listRow.WebHelperResourceRow;
import com.learningHelper.listRow.WebTextResourceRow;
import com.learningHelper.listRow.WebVideoResourceRow;
import com.learningHelper.model.LearningResource;
import com.learningHelper.uiElementsCreators.LearningResourceRowElementsCreator;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class LearningResourceRowUpdater {
	private Map<LearningResourceType, ResourceRow> resourceTypeToRowMap = new HashMap<>();
	private LearningResourceRowElementsCreator elementsCreator;

	public LearningResourceRowUpdater(
			ApplicationController applicationController,
			String learningResourcesGroupName,
			LearningResourceRowElementsCreator elementsCreator) {
		resourceTypeToRowMap.put(LearningResourceType.WEB_HELPER_RESOURCE,
				new WebHelperResourceRow(applicationController,
						learningResourcesGroupName));
		resourceTypeToRowMap.put(LearningResourceType.WEB_VIDEO,
				new WebVideoResourceRow(applicationController,
						learningResourcesGroupName));
		resourceTypeToRowMap.put(LearningResourceType.WEB_TEXT_RESOURCE,
				new WebTextResourceRow(applicationController,
						learningResourcesGroupName));
		this.elementsCreator = elementsCreator;
	}

	public void changeResourceRowType(LearningResource learningResource,
			MainPanel panel, LearningResourceType type,
			CommonListElements commonListElements) {
		panel.clear();
		resourceTypeToRowMap.get(type)
							.addElementsToPanel(learningResource, panel,
									commonListElements);
		panel.updateView();

	}

}
