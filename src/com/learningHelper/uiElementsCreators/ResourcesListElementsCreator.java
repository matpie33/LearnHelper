package com.learningHelper.uiElementsCreators;

import com.guimaker.panels.GuiElementsCreator;
import com.learningHelper.enums.LearningResourceType;
import com.learningHelper.uiElementsActionsCreators.ResourcesListActionsCreator;
import com.learningHelper.uiElementsStyles.UIElementsStyles;
import com.learningHelper.uiElementsTexts.Buttons;
import com.learningHelper.uiElementsTexts.Labels;

import javax.swing.*;
import javax.swing.text.JTextComponent;

public class ResourcesListElementsCreator {

	private JTextField tagInput;
	private JTextField locationInput;
	private JTextField timeRangeStartInput;
	private JTextField timeRangeEndInput;
	private JTextField stoppedPlaceTextInput;
	private ResourcesListActionsCreator actionsCreator;

	public ResourcesListElementsCreator() {
		actionsCreator = new ResourcesListActionsCreator();
	}

	public JLabel createTypeLabel() {
		return GuiElementsCreator.createLabel(UIElementsStyles.labelForInputStyle()
															  .text(Labels.RESOURCE_TYPE));
	}

	public JComboBox createTypeCombobox() {
		return GuiElementsCreator.createCombobox(
				UIElementsStyles.comboboxStyle()
								.setComboboxValues(
										LearningResourceType.getDisplayedValues()));
	}

	public JLabel createTagLabel() {
		return GuiElementsCreator.createLabel(UIElementsStyles.labelForInputStyle()
															  .text(Labels
																	  .RESOURCE_TAG));
	}
	public JLabel createLocationLabel() {
		return GuiElementsCreator.createLabel(UIElementsStyles.labelForInputStyle()
															  .text(Labels
																	  .RESOURCE_LOCATIONS));
	}

	public JTextComponent getTagInput() {
		if (tagInput == null){
			tagInput = GuiElementsCreator.createTextField(
					UIElementsStyles.shortTextInputStyle());
		}
		return tagInput;
	}

	public JTextComponent getLocationInput() {
		if (locationInput == null){
			locationInput = GuiElementsCreator.createTextField(
					UIElementsStyles.shortTextInputStyle());
		}
		return locationInput;
	}

	public AbstractButton createAddAlternativeLocationButton (){
		return GuiElementsCreator.createButtonlikeComponent(UIElementsStyles
				.buttonStyle().text(Buttons.ADD_ALTERNATIVE_LOCATION), actionsCreator
				.createAddAlternativeLocationAction());
	}

	public AbstractButton createGoToResourceButton (){
		return GuiElementsCreator.createButtonlikeComponent(UIElementsStyles
				.buttonStyle().text(Buttons.GO_TO_RESOURCE), actionsCreator
				.createAddAlternativeLocationAction());
	}

	public AbstractButton createIncreaseVideoNumberButton() {
		return GuiElementsCreator.createButtonlikeComponent(
				UIElementsStyles.buttonStyle()
								.text(Buttons.INCREASE_VIDEO_NUMBER),
				actionsCreator.createAddAlternativeLocationAction());
	}

	public JLabel createStoppedPlaceLabel() {
		return GuiElementsCreator.createLabel(UIElementsStyles.labelForInputStyle()
															  .text(Labels
																	  .STOPPED_PLACE));
	}

	public JTextComponent createStoppedPlaceTimeRangeStart() {
		if (timeRangeStartInput == null){
			timeRangeStartInput = GuiElementsCreator.createTextField(
					UIElementsStyles.textInputTimeRangeStyle());
		}
		return timeRangeStartInput;
	}

	public JTextComponent createStoppedPlaceTimeRangeEnd() {
		if (timeRangeEndInput == null) {
			timeRangeEndInput = GuiElementsCreator.createTextField(
					UIElementsStyles.textInputTimeRangeStyle());
		}
		return timeRangeEndInput;
	}

	public JTextComponent createStoppedPlaceTextInput() {
		if (stoppedPlaceTextInput == null) {
			stoppedPlaceTextInput = GuiElementsCreator.createTextField(
					UIElementsStyles.shortTextInputStyle());
		}
		return stoppedPlaceTextInput;
	}


}
