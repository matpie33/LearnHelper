package com.learningHelper.uiElementsActionsCreators;

import com.guimaker.model.CommonListElements;
import com.guimaker.panels.MainPanel;
import com.learningHelper.enums.LearningResourceType;
import com.learningHelper.panelsUpdaters.LearningResourceRowUpdater;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ResourcesListActionsCreator {

	private LearningResourceRowUpdater rowUpdater;

	public ResourcesListActionsCreator() {
		this.rowUpdater = new LearningResourceRowUpdater();
	}

	public AbstractAction createAddAlternativeLocationAction() {
		return new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		};
	}

	public AbstractAction createIncreaseVideoNumberAction() {
		return new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		};
	}

	public ItemListener createActionChangeResourceType(MainPanel panel,
			CommonListElements commonListElements) {
		return new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					String newValue = e.getItem()
									   .toString();
					LearningResourceType type = LearningResourceType.getTypeByString(
							newValue);
					if (type != null) {
						rowUpdater.changeResourceRowType(panel, type,
								commonListElements);
					}

				}

			}
		};
	}
}
