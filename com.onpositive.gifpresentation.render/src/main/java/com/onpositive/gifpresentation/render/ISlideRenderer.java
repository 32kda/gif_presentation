package com.onpositive.gifpresentation.render;

import java.awt.image.BufferedImage;

import com.onpositive.gifpresentation.core.model.ISlide;

public interface ISlideRenderer {
	
	public BufferedImage renderSlide(ISlide slide, int width, int height);

}
