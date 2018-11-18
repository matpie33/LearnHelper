package com.learningHelper.listRow;

import com.guimaker.enums.FillType;
import com.guimaker.model.CommonListElements;
import com.guimaker.panels.MainPanel;
import com.guimaker.row.SimpleRowBuilder;
import com.learningHelper.uiElementsCreators.ResourcesListElementsCreator;

public class WebHelperResourceRow {

	private ResourcesListElementsCreator elementsCreator;

	public WebHelperResourceRow() {
		elementsCreator = new ResourcesListElementsCreator();
	}

	public void addElementsToPanel(MainPanel panel,
			CommonListElements commonListElements) {
		panel.addRowsOfElementsInColumn(
				SimpleRowBuilder.createRowStartingFromColumn(0, FillType.NONE,
						commonListElements.getRowNumberLabel(),
						elementsCreator.createTypeLabel(),
						elementsCreator.createTypeCombobox())
								.nextRow(elementsCreator.createTagLabel(),
										elementsCreator.getTagInput())
								.setColumnToPutRowInto(1)
								.nextRow(FillType.HORIZONTAL,
										elementsCreator.createLocationLabel(),
										elementsCreator.getLocationInput(),
										elementsCreator.createAddAlternativeLocationButton())
								.fillHorizontallySomeElements(
										elementsCreator.getLocationInput())
								.nextRow(
										elementsCreator.createGoToResourceButton())
								.nextRow(commonListElements.getButtonDelete()));

	}

}
