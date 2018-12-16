package com.learningHelper.saving;

import com.learningHelper.model.GroupOfLearningResources;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoadAndSave {

	private File currentFile;
	private JFrame rootFrame;
	private FromAndToXMLConverter fromAndToXMLConverter;

	public LoadAndSave(JFrame rootFrame) {
		this.rootFrame = rootFrame;
		fromAndToXMLConverter = new FromAndToXMLConverter();
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
		int chosenOption = fileChooser.showOpenDialog(rootFrame);
		if (chosenOption == JFileChooser.APPROVE_OPTION) {
			currentFile = fileChooser.getSelectedFile();
			save(groupOfLearningSources);
		}
	}

	public List<GroupOfLearningResources> showLoadFileDialog() {
		JFileChooser fileChooser = new JFileChooser();
		int chosenOption = fileChooser.showOpenDialog(rootFrame);
		if (chosenOption == JFileChooser.APPROVE_OPTION) {
			currentFile = fileChooser.getSelectedFile();
			return fromAndToXMLConverter.loadFile(currentFile);
		}
		return new ArrayList<>();
	}
}
