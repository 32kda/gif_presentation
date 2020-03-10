package com.onpositive.gifpresentation.render.swing;

import java.awt.Component;

import com.onpositive.gifpresentation.core.model.ISlideContent;

public class ComponentContent implements ISlideContent {
	
	private final Component component;

	public ComponentContent(Component component) {
		super();
		this.component = component;
	}

	public Component getComponent() {
		return component;
	}

}
