package com.learningHelper.enums;

import com.learningHelper.uiElementsTexts.Labels;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum LearningResourceType {
	WEB_TEXT_RESOURCE(Labels.WEB_TEXT_RESOURCE), WEB_VIDEO(
			Labels.WEB_VIDEO), WEB_HELPER_RESOURCE(Labels.WEB_HELPER_RESOURCE);

	private String displayedText;

	private LearningResourceType(String displayedText) {
		this.displayedText = displayedText;
	}

	public String getDisplayedText() {
		return displayedText;
	}

	public static List<String> getDisplayedValues() {
		return Arrays.stream(LearningResourceType.values())
					 .map(LearningResourceType::getDisplayedText)
					 .collect(Collectors.toList());
	}

	public static LearningResourceType getTypeByString(
			String learningResourceType) {
		return Arrays.stream(LearningResourceType.values())
					 .filter(type -> type.getDisplayedText()
										 .equals(learningResourceType))
					 .findFirst()
					 .orElse(null);
	}

}
