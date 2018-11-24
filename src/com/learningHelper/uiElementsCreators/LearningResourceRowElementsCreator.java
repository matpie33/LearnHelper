package com.learningHelper.uiElementsCreators;

import com.guimaker.model.CommonListElements;
import com.guimaker.panels.GuiElementsCreator;
import com.guimaker.panels.MainPanel;
import com.learningHelper.application.ApplicationController;
import com.learningHelper.enums.LearningResourceType;
import com.learningHelper.model.LearningResource;
import com.learningHelper.uiElementsActionsCreators.LearningResourceRowActionsCreator;
import com.learningHelper.uiElementsStyles.UIElementsStyles;
import com.learningHelper.uiElementsTexts.Buttons;
import com.learningHelper.uiElementsTexts.Labels;

import javax.swing.*;
import javax.swing.text.JTextComponent;

public class LearningResourceRowElementsCreator {

	private JTextField tagInput;
	private JTextField locationInput;
	private JTextField timeRangeStartInput;
	private JTextField timeRangeEndInput;
	private JTextField stoppedPlaceTextInput;
	private LearningResourceRowActionsCreator actionsCreator;

	public LearningResourceRowElementsCreator(
			ApplicationController applicationController,
			String learningResourcesGroupName) {
		actionsCreator = new LearningResourceRowActionsCreator(this,
				applicationController, learningResourcesGroupName);
	}

	public JLabel getLabelResourceType() {
		return GuiElementsCreator.createLabel(
				UIElementsStyles.labelForInputStyle()
								.text(Labels.RESOURCE_TYPE));
	}

	public JComboBox getComboboxResourceType(LearningResource learningResource,
			MainPanel panel, CommonListElements commonListElements) {
		JComboBox combobox = GuiElementsCreator.createCombobox(
				UIElementsStyles.comboboxStyle()
								.setComboboxValues(
										LearningResourceType.getDisplayedValues()));
		combobox.setSelectedItem(learningResource.getType()
												 .getDisplayedText());
		combobox.addItemListener(
				actionsCreator.createActionChangeResourceType(learningResource,
						panel, commonListElements));
		return combobox;
	}

	public JLabel getLabelResourceTag() {
		return GuiElementsCreator.createLabel(
				UIElementsStyles.labelForInputStyle()
								.text(Labels.RESOURCE_TAG));
	}

	public JLabel getLabelResourceLocations() {
		return GuiElementsCreator.createLabel(
				UIElementsStyles.labelForInputStyle()
								.text(Labels.RESOURCE_LOCATIONS));
	}

	public JTextComponent getInputResourceTag(String tag,
			LearningResource learningResource) {
		if (tagInput == null) {
			tagInput = actionsCreator.addTagChangeListener(learningResource,
					GuiElementsCreator.createTextField(
							UIElementsStyles.shortTextInputStyle()
											.text(tag)));
		}
		return tagInput;
	}

	public JTextComponent getInputResourceLocation() {
		if (locationInput == null) {
			locationInput = GuiElementsCreator.createTextField(
					UIElementsStyles.shortTextInputStyle());
		}
		return locationInput;
	}

	public AbstractButton getButtonAddAlternativeLocation(MainPanel panel) {
		AbstractButton button = GuiElementsCreator.createButtonlikeComponent(
				UIElementsStyles.buttonStyle()
								.text(Buttons.ADD_ALTERNATIVE_LOCATION), null);
		button.addActionListener(
				actionsCreator.createAddAlternativeLocationAction(button,
						panel));
		return button;
	}

	public AbstractButton getButtonGoToResource() {
		return GuiElementsCreator.createButtonlikeComponent(
				UIElementsStyles.buttonStyle()
								.text(Buttons.GO_TO_RESOURCE),
				actionsCreator.createActionGoToResource());
	}

	public AbstractButton getButtonIncreaseVideoNumber() {
		return GuiElementsCreator.createButtonlikeComponent(
				UIElementsStyles.buttonStyle()
								.text(Buttons.INCREASE_VIDEO_NUMBER),
				actionsCreator.createActionIncreaseVideoNumber());
	}

	public JLabel getLabelStoppedPlace() {
		return GuiElementsCreator.createLabel(
				UIElementsStyles.labelForInputStyle()
								.text(Labels.STOPPED_PLACE));
	}

	public JTextComponent getInputStoppedPlaceTimeRangeStart() {
		if (timeRangeStartInput == null) {
			timeRangeStartInput = GuiElementsCreator.createTextField(
					UIElementsStyles.textInputTimeRangeStyle());
		}
		return timeRangeStartInput;
	}

	public JTextComponent getInputStoppedPlaceTimeRangeEnd() {
		if (timeRangeEndInput == null) {
			timeRangeEndInput = GuiElementsCreator.createTextField(
					UIElementsStyles.textInputTimeRangeStyle());
		}
		return timeRangeEndInput;
	}

	public JTextComponent getTextInputStoppedPlace() {
		if (stoppedPlaceTextInput == null) {
			stoppedPlaceTextInput = GuiElementsCreator.createTextField(
					UIElementsStyles.shortTextInputStyle());
		}
		return stoppedPlaceTextInput;
	}

}
