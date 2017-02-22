package com.learningHelper.xml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.learningHelper.resources.Resource;
import com.learningHelper.resources.ResourceType;

public class XMLWriter {
	
	private List <Resource> resources;
	private String rootNode= "Zrodla";
	private String pdfNode = "Pdf";
	private String pdfPath = "Sciezka";
	private String pdfPage = "Strona";
	
	private String urlNode = "Internetowe";
	private String urlLocation = "Adres";
	private String urlStartingPoint = "MiejsceStartu";
	
	
	
	public XMLWriter(){
		resources = new ArrayList <>();
	}
	
	public void addResource (Resource r){
		resources.add(r);
		System.out.println(resources);
//		for (Resource res: resources){
//			if (res instanceof PdfResource){
//				PdfResource pdf = (PdfResource) res;
//			}
//		}
	}
	
	public void replacePdfResourcePath(String oldPath, String newPath) throws Exception{
//		for (Resource res: resources){
//			if (res instanceof PdfResource){
//				PdfResource pdf = (PdfResource) res;
//				if (pdf.getPath().equals(oldPath)){
//					pdf.setPath(newPath);
//					return;
//				}
//			}
//		}
//		throw new Exception ("Nie znalazlem pdf'a ze sciezka: "+oldPath);
	}
	
	public void removeResource (Resource r){
		resources.remove(r);
	}
	
	

	public void saveToXML(String xml) {
	    Document dom;
	    Element e = null;
	    
	    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	    try {
	    	
	        DocumentBuilder db = dbf.newDocumentBuilder();	       
	        dom = db.newDocument();

	        Element rootElement = dom.createElement(rootNode);
	        
	        for (Resource resource: resources){
	        	String path = resource.getUrlAddress();
        		String page = resource.getStartingPlace();
        		Element e1 = null;
        		Element e2 = null;
	        	if (resource.getType().equals(ResourceType.PDF)){
	        		e = dom.createElement(pdfNode);
	    	        e1 = dom.createElement(pdfPath);
	    	        e2 = dom.createElement(pdfPage);
	        	}
	        	else if (resource.getType().equals(ResourceType.URL)){
	        		e = dom.createElement(urlNode);
	    	        e1 = dom.createElement(urlLocation);
	    	        e2 = dom.createElement(urlStartingPoint);
	        	}
	        	e1.appendChild(dom.createTextNode(path));
    	        e.appendChild(e1);
    	        e2.appendChild(dom.createTextNode(page));
    	        e.appendChild(e2);
    	        rootElement.appendChild(e); 
	        }    	        	              

	        dom.appendChild(rootElement);

	        try {
	            Transformer tr = TransformerFactory.newInstance().newTransformer();
	            tr.setOutputProperty(OutputKeys.INDENT, "yes");
	            tr.setOutputProperty(OutputKeys.METHOD, "xml");
	            tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	            tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
	            tr.transform(new DOMSource(dom), new StreamResult(new FileOutputStream(xml)));
	        } 
	        catch (TransformerException te) {
	            System.out.println(te.getMessage());
	        } 
	        catch (IOException ioe) {
	            System.out.println(ioe.getMessage());
	        }
	    }
	    catch (ParserConfigurationException pce) {
	        System.out.println("UsersXML: Error trying to instantiate DocumentBuilder " + pce);
	    }
	}
	
	public List <Resource> readFromXml(String filename) throws ParserConfigurationException, SAXException, IOException{
		File file = new File(filename);
		if (!file.exists()){
			return new ArrayList <>();
		}
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
		        .newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document = documentBuilder.parse(file);
		List <Resource> resources = new ArrayList <> ();
		
		NodeList list = document.getElementsByTagName(urlNode);
		for (int i=0; i<list.getLength(); i++){
			Node node = list.item(i);
			Element element = getXmlElement(node);			
			Resource url = readUrlResource(element);
			resources.add(url);
		}
		
		NodeList listPdf = document.getElementsByTagName(pdfNode);
		for (int i=0; i<listPdf.getLength(); i++){
			Node node = listPdf.item(i);
			Element element = getXmlElement(node);			
			Resource pdf = readPdfResource(element);
			System.out.println("pdf: "+pdf.getUrlAddress());
			resources.add(pdf);
		}
		this.resources=resources;
		return resources;
	}
	
	private Element getXmlElement (Node node){
		Element element = null;
		if (node.getNodeType() == Node.ELEMENT_NODE){
			element = (Element) node;
		}
		return element;
	}
	
	private Resource readUrlResource (Element element){
		String location = element.getElementsByTagName(urlLocation).item(0)
				.getTextContent();
		String startingPoint = element.getElementsByTagName(urlStartingPoint)
				.item(0).getTextContent();
		return new Resource(ResourceType.URL, location, startingPoint);		
	}
	
	private Resource readPdfResource(Element element){
		String path = element.getElementsByTagName(pdfPath).item(0)
				.getTextContent();
		String page = element.getElementsByTagName(pdfPage)
				.item(0).getTextContent();
		return new Resource(ResourceType.PDF, path, page);
	}
	
}
