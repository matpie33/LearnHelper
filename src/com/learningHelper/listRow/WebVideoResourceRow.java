package com.learningHelper.listRow;

import com.guimaker.enums.Anchor;
import com.guimaker.enums.FillType;
import com.guimaker.model.CommonListElements;
import com.guimaker.panels.MainPanel;
import com.guimaker.row.SimpleRowBuilder;
import com.learningHelper.application.ApplicationController;
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
	public void addElementsToPanel(LearningResource learningResource, MainPanel panel,
			CommonListElements commonListElements) {
		LearningResourceRowElementsCreator elementsCreator = new LearningResourceRowElementsCreator(
				applicationController, learningResourcesGroupName);
		panel.addRowsOfElementsInColumn(
				SimpleRowBuilder.createRowStartingFromColumn(0, FillType.NONE,
						Anchor.WEST, commonListElements.getRowNumberLabel(),
						elementsCreator.getLabelResourceType(),
						elementsCreator.getComboboxResourceType(
								learningResource, panel,
								commonListElements))
								.nextRow(elementsCreator.getLabelResourceTag(),
										elementsCreator.getInputResourceTag(
												learningResource.getTag(),
												learningResource))
								.setColumnToPutRowInto(1)
								.nextRow(elementsCreator.getLabelResourceLocations(),
										elementsCreator.getInputResourceLocation(),
										elementsCreator.getButtonIncreaseVideoNumber(),
										elementsCreator.getButtonAddAlternativeLocation(
												panel))
								.nextRow(
										elementsCreator.getLabelStoppedPlace(),
										elementsCreator.getInputStoppedPlaceTimeRangeStart(),
										elementsCreator.getInputStoppedPlaceTimeRangeEnd())
								.nextRow(
										elementsCreator.getButtonGoToResource())
								.nextRow(commonListElements.getButtonDelete()));
	}
}
