package com.learningHelper.listRowDataCreators;

import com.guimaker.list.ListPropertyInformation;
import com.guimaker.list.ListRowData;
import com.guimaker.panels.mainPanel.MainPanel;
import com.learningHelper.application.ApplicationController;
import com.learningHelper.model.LearningResource;
import com.learningHelper.uiElementsActionsCreators.LearningResourceRowActionsCreator;
import com.learningHelper.uiElementsCreators.LearningResourceRowElementsCreator;
import com.learningHelper.uiElementsTexts.FilterProperties;

public class LearningResourceRowDataCreator {

	private LearningResourceRowElementsCreator elementsCreator;
	private LearningResourceRowActionsCreator actionsCreator;

	public LearningResourceRowDataCreator(
			ApplicationController applicationController,
			String learningResourceGroupName) {
		this.actionsCreator = new LearningResourceRowActionsCreator(
				applicationController, learningResourceGroupName);
		this.elementsCreator = new LearningResourceRowElementsCreator(
				applicationController, learningResourceGroupName,
				actionsCreator);

	}

	public LearningResourceRowElementsCreator getElementsCreator() {
		return elementsCreator;
	}

	public ListRowData<LearningResource> createRowData(MainPanel mainPanel) {
		ListRowData<LearningResource> rowData = new ListRowData<>(mainPanel);
		rowData.addPropertyInformation(FilterProperties.TAG,
				new ListPropertyInformation<>(elementsCreator.getInputResourceTag(),
						actionsCreator.getTagInputPropertyManager()));
		return rowData;
	}

}
