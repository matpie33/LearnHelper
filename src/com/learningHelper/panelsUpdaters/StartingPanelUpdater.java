package com.learningHelper.panelsUpdaters;

import com.guimaker.list.myList.MyList;
import com.learningHelper.uiElementsCreators.StartingPanelElementsCreator;

import javax.swing.*;

public class StartingPanelUpdater {

	private StartingPanelElementsCreator elementsCreator;

	public StartingPanelUpdater(StartingPanelElementsCreator elementsCreator) {
		this.elementsCreator = elementsCreator;
	}

	public void addLearningSourcesGroup (String name, MyList myList){
		elementsCreator.getTabPane().add(name, myList.getPanel());
	}

}
