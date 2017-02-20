package com.kanji.graphicInterface;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.text.JTextComponent;

import com.gui.Frame;
import com.gui.FrameManager;
import com.kanji.panels.ConfirmPanel;
import com.resources.PdfResource;
import com.resources.UrlResource;
import com.xml.XMLHelper;

public class ActionMaker {
	
	private static XMLHelper helper = FrameManager.getInstance().getHelper();
	
	public static void foo(){
		//force instantiating of the xml helper
	}
	
	public static AbstractAction createConfirmingAction (final ConfirmPanel panel, final boolean chosen){
		return new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.setAccepted(chosen);
			}
		};
	}

	public static WindowListener createClosingListener (final SimpleWindow window){
		return new WindowAdapter (){
			@Override
			public void windowClosed(WindowEvent e){
				window.close();
			}
		};
	}
	
    public static AbstractAction createDisposingAction(final SimpleWindow dialog){
		return new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(dialog);
				dialog.dispose();
			}
		};
    }
    
    public static AbstractAction createPdfResource(final Frame frame){
		return new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PdfResource pdf = new PdfResource("");
				helper.addResource(pdf);
				try {
					frame.createResource(pdf);
				} 
				catch (Exception e1) {
					frame.showMessageDialog("Nie mozna utworzyc zrodla pdf. Sprawdz konsolę", true);
					e1.printStackTrace();
				}
				
			}
		};
    }
    
    public static AbstractAction createURLResource(final Frame frame){
		return new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UrlResource url = new UrlResource();
				helper.addResource(url);
				try {
					frame.createResource(url);
				} 
				catch (Exception e1) {
					frame.showMessageDialog("Nie mozna utworzyc zrodla webowego. Sprawdz konsolę", 
							true);
					e1.printStackTrace();

				}
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
					frame.showMessageDialog("Plik nie istnieje lub jest katalogiem", true);
				}
				if (Desktop.isDesktopSupported()) {
			          try {
			            Desktop.getDesktop().open(file);
			          }
			          catch (IOException ex) {
			        	  frame.showMessageDialog("Nie można otworzyć przeglądarki.", true);			        	  
			          }
			        } 
				  else { 
					  frame.showMessageDialog("Nie można odtworzyć desktopu", true); 
				  }
			}
		};
    }
    
    public static AbstractAction goToResourceURL (JTextComponent uriContainer, Frame frame){
    	return new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String uri = uriContainer.getText();
				String neededPrefix = "http://";
				if (!uri.startsWith(neededPrefix)){
					uri=neededPrefix+uri;
				}
				URI urii = null;
				try {
					urii = new URI(uri);
				} catch (URISyntaxException e1) {
					frame.showMessageDialog("Niepoprawny uri", true);
					return;
				}
				  if (Desktop.isDesktopSupported()) {
			          try {
			            Desktop.getDesktop().browse(urii);
			          }
			          catch (IOException ex) {
			        	  frame.showMessageDialog("Nie można otworzyć przeglądarki.", true);			        	  
			          }
			        } 
				  else { 
					  frame.showMessageDialog("Nie można odtworzyć desktopu", true); 
				  }
			}
    	};
    }
		
    public static AbstractAction openFile(Frame frame, JTextComponent componentWithPath){
		return new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser ();
				int option = chooser.showOpenDialog(frame.getWindow());
				if (option == JFileChooser.APPROVE_OPTION){
					String oldPath = componentWithPath.getText();
					File file = chooser.getSelectedFile();
					String path = file.getAbsolutePath();
					componentWithPath.setText(path);
					try {
						helper.replacePdfPath(oldPath, path, frame);
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
