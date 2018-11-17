package com.learningHelper.uiElementsCreators;

import com.guimaker.options.ComponentOptions;
import com.guimaker.panels.GuiElementsCreator;
import com.learningHelper.strings.Titles;

import javax.swing.*;

public class StartingPanelElementsCreator {

	public JLabel createTitleLabel(){
		return GuiElementsCreator.createLabel(new ComponentOptions().text(
				Titles.APPLICATION_TITLE));
	}


}
