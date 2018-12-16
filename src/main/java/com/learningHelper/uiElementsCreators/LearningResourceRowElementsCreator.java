package com.learningHelper.uiElementsCreators;

import com.guimaker.list.myList.ListConfiguration;
import com.guimaker.list.myList.MyList;
import com.guimaker.model.CommonListElements;
import com.guimaker.panels.GuiElementsCreator;
import com.guimaker.panels.MainPanel;
import com.learningHelper.application.ApplicationController;
import com.learningHelper.enums.LearningResourceType;
import com.learningHelper.listRow.urlLocationList.ResourceLocationRow;
import com.learningHelper.model.LearningResource;
import com.learningHelper.model.StringListElement;
import com.learningHelper.uiElementsActionsCreators.LearningResourceRowActionsCreator;
import com.learningHelper.uiElementsStyles.UIElementsStyles;
import com.learningHelper.uiElementsTexts.Buttons;
import com.learningHelper.uiElementsTexts.Labels;
import com.learningHelper.uiElementsTexts.UserInformation;

import javax.swing.*;
import javax.swing.text.JTextComponent;

public class LearningResourceRowElementsCreator {

	private JTextField tagInput;
	private JTextField timeRangeStartInput;
	private JTextField timeRangeEndInput;
	private JTextField stoppedPlaceTextInput;
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

	public JComboBox createComboboxResourceType(
			LearningResource learningResource, MainPanel panel,
			CommonListElements commonListElements) {
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
					GuiElementsCreator.createTextField(
							UIElementsStyles.shortTextInputStyle()
											.text(tag)), commonListElements);
		}
		return tagInput;
	}

	public JTextField getTagInput() {
		return tagInput;
	}

	public AbstractButton createButtonGoToResource(
			LearningResource learningResource) {
		return GuiElementsCreator.createButtonlikeComponent(
				UIElementsStyles.buttonStyle()
								.text(Buttons.GO_TO_RESOURCE),
				actionsCreator.createActionGoToResource(learningResource));
	}

	public JLabel createLabelStoppedPlace() {
		return GuiElementsCreator.createLabel(
				UIElementsStyles.labelForInputStyle()
								.text(Labels.STOPPED_PLACE));
	}

	public JTextComponent createInputStoppedPlaceTimeRangeStart(
			LearningResource learningResource,
			CommonListElements<LearningResource> commonListElements) {
		if (timeRangeStartInput == null) {
			timeRangeStartInput = actionsCreator.listenForChangesInStoppedPlaceTimeRangeStartInput(
					GuiElementsCreator.createTextField(
							UIElementsStyles.textInputTimeRangeStyle()
											.text(""
													+ learningResource.getLearningStoppedPlace()
																	  .getTimeRangeStart())),
					learningResource, commonListElements);
		}
		return timeRangeStartInput;
	}

	public JTextField getTimeRangeStartInput() {
		return timeRangeStartInput;
	}

	public JTextField getTimeRangeEndInput() {
		return timeRangeEndInput;
	}

	public JTextComponent createInputStoppedPlaceTimeRangeEnd(
			LearningResource learningResource,
			CommonListElements<LearningResource> commonListElements) {
		if (timeRangeEndInput == null) {
			timeRangeEndInput = actionsCreator.listenForChangesInStoppedPlaceTimeRangeEndInput(
					GuiElementsCreator.createTextField(
							UIElementsStyles.textInputTimeRangeStyle()
											.text(""
													+ learningResource.getLearningStoppedPlace()
																	  .getTimeRangeEnd())),
					learningResource, commonListElements);
		}
		return timeRangeEndInput;
	}

	public JTextComponent createInputStoppedPlace(
			LearningResource learningResource,
			CommonListElements<LearningResource> commonListElements) {
		if (stoppedPlaceTextInput == null) {
			stoppedPlaceTextInput = actionsCreator.listenForChangesInStoppedPlaceTextInput(
					GuiElementsCreator.createTextField(
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
						new ResourceLocationRow(applicationController),
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