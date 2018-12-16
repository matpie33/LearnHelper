package com.learningHelper.saving;

import com.learningHelper.model.GroupOfLearningResources;
import com.learningHelper.modelConversion.EnumToStringConverter;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.enums.EnumConverter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileSaver {

	public void save(List<GroupOfLearningResources> groupOfLearningSources,
			File file) throws IOException {
		XStream xStream = new XStream();
		xStream.autodetectAnnotations(true);
		xStream.toXML(groupOfLearningSources, new FileWriter(file));

	}
}
