package com.onpositive.gifpresentation.render.gif;

import java.awt.Component;
import java.awt.image.BufferedImage;

import com.onpositive.gifpresentation.core.model.ISlide;
import com.onpositive.gifpresentation.render.ISlideRenderer;
import com.onpositive.gifpresentation.render.swing.IComponentCreator;
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
		component.setSize(width, height);
		component.doLayout();
		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		component.paint(bufferedImage.getGraphics());
		return bufferedImage;
	}

}
