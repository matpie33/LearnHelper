package com.learningHelper.listRow;

import com.guimaker.enums.FillType;
import com.guimaker.model.CommonListElements;
import com.guimaker.panels.MainPanel;
import com.guimaker.row.SimpleRowBuilder;
import com.learningHelper.enums.LearningResourceType;
import com.learningHelper.uiElementsCreators.LearningResourceRowElementsCreator;

public class WebHelperResourceRow implements ResourceRow {

	@Override
	public void addElementsToPanel(MainPanel panel,
			CommonListElements commonListElements) {
		LearningResourceRowElementsCreator elementsCreator = new LearningResourceRowElementsCreator();
		panel.addRowsOfElementsInColumn(
				SimpleRowBuilder.createRowStartingFromColumn(0, FillType.NONE,
						commonListElements.getRowNumberLabel(),
						elementsCreator.getLabelResourceType(),
						elementsCreator.getComboboxResourceType(
								LearningResourceType.WEB_HELPER_RESOURCE, panel,
								commonListElements))
								.nextRow(elementsCreator.getLabelResourceTag(),
										elementsCreator.getInputResourceTag())
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
