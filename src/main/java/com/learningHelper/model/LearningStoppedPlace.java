package com.learningHelper.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("learningStoppedPlace")
public class LearningStoppedPlace {

	@XStreamAlias("textFragment")
	private String textFragmentPlace;

	@XStreamAlias("videoTimePoint")
	private VideoTimePoint videoTimePoint;


	public void setTextFragmentPlace(String textFragmentPlace) {
		this.textFragmentPlace = textFragmentPlace;
		videoTimePoint = null;
	}

	public static LearningStoppedPlace empty() {
		return new LearningStoppedPlace();
	}

	public String getTextFragmentPlace() {
		return textFragmentPlace;
	}

	public VideoTimePoint getVideoTimePoint() {
		return videoTimePoint;
	}

	public void initializeVideoTimePoint (){
		videoTimePoint = new VideoTimePoint();
	}

	public String getVideoMinute() {
		return "" + videoTimePoint.getMinute();
	}

	public String getVideoSecond() {
		return "" + videoTimePoint.getSecond();
	}

	public void setVideoMinute(Integer videoMinute) {
		videoTimePoint.setMinute(videoMinute);
	}

	public void setVideoSecond(Integer videoSecond) {
		videoTimePoint.setSecond(videoSecond);
	}

	@Override
	public String toString() {
		return "video range: " + videoTimePoint + " text: " + textFragmentPlace;
	}

	public void clear() {
		videoTimePoint = null;
		textFragmentPlace = null;
	}
}
