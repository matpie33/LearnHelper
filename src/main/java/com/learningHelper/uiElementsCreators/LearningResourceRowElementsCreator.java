package com.learningHelper.uiElementsCreators;

import com.guimaker.enums.ButtonType;
import com.guimaker.enums.KeyModifiers;
import com.guimaker.list.myList.ListConfiguration;
import com.guimaker.list.myList.MyList;
import com.guimaker.model.CommonListElements;
import com.guimaker.model.HotkeyWrapper;
import com.guimaker.options.ButtonOptions;
import com.guimaker.panels.GuiElementsCreator;
import com.guimaker.panels.MainPanel;
import com.guimaker.utilities.CommonActionsCreator;
import com.learningHelper.application.ApplicationController;
import com.learningHelper.enums.LearningResourceType;
import com.learningHelper.listRow.urlLocationList.ResourceLocationRow;
import com.learningHelper.model.LearningResource;
import com.learningHelper.model.StringListElement;
import com.learningHelper.uiElementsActionsCreators.LearningResourceRowActionsCreator;
import com.learningHelper.uiElementsStyles.UIElementsStyles;
import com.learningHelper.uiElementsTexts.Buttons;
import com.learningHelper.uiElementsTexts.Checkboxes;
import com.learningHelper.uiElementsTexts.Labels;
import com.learningHelper.uiElementsTexts.UserInformation;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.event.KeyEvent;

public class LearningResourceRowElementsCreator {

	private JTextComponent tagInput;
	private JTextComponent timeRangeStartInput;
	private JTextComponent timeRangeEndInput;
	private JTextComponent stoppedPlaceTextInput;
	private LearningResourceRowActionsCreator actionsCreator;
	private ApplicationController applicationController;
	private String learningResourcesGroupName;

	public LearningResourceRowElementsCreator(
			ApplicationController applicationController,
			String learningResourcesGroupName,
			LearningResourceRowActionsCreator actionsCreator) {
		this.applicationController = applicationController;
		this.learningResourcesGroupName = learningResourcesGroupName;
		this.actionsCreator = actionsCreator;
	}

	public JLabel createLabelResourceType() {
		return GuiElementsCreator.createLabel(
				UIElementsStyles.labelForInputStyle()
								.text(Labels.RESOURCE_TYPE));
	}

	public AbstractButton createCheckboxSkipChoosingVideoPlayerType() {
		AbstractButton checkbox = GuiElementsCreator.createCheckbox(
				new ButtonOptions(ButtonType.CHECKBOX).text(
						Checkboxes.SKIP_CHOOSING_VIDEO_PLAYER_TYPE),
				actionsCreator.createActionSkipChoosingVideoPlayerType(), null);
		checkbox.setSelected(true);
		return checkbox;
	}

	public JComboBox createComboboxResourceType(
			LearningResource learningResource, MainPanel panel,
			CommonListElements commonListElements) {
		JComboBox combobox = GuiElementsCreator.createCombobox(
				UIElementsStyles.comboboxStyle()
								.setComboboxValues(
										LearningResourceType.getDisplayedValues()));
		combobox.setSelectedItem(learningResource.getType()
												 .getDisplayedText());
		if (commonListElements.isForSingleRowOnly()) {
			CommonActionsCreator.addHotkey(
					new HotkeyWrapper(KeyModifiers.CONTROL, KeyEvent.VK_SPACE),
					actionsCreator.createActionSwitchResourceType(combobox),
					combobox);
		}
		combobox.addItemListener(
				actionsCreator.createActionChangeResourceType(learningResource,
						panel, commonListElements));
		return combobox;
	}

	public JLabel createLabelResourceTag() {
		return GuiElementsCreator.createLabel(
				UIElementsStyles.labelForInputStyle()
								.text(Labels.RESOURCE_TAG));
	}

	public JLabel createLabelResourceLocations() {
		return GuiElementsCreator.createLabel(
				UIElementsStyles.labelForInputStyle()
								.text(Labels.RESOURCE_LOCATIONS));
	}

