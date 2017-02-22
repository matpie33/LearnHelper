package com.learningHelper.gui;

import java.awt.Component;

import javax.swing.text.JTextComponent;

import com.learningHelper.resources.ResourceChangeHandler;
import com.learningHelper.timing.TimeCatcher;
import com.learningHelper.xml.XMLHelper;

public class FrameManager {
	
	private Frame frame;
	private TimeCatcher timer;
	private XMLHelper helper;
	private static volatile FrameManager instance;
	
	public FrameManager(){
		timer = new TimeCatcher(this);
		helper = new XMLHelper();
	}

	public void keyPressed(JTextComponent textComponent, ResourceChangeHandler res){
		if (!timer.cleanStopWatch()){
			helper.setTextComponentToWatch(textComponent, res);
		}
	}
	
	public void timePassed(){		
		tryToReplaceUrlPath();
	}
	
	private void tryToReplaceUrlPath(){
		try {
			timer.stopTimer();
			helper.saveUrlPath();
		} 
		catch (Exception e1) {
			e1.printStackTrace();
			frame.showMessageDialog(e1.getMessage(), true);
		}
	}
	
	public static synchronized FrameManager getInstance(){
		if (instance==null){
			instance = new FrameManager();
		}
		return instance;
	}
	
	public XMLHelper getHelper(){
		return helper;
	}
	
	public void focusLost(){
		if (timer.isRunning()){
			try {
				timer.stopTimer();
				helper.saveUrlPath();
			} 
			catch (Exception e) {
				frame.showMessageDialog(e.getMessage(), false);
				e.printStackTrace();
			}
		}
	}
	
	public void setComponent (Component text, ResourceChangeHandler resource){
		if (text instanceof JTextComponent == false){
			System.out.println("errrr");
		}
		helper.setTextComponentToWatch((JTextComponent)text, resource);
	}
	
	public void setFrame (Frame fr){
		frame=fr;
	}
	
}
