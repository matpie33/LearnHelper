package com.learningHelper.modelConversion;

import com.guimaker.list.myList.MyList;
import com.learningHelper.model.GroupOfLearningResources;
import com.learningHelper.model.LearningResource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GrouppedResourcesConverter {

	public static List<GroupOfLearningResources> convertMapOfResourcesToListOfGroups(
			Map<String, MyList<LearningResource>> grouppedResources) {
		List<GroupOfLearningResources> groupOfLearningSources = new ArrayList<>();
		for (Map.Entry<String, MyList<LearningResource>> groupNameToLearningResources : grouppedResources.entrySet()) {
			groupOfLearningSources.add(new GroupOfLearningResources(
					groupNameToLearningResources.getKey(),
					groupNameToLearningResources.getValue()
												.getWords()));
		}
		return groupOfLearningSources;
	}
}
