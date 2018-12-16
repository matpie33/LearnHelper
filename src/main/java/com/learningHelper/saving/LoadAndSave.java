package com.learningHelper.saving;

import com.learningHelper.model.GroupOfLearningResources;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class LoadAndSave {

	private File currentFile;
	private JFrame rootFrame;
	private FileSaver fileSaver;

	public LoadAndSave(JFrame rootFrame) {
		this.rootFrame = rootFrame;
		fileSaver = new FileSaver();
	}

	public void save(List<GroupOfLearningResources> groupOfLearningSources)
			throws IOException {
		if (currentFile != null) {
			fileSaver.save(groupOfLearningSources, currentFile);
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
}
