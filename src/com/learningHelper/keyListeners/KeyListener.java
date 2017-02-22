package com.learningHelper.keyListeners;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.text.JTextComponent;

import com.learningHelper.gui.FrameManager;
import com.learningHelper.resources.ResourceChangeHandler;

public class KeyListener {
	
	
	public static KeyAdapter saveUrlWhenIdle(JTextComponent textComponent, ResourceChangeHandler res){
		return new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e){
				FrameManager.getInstance().keyPressed(textComponent, res);	
			}
		};
	}
}
