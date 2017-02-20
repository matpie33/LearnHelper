package com.xml;

import java.io.IOException;
import java.util.List;

import javax.swing.text.JTextComponent;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.gui.Frame;
import com.resources.Resource;

public class XMLHelper {
	
	private JTextComponent watched;
	private String toReplace;
	private XMLWriter writer;
	
	public XMLHelper (){
		writer = new XMLWriter();
	}

	public void replacePdfPath(String oldPath, String path, Frame frame) throws Exception{		
		writer.replacePdfResourcePath(oldPath,path);
		writer.saveToXML("xml.xml");		
	}
	
	public void saveUrlPath() throws Exception{
		if (toReplace.equals(watched.getText())){
			return;
		}
		writer.replaceUrlResourcePath(toReplace, watched.getText());
		writer.saveToXML("xml.xml");
		System.out.println("saving: "+toReplace+" into "+watched.getText());
		
	}
	
	
	public void setTextComponentToWatch(JTextComponent s){
		toReplace=s.getText();
		watched=s;
		System.out.println("now watching "+s.getText());
	}
	
	public void save(){
		writer.saveToXML("xml.xml");		
	}
	
	public void addResource(Resource res){
		writer.addResource(res);
	}
	
	public List <Resource> getResources() throws ParserConfigurationException, SAXException, IOException{
		return writer.readFromXml("xml.xml");
	}
	
	
}
