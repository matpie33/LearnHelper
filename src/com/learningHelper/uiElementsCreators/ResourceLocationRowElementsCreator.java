package com.learningHelper.uiElementsCreators;

import com.guimaker.list.myList.MyList;
import com.guimaker.panels.GuiElementsCreator;
import com.learningHelper.application.ApplicationController;
import com.learningHelper.model.StringListElement;
import com.learningHelper.uiElementsActionsCreators.ResourceLocationRowActionsCreator;
import com.learningHelper.uiElementsStyles.UIElementsStyles;
import com.learningHelper.uiElementsTexts.Labels;

import javax.swing.*;
import javax.swing.text.JTextComponent;

public class ResourceLocationRowElementsCreator {

	private ResourceLocationRowActionsCreator actionsCreator;

	public ResourceLocationRowElementsCreator(
			ApplicationController applicationController) {
		this.actionsCreator = new ResourceLocationRowActionsCreator(
				applicationController);
	}

	public JLabel getLabelURL() {
		return GuiElementsCreator.createLabel(
				UIElementsStyles.labelForInputStyle()
								.text(Labels.URL));
	}

	public JTextComponent getInputResourceLocation(
			StringListElement stringListElement, MyList<StringListElement> list) {
		return actionsCreator.withPropertyChangeListener(stringListElement,
				GuiElementsCreator.createTextField(
						UIElementsStyles.shortTextInputStyle()), list);
	}

}
