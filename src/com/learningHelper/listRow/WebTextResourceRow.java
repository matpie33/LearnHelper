package com.learningHelper.listRow;

import com.guimaker.enums.FillType;
import com.guimaker.list.ListRowData;
import com.guimaker.model.CommonListElements;
import com.guimaker.panels.MainPanel;
import com.guimaker.row.SimpleRowBuilder;
import com.learningHelper.application.ApplicationController;
import com.learningHelper.listRowDataCreators.LearningResourceRowDataCreator;
import com.learningHelper.model.LearningResource;
import com.learningHelper.uiElementsCreators.LearningResourceRowElementsCreator;

public class WebTextResourceRow implements ResourceRow {

	private String learningResourcesGroupName;
	private ApplicationController applicationController;

	public WebTextResourceRow(ApplicationController applicationController,
			String learningResourcesGroupName) {
		this.learningResourcesGroupName = learningResourcesGroupName;
		this.applicationController = applicationController;
	}

	@Override
	public ListRowData<LearningResource> addElementsToPanel(LearningResource learningResource,
			MainPanel panel, CommonListElements commonListElements) {
		LearningResourceRowDataCreator rowDataCreator = new LearningResourceRowDataCreator(
				applicationController, learningResourcesGroupName);
		LearningResourceRowElementsCreator elementsCreator = rowDataCreator.getElementsCreator();
		panel.addRowsOfElementsInColumn(
				SimpleRowBuilder.createRowStartingFromColumn(0, FillType.NONE,
						commonListElements.getRowNumberLabel(),
						elementsCreator.getLabelResourceType(),
						elementsCreator.getComboboxResourceType(
								learningResource, panel, commonListElements))
								.nextRow(elementsCreator.getLabelResourceTag(),
										elementsCreator.getInputResourceTag(
												learningResource.getTag(),
												learningResource,
												commonListElements))
								.setColumnToPutRowInto(1)
								.nextRow(FillType.HORIZONTAL,
										elementsCreator.getLabelResourceLocations(),
										elementsCreator.createResourceLocations(
												learningResource)
													   .getPanel())
								.nextRow(elementsCreator.getLabelStoppedPlace(),
										elementsCreator.getTextInputStoppedPlace())
								.nextRow(elementsCreator.getButtonGoToResource(
										learningResource))
								.nextRow(commonListElements.getButtonDelete()));
		return rowDataCreator.createRowData(panel);
	}
}
