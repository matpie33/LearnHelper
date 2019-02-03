package com.learningHelper.panels;

import com.guimaker.enums.Anchor;
import com.guimaker.enums.FillType;
import com.guimaker.model.PanelConfiguration;
import com.guimaker.panels.AbstractPanelWithHotkeysInfo;
import com.guimaker.panels.MainPanel;
import com.guimaker.row.SimpleRowBuilder;
import com.learningHelper.application.ApplicationController;
import com.learningHelper.model.GroupOfLearningResources;
import com.learningHelper.panelsUpdaters.StartingPanelUpdater;
import com.learningHelper.uiElementsCreators.StartingPanelElementsCreator;

import java.awt.*;
import java.util.List;

public class StartingPanel extends AbstractPanelWithHotkeysInfo {

	private final static String UNIQUE_NAME = "Starting panel";
	private StartingPanelElementsCreator elementsCreator;
	private StartingPanelUpdater panelUpdater;

	public StartingPanel(ApplicationController applicationController) {
		elementsCreator = new StartingPanelElementsCreator(
				applicationController, this);
		panelUpdater = new StartingPanelUpdater(elementsCreator);
	}

	public void addLearningResourcesGroups(
			List<GroupOfLearningResources> groups) {
		panelUpdater.addLearningResourcesGroups(groups);
	}

	@Override
	public void createElements() {
		MainPanel upperPanel = new MainPanel(
				new PanelConfiguration().setColorToUse(Color.GREEN));
		upperPanel.addRows(
				SimpleRowBuilder.createRow(FillType.NONE, Anchor.WEST,
						elementsCreator.createResourcesGroupNameLabel(),
						elementsCreator.getResourcesGroupNameInput(),
						elementsCreator.createButtonAddResourcesGroup())
								.nextRow(FillType.BOTH,
										elementsCreator.getTabPane()));

		mainPanel.addRows(
				SimpleRowBuilder.createRow(FillType.NONE, Anchor.CENTER,
						elementsCreator.createTitleLabel())
								.nextRow(FillType.NONE, Anchor.WEST,
										elementsCreator.createButtonLoadLastUsedFile(),
										elementsCreator.createButtonSave(),
										elementsCreator.createButtonLoad())
								.disableBorder().setNotOpaque()
								.nextRow(FillType.BOTH, Anchor.WEST,
										upperPanel.getPanel()));
	}

	@Override
	public String getUniqueName() {
		return UNIQUE_NAME;
	}

	public void clearResourcesGroupTabs() {
		panelUpdater.clearResourcesGroupTabs();
	}

	public void focusResourcesGroupInput() {
		panelUpdater.focusInputForResourcesGroup();
	}
}
