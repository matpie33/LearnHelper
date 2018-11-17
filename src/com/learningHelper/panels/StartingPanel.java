package com.learningHelper.panels;

import com.guimaker.enums.Anchor;
import com.guimaker.enums.FillType;
import com.guimaker.panels.AbstractPanelWithHotkeysInfo;
import com.guimaker.row.SimpleRowBuilder;
import com.learningHelper.uiElementsCreators.StartingPanelElementsCreator;

public class StartingPanel extends AbstractPanelWithHotkeysInfo {

	private final static String UNIQUE_NAME = "Starting panel";
	private StartingPanelElementsCreator startingPanelElementsCreator = new
			StartingPanelElementsCreator();

	@Override
	public void createElements() {
		mainPanel.addRow(SimpleRowBuilder.createRow(FillType.NONE, Anchor
				.NORTH, startingPanelElementsCreator.createTitleLabel()));
	}

	@Override
	public String getUniqueName() {
		return UNIQUE_NAME;
	}
}
