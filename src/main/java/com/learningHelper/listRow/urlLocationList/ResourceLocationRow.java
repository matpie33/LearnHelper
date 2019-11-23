package com.learningHelper.listRow.urlLocationList;

import com.guimaker.enums.FillType;
import com.guimaker.enums.InputGoal;
import com.guimaker.list.ListRowData;
import com.guimaker.list.myList.ListRowCreator;
import com.guimaker.model.CommonListElements;
import com.guimaker.model.PanelConfiguration;
import com.guimaker.panels.mainPanel.MainPanel;
import com.guimaker.row.SimpleRowBuilder;
import com.learningHelper.application.ApplicationController;
import com.learningHelper.enums.LearningResourceType;
import com.learningHelper.model.LearningResource;
import com.learningHelper.model.StringListElement;
import com.learningHelper.uiElementsCreators.LearningResourceRowElementsCreator;
import com.learningHelper.uiElementsCreators.ResourceLocationRowElementsCreator;

public class ResourceLocationRow implements ListRowCreator<StringListElement> {

	private ApplicationController applicationController;
	private LearningResourceRowElementsCreator learningResourceRowElementsCreator;

	public ResourceLocationRow(ApplicationController applicationController,
			LearningResourceRowElementsCreator elementsCreator) {
		this.applicationController = applicationController;
		this.learningResourceRowElementsCreator = elementsCreator;
	}

	@Override
	public ListRowData<StringListElement> createListRow(
			StringListElement stringListElement,
			CommonListElements<StringListElement> commonListElements,
			InputGoal inputGoal) {
		MainPanel panel = new MainPanel(new PanelConfiguration());
		ResourceLocationRowElementsCreator elementsCreator = new ResourceLocationRowElementsCreator(
				applicationController);
		elementsCreator.createElements(stringListElement, commonListElements,
				this.learningResourceRowElementsCreator);
		panel.addRow(SimpleRowBuilder.createRow(FillType.HORIZONTAL,
				elementsCreator.getLabelURL(),
				elementsCreator.getResourceLocationInput(),
				elementsCreator.getButtonIncreaseVideoNumber(),
				commonListElements.getButtonAddRow(),
				commonListElements.getButtonDelete())
									 .componentOnlyIfConditionMatches(
											 elementsCreator.getButtonIncreaseVideoNumber(),
											 isVideoResource(
													 commonListElements)));
		return new ListRowData<>(panel);
	}

	private boolean isVideoResource(
			CommonListElements<StringListElement> commonListElements) {
		return ((LearningResource) commonListElements.getList()
													 .getRootWord()).getType()
																	.equals(LearningResourceType.WEB_VIDEO);
	}
}
