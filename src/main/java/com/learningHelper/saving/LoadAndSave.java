package com.learningHelper.saving;

import com.guimaker.application.ApplicationWindow;
import com.learningHelper.model.GroupOfLearningResources;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoadAndSave {

	private File currentFile;
	private ApplicationWindow applicationWindow;
	private FromAndToXMLConverter fromAndToXMLConverter;
	private ConfigurationFileHandler configurationFileHandler;

	public LoadAndSave(ApplicationWindow applicationWindow) {
		this.applicationWindow = applicationWindow;
		fromAndToXMLConverter = new FromAndToXMLConverter();
		configurationFileHandler = new ConfigurationFileHandler();
	}

	public void initializeConfigurationFileIfDoesntExist() throws IOException {
		configurationFileHandler.initializeConfigurationFileIfDoesntExist();
	}

	public void save(List<GroupOfLearningResources> groupOfLearningSources)
			throws IOException {
		if (currentFile != null) {
			fromAndToXMLConverter.save(groupOfLearningSources, currentFile);
		}
	}

	public void createNewFileAndSave(
			List<GroupOfLearningResources> groupOfLearningSources)
			throws IOException {
		JFileChooser fileChooser = new JFileChooser();
		int chosenOption = fileChooser.showOpenDialog(
				applicationWindow.getContainer());
		if (chosenOption == JFileChooser.APPROVE_OPTION) {
			currentFile = fileChooser.getSelectedFile();
			save(groupOfLearningSources);
		}
	}

	public List<GroupOfLearningResources> showLoadFileDialog()
			throws FileNotFoundException {
		JFileChooser fileChooser = new JFileChooser();
		int chosenOption = fileChooser.showOpenDialog(
				applicationWindow.getContainer());
		if (chosenOption == JFileChooser.APPROVE_OPTION) {
			currentFile = fileChooser.getSelectedFile();
			configurationFileHandler.saveCurrentFilePathToConfigurationFile(
					currentFile);
			return loadResourcesFromFileAndSetTitle();
		}
		return new ArrayList<>();
	}

	public List<GroupOfLearningResources> openLastUsedFile()
			throws IOException {
		File lastUsedFile = configurationFileHandler.getLastUsedFile();
		if (lastUsedFile != null) {
			currentFile = lastUsedFile;
			return loadResourcesFromFileAndSetTitle();
		}
		return new ArrayList<>();
	}

	private List<GroupOfLearningResources> loadResourcesFromFileAndSetTitle() {
		List<GroupOfLearningResources> groupOfLearningResources = fromAndToXMLConverter.loadFile(
				currentFile);
		applicationWindow.addToTitle(currentFile.getPath());
		return groupOfLearningResources;
	}
}
