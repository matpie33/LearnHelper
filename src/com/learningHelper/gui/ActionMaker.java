package com.learningHelper.gui;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.text.JTextComponent;

import com.guimaker.keyBinding.SimpleActionMaker;
import com.learningHelper.resources.Resource;
import com.learningHelper.resources.ResourceType;
import com.learningHelper.strings.Exceptions;
import com.learningHelper.xml.XMLHelper;

public class ActionMaker extends SimpleActionMaker {
	
	private static XMLHelper helper = FrameManager.getInstance().getHelper();
	private final static String PROTOCOL_HTTP = "http";
	private final static String PROTOCOL_HTTPS = "https";
	private final static String AFTER_PROTOCOL = "://";
	
	public static void foo(){
		//force instantiating of the xml helper
	}
    
    public static AbstractAction createPdfResource(final Frame frame){
		return new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Resource pdf = new Resource(ResourceType.PDF);
				helper.addResource(pdf);
				frame.createResource(pdf);
			}
		};
    }
    
    public static AbstractAction createURLResource(final Frame frame){
		return new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Resource url = new Resource(ResourceType.URL);
				helper.addResource(url);
				frame.createResource(url);
			}
		};
    }
    
    public static AbstractAction goToPdfResource(final JTextComponent componentWithPath, Frame frame){
		return new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				File file = new File (componentWithPath.getText());
//				try {
//					Runtime.getRuntime().exec("cmd /c start command.bat");
//				} catch (IOException e1) {
//					frame.showMessageDialog("IO excpetion", true);
//				}
				if (!file.exists() || file.isDirectory()){
					frame.showMessageDialog(Exceptions.FILE_DOESNT_EXIST_OR_IS_DIRECTORY, true);
				}
				if (Desktop.isDesktopSupported()) {
			          try {
			            Desktop.getDesktop().open(file);
			          }
			          catch (IOException ex) {
			        	  frame.showMessageDialog(Exceptions.CANNOT_OPEN_FILE, true);			        	  
			          }
			        } 
				  else { 
					  frame.showMessageDialog(Exceptions.DESKTOP_NOT_SUPPORTED, true); 
				  }
			}
		};
    }
    
    public static AbstractAction goToResourceURL (JTextComponent textFieldWithUri, Frame frame){
    	return new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String uriText = textFieldWithUri.getText();
				if (!uriText.startsWith(PROTOCOL_HTTP) && !uriText.startsWith(PROTOCOL_HTTPS)){
					uriText = PROTOCOL_HTTP+AFTER_PROTOCOL+uriText;
				}
				URI uriObject = null;
				try {
					uriObject = new URI(uriText);
				} 
				catch (URISyntaxException e1) {
					frame.showMessageDialog(Exceptions.URI_SYNTAX_EXCEPTION, true);
					return;
				}
				  if (Desktop.isDesktopSupported()) {
			          try {
			            Desktop.getDesktop().browse(uriObject);
			          }
			          catch (IOException ex) {
			        	  ex.printStackTrace();
			        	  frame.showMessageDialog(Exceptions.CANNOT_OPEN_BROWSER_EXCEPTION, true);			        	  
			          }
			        } 
				  else { 
					  frame.showMessageDialog(Exceptions.DESKTOP_NOT_SUPPORTED, true); 
				  }
			}
    	};
    }
		
    public static AbstractAction openFile(Frame frame, JTextComponent componentWithPath, Resource resource){
		return new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser ();
				int option = chooser.showOpenDialog(frame.getWindow());
				if (option == JFileChooser.APPROVE_OPTION){
					File file = chooser.getSelectedFile();
					String path = file.getAbsolutePath();
					componentWithPath.setText(path);
					try {
						helper.replacePdfPath(resource, path);
					} 
					catch (Exception e1) {
						frame.showMessageDialog(e1.getMessage(), true);
						e1.printStackTrace();
					}
				}
				
				
			}
		};
    }
    
    public static AbstractAction saveUrlResource(Frame frame, JTextComponent componentWithPath){
		return new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				helper.save();
			}
		};
    }
    
      
    
    
    
    
}
