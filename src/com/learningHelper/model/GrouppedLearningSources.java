package com.learningHelper.model;

import java.util.List;

public class GrouppedLearningSources {

	private String groupName;
	private List<LearningResource> learningResources;

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public List<LearningResource> getLearningResources() {
		return learningResources;
	}

	public void setLearningResources(List<LearningResource> learningResources) {
		this.learningResources = learningResources;
	}
}
