package com.learningHelper.saving;

import com.learningHelper.model.GroupOfLearningResources;
import com.thoughtworks.xstream.XStream;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileSaver {

	public void save(List<GroupOfLearningResources> groupOfLearningSources,
			File file) throws IOException {
		XStream xStream = new XStream();
		xStream.toXML(groupOfLearningSources, new FileWriter(file));

	}
}
