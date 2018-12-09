package com.learningHelper.urlHandling;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VideoSeriesNumberIncrementer {

	private static final String PAD_WITH_3_ZEROES_REGEX = "%03d";

	public static String increment(String videoUrl) {
		Matcher matcher = Pattern.compile("\\d+")
								 .matcher(videoUrl);
		while (matcher.find()) {
			String seriesNumber = matcher.group();
			if (seriesNumber.length() == 3) {
				videoUrl = videoUrl.replace(seriesNumber,
						increaseNumberBy1(seriesNumber));
			}
		}
		return videoUrl;
	}

	private static String increaseNumberBy1(String seriesNumber) {
		int intValue = Integer.parseInt(seriesNumber);
		intValue++;
		return String.format(PAD_WITH_3_ZEROES_REGEX, intValue);
	}

}
