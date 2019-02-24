package com.learningHelper.naruto;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class NarutoLinkModifierToSkipChoosingVideoPlayer {

	private static final String CLASS_NAME_VIDEO_PLAYER_LINK = "odtwarzacz_link";
	private static final String ATTRIBUTE_CONTAINING_VIDEO_PLAYER_LINK = "rel";
	private static final String CLASS_TAG_TABLE_COLUMN = "td";
	private static final String NARUTO_VIDEO_PLAYER_URL_TEMPLATE = "https://naruto.wbijam.pl/odtwarzacz-%s.html";
	private static String CLASS_NAME_FOR_TABLE_WITH_VIDEO_PLAYERS_LINKS = "lista";
	private static String TEXT_IDENTIFYING_CDA_PLAYER_ROW = "cda";

	public String modifyLinkForNaruto(String value,
			boolean skipVideoPlayerTypeChossingForNaruto) throws IOException {
		if (!skipVideoPlayerTypeChossingForNaruto) {
			return value;
		}

		Document doc = Jsoup.connect(value)
							.get();
		Elements table = doc.getElementsByClass(
				CLASS_NAME_FOR_TABLE_WITH_VIDEO_PLAYERS_LINKS);
		Elements rowWithCDAPlayer = table.get(0)
										 .getElementsContainingText(
												 TEXT_IDENTIFYING_CDA_PLAYER_ROW);

		String directLinkToPlayer = "";
		for (Element element : rowWithCDAPlayer) {
			if (element.is(CLASS_TAG_TABLE_COLUMN)) {
				Element parent = element.parent();
				directLinkToPlayer = parent.getElementsByClass(
						CLASS_NAME_VIDEO_PLAYER_LINK)
										   .get(0)
										   .attributes()
										   .get(ATTRIBUTE_CONTAINING_VIDEO_PLAYER_LINK);
				break;
			}
		}
		return String.format(NARUTO_VIDEO_PLAYER_URL_TEMPLATE,
				directLinkToPlayer);
	}
}
