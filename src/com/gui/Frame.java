package com.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.guimaker.colors.BasicColors;
import com.guimaker.panels.GuiMaker;
import com.guimaker.panels.MainPanel;
import com.guimaker.row.RowMaker;
import com.guimaker.window.SimpleWindow;
import com.kanji.graphicInterface.ActionMaker;
import com.keyListeners.FocusListeners;
import com.keyListeners.KeyListener;
import com.resources.PdfResource;
import com.resources.Resource;
import com.resources.UrlResource;

public class Frame extends SimpleWindow { 
	private MainPanel  panel;
	private SimpleWindow subWindow;
	private List <MainPanel> panelWithLearningResources;
	private JButton buttonAddPdfResource;
	private JButton buttonAddURLResource;
	private MainPanel scrollPanel;
	private JScrollPane scrollPane;
	
	public Frame(){	
		panelWithLearningResources = new ArrayList <MainPanel> ();
		panel = new MainPanel(BasicColors.LIGHT_VIOLET);
		scrollPanel = new MainPanel (BasicColors.LIGHT_BLUE);
		subWindow = new SimpleWindow();		
		addElements();
		createWindowProperties();	
		List<Resource> resources = new ArrayList <>();
		try {
			resources = FrameManager.getInstance().getHelper().getResources();
			FrameManager.getInstance().setFrame(this);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			showMessageDialog("Exception initializing xml helper", true);
		}
		for (Resource r: resources){
			createResource(r);
		}
		
	}
	
	private void createWindowProperties(){		
		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setContentPane(panel.getPanel());
		frame.setSize(new Dimension(800,600));
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setTitle("Pomocnik do nauki");
	}
	
	public void createResource(Resource type){
		
		JTextField textResourcePath = null;
		JTextField textFinishedPlace = GuiMaker.createTextField(20);
		String resourceLabel = null, finishedPlaceLabel = null;
		ActionListener goToResourceListener = null;
		JButton specifyFile = null;
			if (type instanceof PdfResource){
				PdfResource pdf = (PdfResource) type;
				textResourcePath =  GuiMaker.createTextField(10, pdf.getPath(), false);
				textFinishedPlace.setText(""+pdf.getPage());
				resourceLabel = "Lokalizacja pliku: ";
				finishedPlaceLabel = "Na której stronie skończyłem naukę: ";
				goToResourceListener = ActionMaker.goToPdfResource(textResourcePath, this);
				specifyFile = GuiMaker.createButton("Wskaż plik", ActionMaker.openFile(this, textResourcePath));
			}
			else if (type instanceof UrlResource){
				UrlResource url = (UrlResource) type;
				textResourcePath = GuiMaker.createTextField(10, url.getUrlAddress(), true);
				textResourcePath.addKeyListener(KeyListener.saveUrlWhenIdle(textResourcePath));
				textResourcePath.addFocusListener(FocusListeners.stopTimerWhenFocusLost());
				textResourcePath.addFocusListener(FocusListeners.setComponentToWatchWhenFocused());
				textFinishedPlace.setText(url.getStartingPlace());
				resourceLabel = "Adres URL: ";
				finishedPlaceLabel = "Miejsce na którym przerwałem: ";
				goToResourceListener = ActionMaker.goToResourceURL(textResourcePath, this);
				
			}
		
		textFinishedPlace.addKeyListener(KeyListener.saveUrlWhenIdle(textFinishedPlace));
		textFinishedPlace.addFocusListener(FocusListeners.stopTimerWhenFocusLost());
		textFinishedPlace.addFocusListener(FocusListeners.setComponentToWatchWhenFocused());
			
		JButton buttonGoToResource=GuiMaker.createButton("Otwórz źródło", goToResourceListener);
		
		JLabel labelResourceName = GuiMaker.createLabel(resourceLabel);
		JLabel labelFinishedPlace = GuiMaker.createLabel(finishedPlaceLabel);
				
		MainPanel panel = new MainPanel(BasicColors.DARK_GREEN);
		panelWithLearningResources.add(panel);
		
		panel.addRow(RowMaker.createHorizontallyFilledRow(labelResourceName, textResourcePath).
				fillHorizontallySomeElements(textResourcePath));
		
		panel.addRow(RowMaker.createHorizontallyFilledRow(labelFinishedPlace, textFinishedPlace).
				fillHorizontallySomeElements(textFinishedPlace));
		JPanel newPanel = panel.addRow(RowMaker.createUnfilledRow(GridBagConstraints.WEST, buttonGoToResource));
		if (specifyFile != null){
			panel.addElementsToRow(newPanel, specifyFile);
		}
		
		scrollPanel.addRow(RowMaker.createHorizontallyFilledRow(panel.getPanel()));
		scrollBottom();
		
	}
	
	private void addElements(){
		buttonAddPdfResource = GuiMaker.createButton("Dodaj źródło pdfowe", ActionMaker.createPdfResource(this));
		buttonAddURLResource = GuiMaker.createButton("Dodaj źródło URL", ActionMaker.createURLResource(this));
		panel.addRow(RowMaker.createUnfilledRow(GridBagConstraints.NORTHWEST, buttonAddPdfResource,
				buttonAddURLResource));
		scrollPane = new JScrollPane(scrollPanel.getPanel());
		panel.addRow(RowMaker.createBothSidesFilledRow(scrollPane));
	}
	
	private void scrollBottom(){
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run (){
				JScrollBar vertical = scrollPane.getVerticalScrollBar();
				vertical.setValue( vertical.getMaximum() );
			}
		});		
	}
	
	
}
