package com.learningHelper.keyListeners;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import com.learningHelper.gui.FrameManager;
import com.learningHelper.resources.ResourceChangeHandler;

public class FocusListeners {
	
	public static FocusAdapter stopTimerWhenFocusLost(){
		return new FocusAdapter(){
			@Override
			public void focusLost (FocusEvent e){
				FrameManager.getInstance().focusLost();
			}
		};
	}
	
	public static FocusAdapter setComponentToWatchWhenFocused(ResourceChangeHandler res){
		return new FocusAdapter(){
			@Override
			public void focusGained (FocusEvent e){
				System.out.println("handler is: "+res.getChangeType());
				FrameManager.getInstance().setComponent(e.getComponent(), res);
			}
		};
	}

}
