package com.learningHelper.panels;

import com.guimaker.enums.Anchor;
import com.guimaker.enums.FillType;
import com.guimaker.panels.AbstractPanelWithHotkeysInfo;
import com.guimaker.row.SimpleRowBuilder;
import com.learningHelper.application.ApplicationController;
import com.learningHelper.application.ResourcesHolder;
import com.learningHelper.uiElementsCreators.StartingPanelElementsCreator;

public class StartingPanel extends AbstractPanelWithHotkeysInfo {

	private final static String UNIQUE_NAME = "Starting panel";
	private StartingPanelElementsCreator elementsCreator;

	public StartingPanel(ResourcesHolder resourcesHolder,
			ApplicationController applicationController) {
		elementsCreator = new StartingPanelElementsCreator(
				resourcesHolder, applicationController);
	}

	@Override
	public void createElements() {
		mainPanel.addRow(
				SimpleRowBuilder.createRow(FillType.NONE, Anchor.CENTER,
						elementsCreator.getTitleLabel()));
		mainPanel.addRow(SimpleRowBuilder.createRow(FillType.NONE, Anchor.WEST,
				elementsCreator.getResourcesGroupNameInput(),
				elementsCreator.getButtonAddResourcesGroup()));
		mainPanel.addRow(SimpleRowBuilder.createRow(FillType.BOTH,
				elementsCreator.getTabPane()));
	}

	@Override
	public String getUniqueName() {
		return UNIQUE_NAME;
	}
}
