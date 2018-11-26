package com.learningHelper.model;

import com.guimaker.list.ListElement;
import com.guimaker.list.ListElementInitializer;
import com.sun.org.apache.xerces.internal.xs.StringList;

public class StringListElement implements ListElement {

	private String value;

	public StringListElement(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static ListElementInitializer<StringListElement> getInitializer(){
		return ()-> new StringListElement("");
	}


	@Override
	public boolean isEmpty() {
		return value.isEmpty();
	}

	@Override
	public String getDisplayedText() {
		return null;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString (){
		return value;
	}


}
