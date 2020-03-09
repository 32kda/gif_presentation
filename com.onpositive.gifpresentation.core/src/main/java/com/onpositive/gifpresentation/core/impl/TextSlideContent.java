package com.onpositive.gifpresentation.core.impl;

import com.onpositive.gifpresentation.core.model.ISlideContent;
import com.onpositive.gifpresentation.core.model.ITextSlideContent;

public class TextSlideContent implements ISlideContent, ITextSlideContent {
	
	private final String content;

	public TextSlideContent(String content) {
		super();
		this.content = content;
	}

	public String getText() {
		return content;
	}

}
