package com.learningHelper.gui;

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
import com.learningHelper.keyListeners.FocusListeners;
import com.learningHelper.keyListeners.KeyListener;
import com.learningHelper.resources.ChangeType;
import com.learningHelper.resources.Resource;
import com.learningHelper.resources.ResourceChangeHandler;
import com.learningHelper.resources.ResourceType;
import com.learningHelper.resources.YoutubeResource;
import com.learningHelper.strings.ButtonsLabels;
import com.learningHelper.strings.Exceptions;
import com.learningHelper.strings.Labels;
import com.learningHelper.strings.Titles;
import com.learningHelper.xml.XMLHelper;

public class Frame extends SimpleWindow { 
	private MainPanel  panel;
	private SimpleWindow subWindow;
	private List <MainPanel> panelWithLearningResources;
	private JButton buttonAddPdfResource;
	private JButton buttonAddURLResource;
	private JButton buttonAddYoutubeResource;
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
		XMLHelper helper = FrameManager.getInstance().getHelper();
		try {
			resources = helper.getResources();
			FrameManager.getInstance().setFrame(this);
		} 
		catch (ParserConfigurationException | SAXException | IOException e) {
			showMessageDialog(Exceptions.EXCEPTION_READING_XML_FILE, true);
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
		frame.setTitle(Titles.APPLICATION_TITLE);
	}
	
		
	public void createResource(Resource resource){
		JTextField textResourcePath = null;
		JTextField textFinishedPlace = null;
		JTextField youtubeMinutes = null;
		JLabel youtubeSeparator = GuiMaker.createLabel(":");
		JTextField youtubeSeconds = null;
		String resourceLabel = null, finishedPlaceLabel = null;
		ActionListener goToResourceListener = null;
		JButton specifyFile = null;
			if (resource.getType().equals(ResourceType.PDF)){
				textFinishedPlace = GuiMaker.createTextField(20);
				textResourcePath =  GuiMaker.createTextField(10, resource.getUrlAddress(), false);
				textFinishedPlace.setText(""+resource.getStartingPlace());
				resourceLabel = Labels.FILE_LOCATION;
				finishedPlaceLabel = Labels.FINISHED_PAGE_LOCATION;
				goToResourceListener = ActionMaker.goToPdfResource(textResourcePath, this);
				specifyFile = GuiMaker.createButton(ButtonsLabels.SELECT_FILE, 
						ActionMaker.openFile(this, textResourcePath, resource));
			}
			
			else if (resource.getType().equals(ResourceType.URL)){
				textFinishedPlace = GuiMaker.createTextField(20);
				textResourcePath = GuiMaker.createTextField(10, resource.getUrlAddress(), true);
				ResourceChangeHandler handler = new ResourceChangeHandler(resource, ChangeType.PATH);
				textResourcePath.addKeyListener(KeyListener.saveUrlWhenIdle(textResourcePath, handler));
				textResourcePath.addFocusListener(FocusListeners.stopTimerWhenFocusLost());
				textResourcePath.addFocusListener(FocusListeners.setComponentToWatchWhenFocused(handler));
				textFinishedPlace.setText(resource.getStartingPlace());
				resourceLabel = Labels.URL_ADDRESS;
				finishedPlaceLabel = Labels.FINISHED_WORD_LOCATION;
				goToResourceListener = ActionMaker.goToResourceURL(textResourcePath, this);
			}
			else if (resource.getType().equals(ResourceType.YOUTUBE)){
				System.out.println("found youtube res" + resource);
				YoutubeResource youtubeRes = (YoutubeResource) resource;
				textResourcePath = GuiMaker.createTextField(10, resource.getUrlAddress(), true);
				ResourceChangeHandler handler = new ResourceChangeHandler(resource, ChangeType.PATH);
				textResourcePath.addKeyListener(KeyListener.saveUrlWhenIdle(textResourcePath, handler));
				textResourcePath.addFocusListener(FocusListeners.stopTimerWhenFocusLost());
				textResourcePath.addFocusListener(FocusListeners.setComponentToWatchWhenFocused(handler));
				ResourceChangeHandler minutesHandler = new ResourceChangeHandler
						(resource, ChangeType.YOUTUBE_MINUTES);
				youtubeMinutes = GuiMaker.createTextField(5);
				youtubeMinutes.setText(""+youtubeRes.getMinutes());
				youtubeMinutes.addKeyListener(KeyListener.acceptDigitsOnly(youtubeSeconds));
				youtubeMinutes.addKeyListener(KeyListener.saveUrlWhenIdle(youtubeMinutes, minutesHandler));
				youtubeMinutes.addFocusListener(FocusListeners.stopTimerWhenFocusLost());
				youtubeMinutes.addFocusListener(FocusListeners.setComponentToWatchWhenFocused(minutesHandler));
				ResourceChangeHandler secondsHandler = new ResourceChangeHandler
						(resource, ChangeType.YOUTUBE_SECONDS);
				youtubeSeconds = GuiMaker.createTextField(5);
				youtubeSeconds.setText(""+youtubeRes.getSeconds());
				youtubeSeconds.addKeyListener(KeyListener.acceptDigitsOnly(youtubeSeconds));
				youtubeSeconds.addKeyListener(KeyListener.saveUrlWhenIdle(youtubeSeconds, secondsHandler));
				youtubeSeconds.addFocusListener(FocusListeners.stopTimerWhenFocusLost());
				youtubeSeconds.addFocusListener(FocusListeners.setComponentToWatchWhenFocused(secondsHandler));
				resourceLabel = Labels.URL_ADDRESS;
				finishedPlaceLabel = Labels.FINISHED_WORD_LOCATION;
				goToResourceListener = ActionMaker.goToYoutubeURL(textResourcePath, 
						youtubeMinutes, youtubeSeconds, this);
			}
			
			if (textFinishedPlace != null){
				ResourceChangeHandler handler = new ResourceChangeHandler(resource, ChangeType.STARTING_POINT);
				textFinishedPlace.addKeyListener(KeyListener.saveUrlWhenIdle(textFinishedPlace, handler));
				textFinishedPlace.addFocusListener(FocusListeners.stopTimerWhenFocusLost());
				textFinishedPlace.addFocusListener(FocusListeners.setComponentToWatchWhenFocused(handler));
			}
			
		JButton buttonGoToResource=GuiMaker.createButton(ButtonsLabels.GO_TO_SOURCE, goToResourceListener);
		
		JLabel labelResourceName = GuiMaker.createLabel(resourceLabel);
		JLabel labelFinishedPlace = GuiMaker.createLabel(finishedPlaceLabel);
				
		MainPanel panel = new MainPanel(BasicColors.DARK_GREEN, true);
		panelWithLearningResources.add(panel);
		
		panel.addRow(RowMaker.createHorizontallyFilledRow(labelResourceName, textResourcePath).
				fillHorizontallySomeElements(textResourcePath));
		if (textFinishedPlace!=null){
			panel.addRow(RowMaker.createHorizontallyFilledRow(labelFinishedPlace, textFinishedPlace).
					fillHorizontallySomeElements(textFinishedPlace));
		}
		else{
			panel.addRow(RowMaker.createUnfilledRow(GridBagConstraints.WEST,
					labelFinishedPlace, youtubeMinutes, youtubeSeparator, youtubeSeconds));
		}
		
		JPanel newPanel = panel.addRow(RowMaker.createUnfilledRow(GridBagConstraints.WEST, buttonGoToResource));
		if (specifyFile != null){
			panel.addElementsToRow(newPanel, specifyFile);
		}
		
		scrollPanel.addRow(RowMaker.createHorizontallyFilledRow(panel.getPanel()));
		scrollBottom();
		
	}
	
	private void addElements(){
		buttonAddPdfResource = GuiMaker.createButton(ButtonsLabels.ADD_PDF_RESOURCE, 
				ActionMaker.createPdfResource(this));
		buttonAddURLResource = GuiMaker.createButton(ButtonsLabels.ADD_URL_RESOURCE, 
				ActionMaker.createURLResource(this));
		buttonAddYoutubeResource = GuiMaker.createButton(ButtonsLabels.ADD_YOUTUBE_RESOURCE, 
				ActionMaker.createYoutubeResource(this));
		panel.addRow(RowMaker.createUnfilledRow(GridBagConstraints.NORTHWEST, buttonAddPdfResource,
				buttonAddURLResource, buttonAddYoutubeResource));
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
