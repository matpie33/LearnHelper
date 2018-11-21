package com.learningHelper.listRow;

import com.guimaker.enums.FillType;
import com.guimaker.model.CommonListElements;
import com.guimaker.panels.MainPanel;
import com.guimaker.row.SimpleRowBuilder;
import com.learningHelper.application.ApplicationController;
import com.learningHelper.enums.LearningResourceType;
import com.learningHelper.model.LearningResource;
import com.learningHelper.uiElementsCreators.LearningResourceRowElementsCreator;

public class WebHelperResourceRow implements ResourceRow {

	private String learningResourcesGroupName;
	private ApplicationController applicationController;

	public WebHelperResourceRow(ApplicationController applicationController,
			String groupName) {
		learningResourcesGroupName = groupName;
		this.applicationController = applicationController;
	}

	@Override
	public void addElementsToPanel(LearningResource learningResource,
			MainPanel panel, CommonListElements commonListElements) {
		LearningResourceRowElementsCreator elementsCreator = new LearningResourceRowElementsCreator(
				applicationController, learningResourcesGroupName);
		panel.addRowsOfElementsInColumn(
				SimpleRowBuilder.createRowStartingFromColumn(0, FillType.NONE,
						commonListElements.getRowNumberLabel(),
						elementsCreator.getLabelResourceType(),
						elementsCreator.getComboboxResourceType(
								learningResource,
								LearningResourceType.WEB_HELPER_RESOURCE, panel,
								commonListElements))
								.nextRow(elementsCreator.getLabelResourceTag(),
										elementsCreator.getInputResourceTag(
												learningResource))
								.setColumnToPutRowInto(1)
								.nextRow(FillType.HORIZONTAL,
										elementsCreator.getLabelResourceLocations(),
										elementsCreator.getInputResourceLocation(),
										elementsCreator.getButtonAddAlternativeLocation())
								.fillHorizontallySomeElements(
										elementsCreator.getInputResourceLocation())
								.nextRow(
										elementsCreator.getButtonGoToResource())
								.nextRow(commonListElements.getButtonDelete()));

	}

}
