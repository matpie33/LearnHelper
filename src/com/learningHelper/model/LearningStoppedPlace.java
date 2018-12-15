package com.learningHelper.model;

import com.guimaker.utilities.Range;

public class LearningStoppedPlace {

	private String textFragmentPlace;
	private Range videoTimeRangePlace;

	public void setTextFragmentPlace(String textFragmentPlace) {
		this.textFragmentPlace = textFragmentPlace;
		videoTimeRangePlace = null;
	}

	public static LearningStoppedPlace empty() {
		return new LearningStoppedPlace();
	}

	public String getTextFragmentPlace() {
		return textFragmentPlace;
	}

	public Range getVideoTimeRangePlace() {
		return videoTimeRangePlace;
	}

	public void setVideoTimeRangeStart(Integer videoTimeRangeStart) {
		int rangeEnd = videoTimeRangePlace != null ?
				videoTimeRangePlace.getRangeEnd() :
				Integer.MAX_VALUE;
		videoTimeRangePlace = new Range(videoTimeRangeStart, rangeEnd);
		textFragmentPlace = null;
	}

	public void setVideoTimeRangeEnd(Integer videoTimeRangeEnd) {
		int rangeStart = videoTimeRangePlace != null ?
				videoTimeRangePlace.getRangeStart() :
				0;
		videoTimeRangePlace = new Range(rangeStart, videoTimeRangeEnd);
		textFragmentPlace = null;
	}

	@Override
	public String toString() {
		return "video range: " + videoTimeRangePlace + " text: "
				+ textFragmentPlace;
	}

	public void clear() {
		videoTimeRangePlace = null;
		textFragmentPlace = null;
	}
}
