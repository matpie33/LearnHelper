package com.learningHelper.application;

import com.learningHelper.model.GrouppedLearningSources;
import com.learningHelper.model.LearningResource;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.List;

public class ResourcesHolder {

	private List<GrouppedLearningSources> learningResourceGroups;

	public ResourcesHolder() {
		learningResourceGroups = new ArrayList<>();
	}

	public void addResource (GrouppedLearningSources grouppedLearningSources){
		learningResourceGroups.add(grouppedLearningSources);
	}

	public List<GrouppedLearningSources> getLearningResourceGroups() {
		return learningResourceGroups;
	}

	public boolean hasResources() {
		return !learningResourceGroups.isEmpty();
	}
}
