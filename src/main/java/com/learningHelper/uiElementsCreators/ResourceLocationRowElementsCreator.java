package com.learningHelper.uiElementsCreators;

import com.guimaker.list.myList.MyList;
import com.guimaker.model.CommonListElements;
import com.guimaker.panels.GuiElementsCreator;
import com.learningHelper.application.ApplicationController;
import com.learningHelper.enums.LearningResourceType;
import com.learningHelper.model.LearningResource;
import com.learningHelper.model.StringListElement;
import com.learningHelper.uiElementsActionsCreators.ResourceLocationRowActionsCreator;
import com.learningHelper.uiElementsStyles.UIElementsStyles;
import com.learningHelper.uiElementsTexts.Buttons;
import com.learningHelper.uiElementsTexts.Labels;

import javax.swing.*;
import javax.swing.text.JTextComponent;

public class ResourceLocationRowElementsCreator {

	private ResourceLocationRowActionsCreator actionsCreator;
	private JTextComponent resourceLocationInput;

	public ResourceLocationRowElementsCreator(
			ApplicationController applicationController) {
		this.actionsCreator = new ResourceLocationRowActionsCreator(
				applicationController);
	}

	public JLabel createLabelURL() {
		return GuiElementsCreator.createLabel(
				UIElementsStyles.labelForInputStyle()
								.text(Labels.URL));
	}

	public JTextComponent createInputResourceLocation(
			StringListElement stringListElement,
			MyList<StringListElement> list) {
		resourceLocationInput = actionsCreator.withPropertyChangeListener(
				stringListElement, GuiElementsCreator.createTextArea(
						UIElementsStyles.shortTextInputStyle()
										.text(stringListElement.getValue())),
				list);
		return resourceLocationInput;
	}

	public JComponent createButtonIncreaseVideoNumberIfApplicable(
			CommonListElements<StringListElement> commonListElements,
			StringListElement stringListElement,
			LearningResourceRowElementsCreator elementsCreator) {
		LearningResource learningResource = (LearningResource) commonListElements.getList()
																				 .getRootWord();
		if (learningResource.getType()
							.equals(LearningResourceType.WEB_VIDEO)) {
			return GuiElementsCreator.createButtonLikeComponent(
					UIElementsStyles.buttonStyle()
									.text(Buttons.INCREASE_VIDEO_NUMBER),
					actionsCreator.createIncreaseVideoSeriesNumberAction(
							resourceLocationInput, stringListElement,
							learningResource, elementsCreator));
		}
		return null;
	}

}
