package com.keyListeners;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import com.gui.FrameManager;

public class FocusListeners {
	
	public static FocusAdapter stopTimerWhenFocusLost(){
		return new FocusAdapter(){
			@Override
			public void focusLost (FocusEvent e){
				FrameManager.getInstance().focusLost();
			}
		};
	}
	
	public static FocusAdapter setComponentToWatchWhenFocused(){
		return new FocusAdapter(){
			@Override
			public void focusGained (FocusEvent e){
				System.out.println("component is: "+e.getComponent().getClass());
				FrameManager.getInstance().setComponent(e.getComponent());
			}
		};
	}

}
