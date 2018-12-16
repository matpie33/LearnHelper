package com.learningHelper.model;

import java.util.List;

public class GroupOfLearningResources {

	private String groupName;
	private List<LearningResource> learningResources;

	public GroupOfLearningResources(String groupName,
			List<LearningResource> learningResources) {
		this.groupName = groupName;
		this.learningResources = learningResources;
	}

	public String getGroupName() {
		return groupName;
	}

	public List<LearningResource> getLearningResources() {
		return learningResources;
	}

}
