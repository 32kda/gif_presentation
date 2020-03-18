package com.onpositive.gifpresentation.render.gif;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

import com.onpositive.gifpresentation.core.model.ISlide;
import com.onpositive.gifpresentation.render.ISlideRenderer;
import com.onpositive.gifpresentation.render.swing.IComponentCreator;
import com.onpositive.gifpresentation.render.swing.ScreenImage;
import com.onpositive.gifpresentation.render.swing.SwingComponentCreator;

public class SwingSlideRenderer implements ISlideRenderer {

	private IComponentCreator componentCreator;

	public SwingSlideRenderer(IComponentCreator componentCreator) {
		super();
		this.componentCreator = componentCreator;
	}
	
	public SwingSlideRenderer() {
		componentCreator = new SwingComponentCreator();
	}

	@Override
	public BufferedImage renderSlide(ISlide slide, int width, int height) {
		Component component = componentCreator.createComponent(slide);
		component.setSize(width,height);
		component.setPreferredSize(new Dimension(width, height));
		BufferedImage bufferedImage = ScreenImage.createImage((JComponent) component);
		return bufferedImage;
	}

}
