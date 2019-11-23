package com.learningHelper.listRow;

import com.guimaker.list.ListRowData;
import com.guimaker.model.CommonListElements;
import com.guimaker.panels.mainPanel.MainPanel;
import com.learningHelper.model.LearningResource;

public interface ResourceRow {
	public ListRowData<LearningResource> addElementsToPanel(LearningResource learningResource, MainPanel panel,
			CommonListElements<LearningResource> commonListElements);
}
