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
		elementsCreator.createElements(learningResource, panel,
				commonListElements);
		panel.addRowsOfElementsInColumn(
				SimpleRowBuilder.createRowStartingFromColumn(0, FillType.NONE,
						Anchor.WEST, commonListElements.getRowNumberLabel(),
						elementsCreator.getLabelResourceType(),
						elementsCreator.getComboboxResourceType())
								.nextRow(
										elementsCreator.getCheckboxIsNarutoVideoLink())
								.setColumnToPutRowInto(1)
								.nextRow(
										elementsCreator.getCheckboxSkipChoosingVideoPlayerType())
								.nextRow(elementsCreator.getLabelResourceTag(),
										elementsCreator.getInputResourceTag())
								.nextRow(FillType.HORIZONTAL,
										elementsCreator.getLabelResourceLocations(),
										elementsCreator.getResourceLocationsList()
													   .getPanel())
								.nextRow(elementsCreator.getLabelStoppedPlace(),
										elementsCreator.getVideoMinuteInput(),
										elementsCreator.getVideoSecondInput())

								.inSameColumn(
										elementsCreator.getVideoMinuteInput(),
										elementsCreator.getVideoSecondInput())
								.nextRow(
										elementsCreator.getButtonGoToResource())
								.onlyAddIf(!commonListElements
										.isForSingleRowOnly())
								.nextRow(commonListElements.getButtonDelete()));
		return rowDataCreator.createRowData(panel);
	}
}
