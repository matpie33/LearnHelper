package com.learningHelper.uiElementsCreators;

import com.guimaker.panels.GuiElementsCreator;
import com.learningHelper.application.ApplicationController;
import com.learningHelper.model.LearningResource;
import com.learningHelper.uiElementsActionsCreators.ResourceLocationRowActionsCreator;
import com.learningHelper.uiElementsStyles.UIElementsStyles;
import com.learningHelper.uiElementsTexts.Labels;

import javax.swing.*;
import javax.swing.text.JTextComponent;

public class ResourceLocationRowElementsCreator {

	private ResourceLocationRowActionsCreator actionsCreator;

	public ResourceLocationRowElementsCreator(
			ApplicationController applicationController,
			String learningResourcesGroupName) {
		this.actionsCreator = new ResourceLocationRowActionsCreator(
				applicationController, learningResourcesGroupName);
	}

	public JLabel getLabelURL() {
		return GuiElementsCreator.createLabel(
				UIElementsStyles.labelForInputStyle()
								.text(Labels.URL));
	}

	public JTextComponent getInputResourceLocation(
			LearningResource learningResource) {
		return actionsCreator.withPropertyChangeListener(learningResource,
				GuiElementsCreator.createTextField(
						UIElementsStyles.shortTextInputStyle()));
	}

}
