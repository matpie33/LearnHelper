package com.learningHelper.listRow;

import com.guimaker.enums.Anchor;
import com.guimaker.enums.FillType;
import com.guimaker.list.ListRowData;
import com.guimaker.model.CommonListElements;
import com.guimaker.panels.MainPanel;
import com.guimaker.row.SimpleRowBuilder;
import com.learningHelper.application.ApplicationController;
import com.learningHelper.listRowDataCreators.LearningResourceRowDataCreator;
import com.learningHelper.model.LearningResource;
import com.learningHelper.uiElementsCreators.LearningResourceRowElementsCreator;

public class WebVideoResourceRow implements ResourceRow {

	private String learningResourcesGroupName;
	private ApplicationController applicationController;

	public WebVideoResourceRow(ApplicationController applicationController,
			String learningResourcesGroupName) {
		this.learningResourcesGroupName = learningResourcesGroupName;
		this.applicationController = applicationController;
	}

	@Override
	public ListRowData<LearningResource> addElementsToPanel(
			LearningResource learningResource, MainPanel panel,
			CommonListElements<LearningResource> commonListElements) {
		LearningResourceRowDataCreator rowDataCreator = new LearningResourceRowDataCreator(
				applicationController, learningResourcesGroupName);
		LearningResourceRowElementsCreator elementsCreator = rowDataCreator.getElementsCreator();
		panel.addRowsOfElementsInColumn(
				SimpleRowBuilder.createRowStartingFromColumn(0, FillType.NONE,
						Anchor.WEST, commonListElements.getRowNumberLabel(),
						elementsCreator.createLabelResourceType(),
						elementsCreator.createComboboxResourceType(
								learningResource, panel, commonListElements))
								.nextRow(
										elementsCreator.createLabelResourceTag(),
										elementsCreator.createInputResourceTag(
												learningResource.getTag(),
												learningResource,
												commonListElements))
								.setColumnToPutRowInto(1)
								.nextRow(FillType.HORIZONTAL,
										elementsCreator.createLabelResourceLocations(),
										elementsCreator.createResourceLocations(
												learningResource)
													   .getPanel())
								.nextRow(
										elementsCreator.createLabelStoppedPlace(),
										elementsCreator.createInputStoppedPlaceTimeRangeStart(),
										elementsCreator.createInputStoppedPlaceTimeRangeEnd())
								.inSameColumn(
										elementsCreator.getTimeRangeStartInput(),
										elementsCreator.getTimeRangeEndInput())
								.nextRow(
										elementsCreator.createButtonGoToResource(
												learningResource))
								.nextRow(commonListElements.getButtonDelete()));
		return rowDataCreator.createRowData(panel);
	}
}
