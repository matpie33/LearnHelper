package com.learningHelper.listRow;

import com.guimaker.model.CommonListElements;
import com.guimaker.panels.MainPanel;
import com.learningHelper.model.LearningResource;

public interface ResourceRow {
	public void addElementsToPanel(LearningResource learningResource, MainPanel panel,
			CommonListElements<LearningResource> commonListElements);
}
