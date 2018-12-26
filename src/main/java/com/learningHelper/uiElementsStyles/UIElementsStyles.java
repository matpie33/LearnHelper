package com.learningHelper.uiElementsStyles;

import com.guimaker.colors.BasicColors;
import com.guimaker.enums.ButtonType;
import com.guimaker.options.ButtonOptions;
import com.guimaker.options.ComboboxOptions;
import com.guimaker.options.ComponentOptions;
import com.guimaker.options.TextComponentOptions;

import javax.swing.*;
import java.awt.*;

public class UIElementsStyles {

	private static final int PADDING_TEXTFIELD = 3;

	public static ComponentOptions titleLabelStyle() {
		return new ComponentOptions().foregroundColor(Color.WHITE)
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
		return defaultTextFieldOption().rowsAndColumns(1, 15);
	}

	public static TextComponentOptions timeRangeInputStyle() {
		return defaultTextFieldOption().rowsAndColumns(1, 3)
									   .digitsOnly(true)
									   .selectAllOnFocus();
	}

	public static ComboboxOptions comboboxStyle() {
		return new ComboboxOptions();
	}

	private static TextComponentOptions defaultTextFieldOption() {
		return new TextComponentOptions().border(

				BorderFactory.createCompoundBorder(
						BorderFactory.createLineBorder(Color.WHITE, 1, false),
						BorderFactory.createEmptyBorder(PADDING_TEXTFIELD,
								PADDING_TEXTFIELD, PADDING_TEXTFIELD,
								PADDING_TEXTFIELD)))
										 .backgroundColor(
												 BasicColors.BLUE_NORMAL_7);
		//TODO add the padding as default in guimaker
	}

}
