package com.learningHelper.modelConversion;

import com.learningHelper.enums.LearningResourceType;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class EnumToStringConverter implements Converter {
	@Override
	public void marshal(Object source, HierarchicalStreamWriter writer,
			MarshallingContext context) {
		writer.setValue(((LearningResourceType)source).getDisplayedText());
	}

	@Override
	public Object unmarshal(HierarchicalStreamReader reader,
			UnmarshallingContext context) {
		return LearningResourceType.getTypeByString(reader.getValue());
	}

	@Override
	public boolean canConvert(Class type) {
		return type.equals(LearningResourceType.class);
	}
}
