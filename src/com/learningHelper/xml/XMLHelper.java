package com.learningHelper.xml;

import java.io.IOException;
import java.util.List;

import javax.swing.text.JTextComponent;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.learningHelper.resources.Resource;
import com.learningHelper.resources.ResourceChangeHandler;

public class XMLHelper {
	
	private JTextComponent watchedTextComponent;
	private ResourceChangeHandler resourceChangeHandler;
	private XMLWriter writer;
	private String xmlFileName = "sources.xml";
	
	public XMLHelper (){
		writer = new XMLWriter();
	}

	public void replacePdfPath(Resource resource, String path) throws Exception{		
		writer.removeResource(resource);
		System.out.println("removed: "+resource);
		resource.setUrlAddress(path);
		writer.addResource(resource);
		writer.saveToXML(xmlFileName);
		System.out.println("added: "+resource);
		System.out.println("replaced");
	}
	
	public void saveUrlPath() throws Exception{
		if (resourceChangeHandler.equals(watchedTextComponent.getText())){
			return;
		}
		Resource oldResource = resourceChangeHandler.getResource();
		writer.removeResource(oldResource);		
		writer.addResource(resourceChangeHandler.updateAndReturn(watchedTextComponent.getText()));
		writer.saveToXML(xmlFileName);
	}
	
	public void setTextComponentToWatch(JTextComponent s, ResourceChangeHandler resource){
		resourceChangeHandler=resource;
		watchedTextComponent=s;
	}
	
	public void setResourceHandler(ResourceChangeHandler resource){
		resourceChangeHandler = resource;
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
