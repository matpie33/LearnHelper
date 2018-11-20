package com.learningHelper.listRow;

import com.guimaker.enums.Anchor;
import com.guimaker.enums.FillType;
import com.guimaker.model.CommonListElements;
import com.guimaker.panels.MainPanel;
import com.guimaker.row.SimpleRowBuilder;
import com.learningHelper.enums.LearningResourceType;
import com.learningHelper.uiElementsCreators.ResourcesListElementsCreator;

public class WebVideoResourceRow implements ResourceRow {

	@Override
	public void addElementsToPanel(MainPanel panel,
			CommonListElements commonListElements) {
		ResourcesListElementsCreator elementsCreator = new ResourcesListElementsCreator();
		panel.addRowsOfElementsInColumn(
				SimpleRowBuilder.createRowStartingFromColumn(0, FillType.NONE,
						Anchor.WEST, commonListElements.getRowNumberLabel(),
						elementsCreator.createTypeLabel(),
						elementsCreator.createResourceTypeCombobox(
								LearningResourceType.WEB_VIDEO, panel,
								commonListElements))
								.nextRow(elementsCreator.createTagLabel(),
										elementsCreator.getTagInput())
								.setColumnToPutRowInto(1)
								.nextRow(elementsCreator.createLocationLabel(),
										elementsCreator.getLocationInput(),
										elementsCreator.createIncreaseVideoNumberButton(),
										elementsCreator.createAddAlternativeLocationButton())
								.nextRow(
										elementsCreator.createStoppedPlaceLabel(),
										elementsCreator.createStoppedPlaceTimeRangeStart(),
										elementsCreator.createStoppedPlaceTimeRangeEnd())
								.nextRow(
										elementsCreator.createGoToResourceButton())
								.nextRow(commonListElements.getButtonDelete()));
	}
}
