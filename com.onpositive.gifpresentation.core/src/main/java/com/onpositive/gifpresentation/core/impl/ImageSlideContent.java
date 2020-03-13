package com.onpositive.gifpresentation.core.impl;

import java.io.File;

import com.onpositive.gifpresentation.core.model.IImageSlideContent;

public class ImageSlideContent implements IImageSlideContent {

	private final File imageFile;

	public ImageSlideContent(File imageFile) {
		super();
		this.imageFile = imageFile;
	}

	@Override
	public File getImageFile() {
		return imageFile;
	}

}
