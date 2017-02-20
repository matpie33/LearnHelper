package com.gui;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.xml.XMLWriter;

public class StartLearnHelper {
	
	public static void main (String [] args) throws ParserConfigurationException, SAXException, IOException{
		Frame f = new Frame();
		new XMLWriter().readFromXml("xml.xml");
		
	}

}
