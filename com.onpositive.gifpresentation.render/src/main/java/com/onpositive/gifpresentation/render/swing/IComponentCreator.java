package com.onpositive.gifpresentation.render.swing;

import java.awt.Component;

import com.onpositive.gifpresentation.core.model.ISlide;

public interface IComponentCreator {
	
	public Component createComponent(ISlide slide);
	
}
