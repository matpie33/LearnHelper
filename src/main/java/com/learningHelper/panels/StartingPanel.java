package com.learningHelper.panels;

import com.guimaker.enums.Anchor;
import com.guimaker.enums.FillType;
import com.guimaker.panels.AbstractPanelWithHotkeysInfo;
import com.guimaker.row.SimpleRowBuilder;
import com.learningHelper.application.ApplicationController;
import com.learningHelper.uiElementsCreators.StartingPanelElementsCreator;

public class StartingPanel extends AbstractPanelWithHotkeysInfo {

	private final static String UNIQUE_NAME = "Starting panel";
	private StartingPanelElementsCreator elementsCreator;

	public StartingPanel(ApplicationController applicationController) {
		elementsCreator = new StartingPanelElementsCreator(
				applicationController);
	}

	@Override
	public void createElements() {
		mainPanel.addRows(
				SimpleRowBuilder.createRow(FillType.NONE, Anchor.CENTER,
						elementsCreator.createTitleLabel())
								.nextRow(FillType.NONE, Anchor.WEST,
										elementsCreator.createButtonSave())
								.nextRow(
										elementsCreator.createResourcesGroupNameLabel(),
										elementsCreator.createResourcesGroupNameInput(),
										elementsCreator.createButtonAddResourcesGroup())
								.nextRow(FillType.BOTH,
										elementsCreator.createTabPane()));
	}

	@Override
	public String getUniqueName() {
		return UNIQUE_NAME;
	}
}
