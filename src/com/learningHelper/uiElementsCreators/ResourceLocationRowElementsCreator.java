package com.learningHelper.uiElementsCreators;

import com.guimaker.panels.GuiElementsCreator;
import com.learningHelper.uiElementsStyles.UIElementsStyles;
import com.learningHelper.uiElementsTexts.Labels;

import javax.swing.*;
import javax.swing.text.JTextComponent;

public class ResourceLocationRowElementsCreator {

	public JLabel getLabelURL() {
		return GuiElementsCreator.createLabel(
				UIElementsStyles.labelForInputStyle()
								.text(Labels.URL));
	}

	public JTextComponent getInputResourceLocation() {
		return GuiElementsCreator.createTextField(
				UIElementsStyles.shortTextInputStyle());
	}

}
