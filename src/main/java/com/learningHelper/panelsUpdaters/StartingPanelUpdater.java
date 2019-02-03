package com.learningHelper.panelsUpdaters;

import com.guimaker.list.myList.MyList;
import com.learningHelper.model.GroupOfLearningResources;
import com.learningHelper.model.LearningResource;
import com.learningHelper.uiElementsCreators.StartingPanelElementsCreator;

import java.util.List;

public class StartingPanelUpdater {

	private StartingPanelElementsCreator elementsCreator;

	public StartingPanelUpdater(StartingPanelElementsCreator elementsCreator) {
		this.elementsCreator = elementsCreator;
	}

	public void focusInputForResourcesGroup() {
		elementsCreator.getResourcesGroupNameInput()
					   .requestFocusInWindow();
	}

	public void addLearningResourcesGroupToTabPane(String groupName,
			MyList myList) {
		elementsCreator.getTabPane()
					   .add(groupName, myList.getPanel());
	}

	public void addLearningResourcesGroups(
			List<GroupOfLearningResources> groupsOfLearningResources) {
		for (GroupOfLearningResources singleGroup : groupsOfLearningResources) {
			MyList<LearningResource> learningResourcesList = elementsCreator.createLearningResourcesList(
					singleGroup.getGroupName());
			singleGroup.getLearningResources()
					   .forEach(learningResourcesList::addWord);
			addLearningResourcesGroupToTabPane(singleGroup.getGroupName(),
					learningResourcesList);
		}
	}

	public void clearResourcesGroupTabs() {
		elementsCreator.getTabPane()
					   .removeAll();
	}
}
