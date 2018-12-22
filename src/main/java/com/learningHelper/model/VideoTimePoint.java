package com.learningHelper.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class VideoTimePoint {

	@XStreamAlias("videoMinute")
	private int minute;

	@XStreamAlias("videoSecond")
	private int second;

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		this.second = second;
	}
}
