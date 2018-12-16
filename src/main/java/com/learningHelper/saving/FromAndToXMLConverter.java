package com.learningHelper.saving;

import com.learningHelper.model.GroupOfLearningResources;
import com.thoughtworks.xstream.XStream;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FromAndToXMLConverter {

	public void save(List<GroupOfLearningResources> groupOfLearningSources,
			File file) throws IOException {
		XStream xStream = new XStream();
		xStream.autodetectAnnotations(true);
		xStream.toXML(groupOfLearningSources, new FileWriter(file));

	}

	public List<GroupOfLearningResources> loadFile(File currentFile) {
		XStream xStream = new XStream();
		xStream.autodetectAnnotations(true);
		xStream.processAnnotations(GroupOfLearningResources.class);
		return (List<GroupOfLearningResources>)xStream.fromXML(currentFile);
	}
}
