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

	private JTextComponent resourceTagInput;
	private JTextComponent videoMinuteInput;
	private JTextComponent videoSecondInput;
	private JTextComponent stoppedPlaceTextInput;
	private LearningResourceRowActionsCreator actionsCreator;
	private ApplicationController applicationController;
	private String learningResourcesGroupName;
	private JLabel labelResourceType;
	private AbstractButton checkboxSkipChoosingVideoPlayerType;
	private AbstractButton checkboxIsNarutoVideoLink;
	private JComboBox comboboxResourceType;
	private JLabel labelResourceTag;
	private JLabel labelResourceLocations;
	private AbstractButton buttonGoToResource;
	private JLabel labelStoppedPlace;
	private MyList<StringListElement> resourceLocationsList;

	public LearningResourceRowElementsCreator(
			ApplicationController applicationController,
			String learningResourcesGroupName,
			LearningResourceRowActionsCreator actionsCreator) {
		this.applicationController = applicationController;
		this.learningResourcesGroupName = learningResourcesGroupName;
		this.actionsCreator = actionsCreator;
	}

	public void createElements(LearningResource learningResource,
			MainPanel panel,
			CommonListElements<LearningResource> commonListElements) {
		resourceLocationsList = createResourceLocations(learningResource);
		createLabels();
		createButtonsAndBoxes(learningResource, panel, commonListElements);
		createInputs(learningResource, commonListElements);
	}

	private void createLabels() {
		labelResourceType = GuiElementsCreator.createLabel(
				UIElementsStyles.labelForInputStyle()
								.text(Labels.RESOURCE_TYPE));
		labelResourceTag = GuiElementsCreator.createLabel(
				UIElementsStyles.labelForInputStyle()
								.text(Labels.RESOURCE_TAG));
		labelResourceLocations = GuiElementsCreator.createLabel(
				UIElementsStyles.labelForInputStyle()
								.text(Labels.RESOURCE_LOCATIONS));
		labelStoppedPlace = GuiElementsCreator.createLabel(
				UIElementsStyles.labelForInputStyle()
								.text(Labels.STOPPED_PLACE));
	}

	private void createInputs(LearningResource learningResource,
			CommonListElements<LearningResource> commonListElements) {
		resourceTagInput = actionsCreator.addTagChangeListener(learningResource,
				GuiElementsCreator.createTextArea(
						UIElementsStyles.shortTextInputStyle()
										.text(learningResource.getTag())),
				commonListElements);

		if (learningResource.getType()
							.equals(LearningResourceType.WEB_VIDEO)) {
			videoMinuteInput = actionsCreator.listenForChangesInStoppedPlaceTimeRangeStartInput(
					GuiElementsCreator.createTextArea(
							UIElementsStyles.timeRangeInputStyle()
											.text(""
													+ learningResource.getLearningStoppedPlace()
																	  .getVideoMinute())),
					learningResource, commonListElements);
			videoSecondInput = actionsCreator.listenForChangesInStoppedPlaceTimeRangeEndInput(
					GuiElementsCreator.createTextArea(
							UIElementsStyles.timeRangeInputStyle()
											.text(""
													+ learningResource.getLearningStoppedPlace()
																	  .getVideoSecond())),
					learningResource, commonListElements);
		}
		stoppedPlaceTextInput = actionsCreator.listenForChangesInStoppedPlaceTextInput(
				GuiElementsCreator.createTextArea(
						UIElementsStyles.shortTextInputStyle()
										.text(learningResource.getLearningStoppedPlace()
															  .getTextFragmentPlace())),
				learningResource, commonListElements);

	}

	private void createButtonsAndBoxes(LearningResource learningResource,
			MainPanel panel,
			CommonListElements<LearningResource> commonListElements) {
		buttonGoToResource = GuiElementsCreator.createButtonLikeComponent(
				UIElementsStyles.buttonStyle()
								.text(Buttons.GO_TO_RESOURCE),
				actionsCreator.createActionGoToResource(learningResource));
		checkboxSkipChoosingVideoPlayerType = GuiElementsCreator.createCheckbox(
				new ButtonOptions(ButtonType.CHECKBOX).text(
						Checkboxes.SKIP_CHOOSING_VIDEO_PLAYER_TYPE),
				actionsCreator.createActionSkipChoosingVideoPlayerType(
						learningResource), null);
		checkboxSkipChoosingVideoPlayerType.setSelected(
				learningResource.isSkipChoosingVideoPlayerForNaruto());
		checkboxSkipChoosingVideoPlayerType.setEnabled(
				learningResource.isNarutoLink());

		checkboxIsNarutoVideoLink = GuiElementsCreator.createCheckbox(
				new ButtonOptions(ButtonType.CHECKBOX).text(
						Checkboxes.NARUTO_VIDEO_LINK),
				actionsCreator.createActionLinkToNaruto(learningResource, this),
				null);
		checkboxIsNarutoVideoLink.setSelected(learningResource.isNarutoLink());

		comboboxResourceType = createComboboxResourceType(learningResource,
				panel, commonListElements);
	}

	private JComboBox createComboboxResourceType(
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

	private MyList<StringListElement> createResourceLocations(
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

	public JTextComponent getInputResourceTag() {
		return resourceTagInput;
	}

	public JTextComponent getVideoMinuteInput() {
		return videoMinuteInput;
	}

	public JTextComponent getVideoSecondInput() {
		return videoSecondInput;
	}

	public JTextComponent getStoppedPlaceTextInput() {
		return stoppedPlaceTextInput;
	}

	public ApplicationController getApplicationController() {
		return applicationController;
	}

	public JLabel getLabelResourceType() {
		return labelResourceType;
	}

	public AbstractButton getCheckboxSkipChoosingVideoPlayerType() {
		return checkboxSkipChoosingVideoPlayerType;
	}

	public AbstractButton getCheckboxIsNarutoVideoLink() {
		return checkboxIsNarutoVideoLink;
	}

	public JComboBox getComboboxResourceType() {
		return comboboxResourceType;
	}

	public JLabel getLabelResourceTag() {
		return labelResourceTag;
	}

	public JLabel getLabelResourceLocations() {
		return labelResourceLocations;
	}

	public AbstractButton getButtonGoToResource() {
		return buttonGoToResource;
	}

	public JLabel getLabelStoppedPlace() {
		return labelStoppedPlace;
	}

	public MyList<StringListElement> getResourceLocationsList() {
		return resourceLocationsList;
	}
}
