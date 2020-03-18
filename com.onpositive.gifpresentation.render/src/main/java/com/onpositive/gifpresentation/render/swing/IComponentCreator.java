package com.onpositive.gifpresentation.render.swing;

import javax.swing.JComponent;

import com.onpositive.gifpresentation.core.model.ISlide;

public interface IComponentCreator {
	
	public JComponent createComponent(ISlide slide);
	
}
