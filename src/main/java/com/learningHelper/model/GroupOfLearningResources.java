package com.learningHelper.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.List;

@XStreamAlias("resourcesGroup")
public class GroupOfLearningResources {

	@XStreamAlias("groupName")
	private String groupName;

	@XStreamAlias("resources")
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