	public JTextComponent createInputResourceTag(String tag,
			LearningResource learningResource,
			CommonListElements<LearningResource> commonListElements) {
		if (tagInput == null) {
			tagInput = actionsCreator.addTagChangeListener(learningResource,
					GuiElementsCreator.createTextArea(
							UIElementsStyles.shortTextInputStyle()
											.text(tag)), commonListElements);
		}
		return tagInput;
	}

	public JTextComponent getTagInput() {
		return tagInput;
	}

	public AbstractButton createButtonGoToResource(
			LearningResource learningResource) {
		return GuiElementsCreator.createButtonLikeComponent(
				UIElementsStyles.buttonStyle()
								.text(Buttons.GO_TO_RESOURCE),
				actionsCreator.createActionGoToResource(learningResource));
	}

	public JLabel createLabelStoppedPlace() {
		return GuiElementsCreator.createLabel(
				UIElementsStyles.labelForInputStyle()
								.text(Labels.STOPPED_PLACE));
	}

	public JTextComponent createInputStoppedPlaceVideoMinute(
			LearningResource learningResource,
			CommonListElements<LearningResource> commonListElements) {
		if (timeRangeStartInput == null) {
			timeRangeStartInput = actionsCreator.listenForChangesInStoppedPlaceTimeRangeStartInput(
					GuiElementsCreator.createTextArea(
							UIElementsStyles.timeRangeInputStyle()
											.text(""
													+ learningResource.getLearningStoppedPlace()
																	  .getVideoMinute())),
					learningResource, commonListElements);
		}
		return timeRangeStartInput;
	}

	public JTextComponent getTimeRangeStartInput() {
		return timeRangeStartInput;
	}

	public JTextComponent getTimeRangeEndInput() {
		return timeRangeEndInput;
	}

	public JTextComponent createInputStoppedPlaceVideoSecond(
			LearningResource learningResource,
			CommonListElements<LearningResource> commonListElements) {
		if (timeRangeEndInput == null) {
			timeRangeEndInput = actionsCreator.listenForChangesInStoppedPlaceTimeRangeEndInput(
					GuiElementsCreator.createTextArea(
							UIElementsStyles.timeRangeInputStyle()
											.text(""
													+ learningResource.getLearningStoppedPlace()
																	  .getVideoSecond())),
					learningResource, commonListElements);
		}
		return timeRangeEndInput;
	}

	public JTextComponent createInputStoppedPlace(
			LearningResource learningResource,
			CommonListElements<LearningResource> commonListElements) {
		if (stoppedPlaceTextInput == null) {
			stoppedPlaceTextInput = actionsCreator.listenForChangesInStoppedPlaceTextInput(
					GuiElementsCreator.createTextArea(
							UIElementsStyles.shortTextInputStyle()
											.text(learningResource.getLearningStoppedPlace()
																  .getTextFragmentPlace())),
					learningResource, commonListElements);
		}
		return stoppedPlaceTextInput;
	}

	public MyList<StringListElement> createResourceLocations(
			LearningResource learningResource) {
		MyList<StringListElement> locationsList = new MyList<>(
				new ListConfiguration<>(UserInformation.URL_LOCATION_DELETE,
						new ResourceLocationRow(applicationController, this),
						StringListElement.getInitializer(), "",
						applicationController.getApplicationWindow(),
						applicationController).skipTitle(true)
											  .showButtonsLoadNextPreviousWords(
													  false)
											  .inheritScrollbar(true)
											  .enableWordAdding(false)
											  .parentListAndWordContainingThisList(
													  applicationController.getLearningResourcesGroup(
															  learningResourcesGroupName),
													  learningResource,
													  learningResource.getAlternativeLocations()));
		if (learningResource.getAlternativeLocations()
							.isEmpty()) {
			locationsList.addWord(new StringListElement(""));
		}
		else {
			learningResource.getAlternativeLocations()
							.forEach(locationsList::addWord);
		}

		return locationsList;
	}

}
