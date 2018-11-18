package com.learningHelper.uiElementsStyles;

import com.guimaker.colors.BasicColors;
import com.guimaker.enums.ButtonType;
import com.guimaker.options.ButtonOptions;
import com.guimaker.options.ComboboxOptions;
import com.guimaker.options.ComponentOptions;
import com.guimaker.options.TextComponentOptions;

import java.awt.*;

public class UIElementsStyles {

	public static ComponentOptions titleLabelStyle() {
		return new ComponentOptions().foregroundColor(BasicColors.BLUE_BRIGHT_1)
									 .fontSize(20F);
	}

	public static ComponentOptions informationLabelStyle() {
		return new ComponentOptions().foregroundColor(Color.WHITE);
	}

	public static ComponentOptions labelForInputStyle() {
		return new ComponentOptions().foregroundColor(Color.WHITE);
	}

	public static ButtonOptions buttonStyle() {
		return new ButtonOptions(ButtonType.BUTTON);
	}

	public static TextComponentOptions shortTextInputStyle() {
		return new TextComponentOptions().rowsAndColumns(1, 15);
	}

	public static TextComponentOptions textInputTimeRangeStyle() {
		return new TextComponentOptions().rowsAndColumns(1, 1);
	}

	public static ComboboxOptions comboboxStyle() {
		return new ComboboxOptions();
	}

}
