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

import com.learningHelper.resources.PdfResource;
import com.learningHelper.resources.Resource;
import com.learningHelper.resources.UrlResource;

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
		for (Resource res: resources){
			if (res instanceof PdfResource){
				PdfResource pdf = (PdfResource) res;
			}
		}
	}
	
	public void replacePdfResourcePath(String oldPath, String newPath) throws Exception{
		for (Resource res: resources){
			if (res instanceof PdfResource){
				PdfResource pdf = (PdfResource) res;
				if (pdf.getPath().equals(oldPath)){
					pdf.setPath(newPath);
					return;
				}
			}
		}
		throw new Exception ("Nie znalazlem pdf'a ze sciezka: "+oldPath);
	}
	
	public void replaceUrlResourcePath(String oldPath, String newPath) throws Exception{
		for (Resource res: resources){
			if (res instanceof UrlResource){
				UrlResource url = (UrlResource) res;
				if (url.getUrlAddress().equals(oldPath)){
					url.setUrlAddress(newPath);
					resources.remove(res);
					resources.add(url);
					return;
				}
			}
		}
		throw new Exception ("Nie znalazlem URL'a ze sciezka: "+oldPath);
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
	        	if (resource instanceof PdfResource){
	        		PdfResource pdf = (PdfResource) resource;
	        		String path = pdf.getPath();
	        		int page = pdf.getPage();
	        		
	        		e = dom.createElement(pdfNode);
	        		
	    	        Element e1 = dom.createElement(pdfPath);
	    	        e1.appendChild(dom.createTextNode(path));
	    	        e.appendChild(e1);
	    	        
	    	        e1 = dom.createElement(pdfPage);
	    	        e1.appendChild(dom.createTextNode(""+page));
	    	        e.appendChild(e1);
	    	        rootElement.appendChild(e); 
	        	}
	        	else if (resource instanceof UrlResource){
	        		UrlResource urlRes = (UrlResource) resource;
	        		String start = urlRes.getStartingPlace();
	        		String url = urlRes.getUrlAddress();
	        		
	        		e = dom.createElement(urlNode);
	    	        Element e1 = dom.createElement(urlLocation);
	    	        e1.appendChild(dom.createTextNode(url));
	    	        e.appendChild(e1);
	    	        
	    	        e1 = dom.createElement(urlStartingPoint);
	    	        e1.appendChild(dom.createTextNode(start));
	    	        e.appendChild(e1);
	    	        rootElement.appendChild(e); 
	        	}
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
			UrlResource url = readUrlResource(element);
			resources.add(url);
		}
		
		NodeList listPdf = document.getElementsByTagName(pdfNode);
		for (int i=0; i<listPdf.getLength(); i++){
			Node node = listPdf.item(i);
			Element element = getXmlElement(node);			
			PdfResource pdf = readPdfResource(element);
			System.out.println("pdf: "+pdf.getPath());
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
	
	private UrlResource readUrlResource (Element element){
		String location = element.getElementsByTagName(urlLocation).item(0)
				.getTextContent();
		String startingPoint = element.getElementsByTagName(urlStartingPoint)
				.item(0).getTextContent();
		return new UrlResource(location, startingPoint);		
	}
	
	private PdfResource readPdfResource(Element element){
		String path = element.getElementsByTagName(pdfPath).item(0)
				.getTextContent();
		int page = Integer.parseInt(element.getElementsByTagName(pdfPage)
				.item(0).getTextContent());
		return new PdfResource(path, page);
	}
	
}
