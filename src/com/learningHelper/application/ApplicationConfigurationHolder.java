package com.learningHelper.application;

import com.guimaker.application.ApplicationConfiguration;
import com.guimaker.colors.BasicColors;
import com.learningHelper.strings.Titles;

public class ApplicationConfigurationHolder {

	public ApplicationConfiguration getApplicationConfiguration() {
		return new ApplicationConfiguration(
				Titles.APPLICATION_TITLE).setContentPanelColor(
				BasicColors.BLUE_NORMAL_6)
										 .setPanelBackgroundColor(
												 BasicColors.PURPLE_DARK_2);
	}

}
