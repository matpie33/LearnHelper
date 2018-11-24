package com.learningHelper.listRow.urlLocationList;

import com.guimaker.enums.FillType;
import com.guimaker.enums.InputGoal;
import com.guimaker.list.ListRowData;
import com.guimaker.list.myList.ListRowCreator;
import com.guimaker.model.CommonListElements;
import com.guimaker.model.PanelConfiguration;
import com.guimaker.panels.MainPanel;
import com.guimaker.row.SimpleRowBuilder;
import com.learningHelper.model.StringListElement;
import com.learningHelper.uiElementsCreators.LearningResourceRowElementsCreator;
import com.learningHelper.uiElementsCreators.ResourceLocationRowElementsCreator;

public class ResourceLocationRow implements ListRowCreator<StringListElement> {

	private ResourceLocationRowElementsCreator elementsCreator;

	public ResourceLocationRow() {
		elementsCreator = new ResourceLocationRowElementsCreator();
	}

	@Override
	public ListRowData<StringListElement> createListRow(
			StringListElement stringListElement,
			CommonListElements commonListElements, InputGoal inputGoal) {
		MainPanel panel = new MainPanel(new PanelConfiguration());
		panel.addRow(SimpleRowBuilder.createRow(FillType.HORIZONTAL,
				elementsCreator.getLabelURL(),
				elementsCreator.getInputResourceLocation(),
				commonListElements.getButtonAddRow(), commonListElements
						.getButtonDelete()));
		return new ListRowData<>(panel);
	}
}
