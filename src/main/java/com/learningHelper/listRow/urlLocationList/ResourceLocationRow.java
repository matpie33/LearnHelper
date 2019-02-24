package com.learningHelper.listRow.urlLocationList;

import com.guimaker.enums.FillType;
import com.guimaker.enums.InputGoal;
import com.guimaker.list.ListRowData;
import com.guimaker.list.myList.ListRowCreator;
import com.guimaker.model.CommonListElements;
import com.guimaker.model.PanelConfiguration;
import com.guimaker.panels.MainPanel;
import com.guimaker.row.SimpleRowBuilder;
import com.learningHelper.application.ApplicationController;
import com.learningHelper.model.StringListElement;
import com.learningHelper.uiElementsCreators.LearningResourceRowElementsCreator;
import com.learningHelper.uiElementsCreators.ResourceLocationRowElementsCreator;

public class ResourceLocationRow implements ListRowCreator<StringListElement> {

	private ApplicationController applicationController;
	private LearningResourceRowElementsCreator elementsCreator;

	public ResourceLocationRow(ApplicationController applicationController,
			LearningResourceRowElementsCreator elementsCreator) {
		this.applicationController = applicationController;
		this.elementsCreator = elementsCreator;
	}

	@Override
	public ListRowData<StringListElement> createListRow(
			StringListElement stringListElement,
			CommonListElements<StringListElement> commonListElements,
			InputGoal inputGoal) {
		MainPanel panel = new MainPanel(new PanelConfiguration());
		ResourceLocationRowElementsCreator elementsCreator = new ResourceLocationRowElementsCreator(
				applicationController);
		panel.addRow(SimpleRowBuilder.createRow(FillType.HORIZONTAL,
				elementsCreator.createLabelURL(),
				elementsCreator.createInputResourceLocation(stringListElement,
						commonListElements.getList()),
				elementsCreator.createButtonIncreaseVideoNumberIfApplicable(
						commonListElements, stringListElement,
						this.elementsCreator),
				commonListElements.getButtonAddRow(),
				commonListElements.getButtonDelete()));
		return new ListRowData<>(panel);
	}
}
