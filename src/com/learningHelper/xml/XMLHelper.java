package com.learningHelper.xml;

import java.io.IOException;
import java.util.List;

import javax.swing.text.JTextComponent;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.learningHelper.gui.Frame;
import com.learningHelper.resources.Resource;

public class XMLHelper {
	
	private JTextComponent watched;
	private String toReplace;
	private XMLWriter writer;
	private String xmlFileName = "sources.xml";
	
	public XMLHelper (){
		writer = new XMLWriter();
	}

	public void replacePdfPath(String oldPath, String path, Frame frame) throws Exception{		
		writer.replacePdfResourcePath(oldPath,path);
		writer.saveToXML(xmlFileName);		
	}
	
	public void saveUrlPath() throws Exception{
		if (toReplace.equals(watched.getText())){
			return;
		}
		writer.replaceUrlResourcePath(toReplace, watched.getText());
		writer.saveToXML(xmlFileName);
		
	}
	
	
	public void setTextComponentToWatch(JTextComponent s){
		toReplace=s.getText();
		watched=s;
		System.out.println("now watching "+s.getText());
	}
	
	public void save(){
		writer.saveToXML(xmlFileName);		
	}
	
	public void addResource(Resource res){
		writer.addResource(res);
	}
	
	public List <Resource> getResources() throws ParserConfigurationException, SAXException, IOException{
		return writer.readFromXml(xmlFileName);
	}
	
	
}
