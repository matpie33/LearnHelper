package com.learningHelper.saving;

import com.learningHelper.model.GroupOfLearningResources;
import com.learningHelper.model.LearningResource;
import com.learningHelper.model.LearningStoppedPlace;
import com.learningHelper.model.StringListElement;
import com.thoughtworks.xstream.XStream;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class FromAndToXMLConverter {

	public void save(List<GroupOfLearningResources> groupOfLearningSources,
			File file) throws IOException {
		XStream xStream = initializeXStream();
		xStream.toXML(groupOfLearningSources,
				new OutputStreamWriter(new FileOutputStream(file),
						StandardCharsets.UTF_8));

	}

	private XStream initializeXStream() {
		XStream xStream = new XStream();
		xStream.autodetectAnnotations(true);
		XStream.setupDefaultSecurity(xStream);
		xStream.allowTypes(new Class[] { GroupOfLearningResources.class,
				LearningResource.class, LearningStoppedPlace.class,
				StringListElement.class });
		return xStream;
	}

	public List<GroupOfLearningResources> loadFile(File currentFile) {
		XStream xStream = initializeXStream();
		xStream.processAnnotations(GroupOfLearningResources.class);
		return (List<GroupOfLearningResources>) xStream.fromXML(currentFile);
	}
}
