package com.learningHelper.panelsUpdaters;

import com.learningHelper.uiElementsCreators.StartingPanelElementsCreator;

import javax.swing.*;

public class StartingPanelUpdater {

	private StartingPanelElementsCreator elementsCreator;

	public StartingPanelUpdater(StartingPanelElementsCreator elementsCreator) {
		this.elementsCreator = elementsCreator;
	}

	public void addLearningSourcesGroup (String name){
		elementsCreator.getTabPane().add(name, new JPanel());
	}

}
