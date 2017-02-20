package com.learningHelper.keyListeners;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.text.JTextComponent;

import com.learningHelper.gui.FrameManager;

public class KeyListener {
	
	
	public static KeyAdapter saveUrlWhenIdle(JTextComponent textComponent){
		return new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e){
				FrameManager.getInstance().keyPressed(textComponent);	
			}
		};
	}
}
