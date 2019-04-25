package com.learningHelper.uiElementsCreators;

import com.guimaker.list.myList.MyList;
import com.guimaker.model.CommonListElements;
import com.guimaker.panels.GuiElementsCreator;
import com.learningHelper.application.ApplicationController;
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
	private JLabel labelURL;
	private AbstractButton buttonIncreaseVideoNumber;

	public ResourceLocationRowElementsCreator(
			ApplicationController applicationController) {
		this.actionsCreator = new ResourceLocationRowActionsCreator(
				applicationController);
	}

	public void createElements(StringListElement stringListElement,
			CommonListElements<StringListElement> commonListElements,
			LearningResourceRowElementsCreator elementsCreator) {
		MyList<StringListElement> resourceLocationList = commonListElements.getList();
		LearningResource learningResource = (LearningResource) resourceLocationList.getRootWord();

		labelURL = GuiElementsCreator.createLabel(
				UIElementsStyles.labelForInputStyle()
								.text(Labels.URL));
		resourceLocationInput = actionsCreator.withPropertyChangeListener(
				stringListElement, GuiElementsCreator.createTextArea(
						UIElementsStyles.shortTextInputStyle()
										.text(stringListElement.getValue())),
				resourceLocationList);
		buttonIncreaseVideoNumber = GuiElementsCreator.createButtonLikeComponent(
				UIElementsStyles.buttonStyle()
								.text(Buttons.INCREASE_VIDEO_NUMBER),
				actionsCreator.createIncreaseVideoSeriesNumberAction(
						resourceLocationInput, stringListElement,
						learningResource, elementsCreator));
	}

	public JTextComponent getResourceLocationInput() {
		return resourceLocationInput;
	}

	public JLabel getLabelURL() {
		return labelURL;
	}

	public AbstractButton getButtonIncreaseVideoNumber() {
		return buttonIncreaseVideoNumber;
	}

}
