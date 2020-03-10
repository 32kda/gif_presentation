package com.onpositive.gifpresentation.render;

import java.io.File;

import com.onpositive.gifpresentation.core.model.IPresentation;

public interface IPresentationRenderer {

	void renderPresentation(IPresentation presentation, int width, int height, int delay, File outputFile);
	
}
