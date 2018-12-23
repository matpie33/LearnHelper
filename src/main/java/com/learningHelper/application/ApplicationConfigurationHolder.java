package com.learningHelper.application;

import com.guimaker.application.ApplicationConfiguration;
import com.guimaker.colors.BasicColors;
import com.learningHelper.uiElementsTexts.Titles;

public class ApplicationConfigurationHolder {

	public ApplicationConfiguration getApplicationConfiguration() {
		return new ApplicationConfiguration(
				Titles.APPLICATION_TITLE).setContentPanelColor(
				BasicColors.BLUE_BRIGHT_4)
										 .setPanelBackgroundColor(
												 BasicColors.GREEN_DARK_1)
										 .setHotkeysPanelColor(
												 BasicColors.BLUE_NORMAL_8);
	}

}
