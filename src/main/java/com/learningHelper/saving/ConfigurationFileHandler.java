package com.learningHelper.saving;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.List;

public class ConfigurationFileHandler {

	private File configurationFile;
	private static final String CONFIGURATION_FILE_NAME = "configuration.txt";
	private static final String LAST_OPENED_FILE_FORMAT =
			"last_opened_file = " + "%s";

	public void initializeConfigurationFileIfDoesntExist() throws IOException {
		configurationFile = new File(CONFIGURATION_FILE_NAME);
		configurationFile.createNewFile();
	}

	public void saveCurrentFilePathToConfigurationFile(File currentFile)
			throws FileNotFoundException {
		try (PrintWriter printWriter = new PrintWriter(configurationFile)) {
			printWriter.write(String.format(LAST_OPENED_FILE_FORMAT,
					currentFile.getAbsolutePath()));
		}

	}

	public File getLastUsedFile() throws IOException {
		List<String> lines = Files.readAllLines(configurationFile.toPath());
		if (!lines.isEmpty()) {
			String lastFilePath = lines.get(0);
			lastFilePath = lastFilePath.replace("last_opened_file = ", "");
			return new File(lastFilePath);
		}
		return null;

	}
}
