package com.learningHelper.listRow;

import com.guimaker.enums.Anchor;
import com.guimaker.enums.FillType;
import com.guimaker.model.CommonListElements;
import com.guimaker.panels.MainPanel;
import com.guimaker.row.SimpleRowBuilder;
import com.learningHelper.enums.LearningResourceType;
import com.learningHelper.uiElementsCreators.LearningResourceRowElementsCreator;

public class WebVideoResourceRow implements ResourceRow {

	@Override
	public void addElementsToPanel(MainPanel panel,
			CommonListElements commonListElements) {
		LearningResourceRowElementsCreator elementsCreator = new LearningResourceRowElementsCreator();
		panel.addRowsOfElementsInColumn(
				SimpleRowBuilder.createRowStartingFromColumn(0, FillType.NONE,
						Anchor.WEST, commonListElements.getRowNumberLabel(),
						elementsCreator.getLabelResourceType(),
						elementsCreator.getComboboxResourceType(
								LearningResourceType.WEB_VIDEO, panel,
								commonListElements))
								.nextRow(elementsCreator.getLabelResourceTag(),
										elementsCreator.getInputResourceTag())
								.setColumnToPutRowInto(1)
								.nextRow(elementsCreator.getLabelResourceLocations(),
										elementsCreator.getInputResourceLocation(),
										elementsCreator.getButtonIncreaseVideoNumber(),
										elementsCreator.getButtonAddAlternativeLocation())
								.nextRow(
										elementsCreator.getLabelStoppedPlace(),
										elementsCreator.getInputStoppedPlaceTimeRangeStart(),
										elementsCreator.getInputStoppedPlaceTimeRangeEnd())
								.nextRow(
										elementsCreator.getButtonGoToResource())
								.nextRow(commonListElements.getButtonDelete()));
	}
}
