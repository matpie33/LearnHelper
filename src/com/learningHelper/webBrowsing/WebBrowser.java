package com.learningHelper.webBrowsing;

import com.guimaker.utilities.ThreadUtilities;
import com.learningHelper.model.LearningResource;
import com.learningHelper.model.StringListElement;

import java.awt.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;

public class WebBrowser {

	private static Desktop desktop = Desktop.getDesktop();

	public static void browseAllResources(
			Collection<LearningResource> resources) {
		if (Desktop.isDesktopSupported()) {
			resources.forEach(resource -> browseResource(resource, false));
		}
	}

	private static void browseResource(LearningResource resource,
			boolean withCheckForEveryLocation) {
		if (resource.getAlternativeLocations()
					.isEmpty()) {
			return;
		}
		String value = resource.getAlternativeLocations()
							   .get(0)
							   .getValue();
		if (withCheckForEveryLocation) {
			checkWhichResourceLocationWorks(resource);
		}
		if (!value.isEmpty()){
			browseUrl(value);
		}
	}

	private static void browseUrl(String value) {
		try {
			value = appendProtocolIfNeeded(value);
			desktop.browse(new URI(value));
		}
		catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
	}

	private static String appendProtocolIfNeeded(String value) {
		if (!value.startsWith("http") && !value.startsWith("www")) {
			return "http://" + value;
		}
		return value;
	}

	public static void browseResource(LearningResource resource) {
		browseResource(resource, true);
	}

	private static void checkWhichResourceLocationWorks(
			LearningResource resource) {
		ThreadUtilities.callOnOtherThread(() -> checkLocations(resource));
	}

	private static void checkLocations(LearningResource resource) {
		boolean isFirst = true;
		for (StringListElement stringListElement : resource.getAlternativeLocations()) {
			if (stringListElement.getValue().isEmpty()){
				continue;
			}
			boolean isOk = testIfResourceLocationIsWorking(
					stringListElement.getValue());
			if (isOk) {
				if (!isFirst) {
					browseUrl(stringListElement.getValue());
				}
				return;
			}
			isFirst = false;

		}

	}

	private static boolean testIfResourceLocationIsWorking(String location) {
		try {
			return InetAddress.getByName(location)
							  .isReachable(2000);
		}
		catch (Exception e) {
			return false;
		}
	}

}
