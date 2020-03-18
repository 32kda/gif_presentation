package com.onpositive.gifpresentation.render.swing;

import javax.swing.JComponent;

import com.onpositive.gifpresentation.core.model.ISlideContent;

public class ComponentContent implements ISlideContent {
	
	private final JComponent component;

	public ComponentContent(JComponent component) {
		super();
		this.component = component;
	}

	public JComponent getComponent() {
		return component;
	}

}
