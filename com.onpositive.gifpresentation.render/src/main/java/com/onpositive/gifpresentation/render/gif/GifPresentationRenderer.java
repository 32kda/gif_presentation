package com.onpositive.gifpresentation.render.gif;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import com.madgag.gif.fmsware.AnimatedGifEncoder;
import com.onpositive.gifpresentation.core.model.IPresentation;
import com.onpositive.gifpresentation.core.model.ISlide;
import com.onpositive.gifpresentation.render.IPresentationRenderer;
import com.onpositive.gifpresentation.render.ISlideRenderer;

public class GifPresentationRenderer implements IPresentationRenderer {

	private ISlideRenderer slideRenderer;

	public GifPresentationRenderer() {
		slideRenderer = new SwingSlideRenderer();
	}

	public GifPresentationRenderer(ISlideRenderer slideRenderer) {
		super();
		this.slideRenderer = slideRenderer;
	}

	@Override
	public void renderPresentation(IPresentation presentation, int width, int height, int delay, File outputFile) {
		AnimatedGifEncoder e = new AnimatedGifEncoder();
		e.start(outputFile.getAbsolutePath());
		e.setDelay(delay);
		List<ISlide> slides = presentation.getSlides();
		for (ISlide slide : slides) {
			BufferedImage img = slideRenderer.renderSlide(slide, width, height);
			e.addFrame(img);
		}
		e.finish();
	}

}
