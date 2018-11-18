package com.learningHelper.listRow;

import com.guimaker.enums.Anchor;
import com.guimaker.enums.FillType;
import com.guimaker.model.CommonListElements;
import com.guimaker.panels.MainPanel;
import com.guimaker.row.SimpleRowBuilder;

import javax.swing.*;

public class WebVideoResourceRow implements ResourceRow {

	@Override
	public void addElementsToPanel(MainPanel panel,
			CommonListElements commonListElements) {
		panel.addRowsOfElementsInColumn(
				SimpleRowBuilder.createRowStartingFromColumn(0, FillType.NONE,
						Anchor.WEST, new JButton("Test button"), new JLabel
								("Test label"))
								.nextRow(new JLabel("Test label"))
								.setColumnToPutRowInto(1));
	}
}
