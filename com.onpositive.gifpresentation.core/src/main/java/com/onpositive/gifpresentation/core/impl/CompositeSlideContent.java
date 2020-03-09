package com.onpositive.gifpresentation.core.impl;

import java.util.Arrays;
import java.util.List;

import com.onpositive.gifpresentation.core.model.ICompositeSlideContent;
import com.onpositive.gifpresentation.core.model.ISlideContent;
import com.onpositive.gifpresentation.core.model.Orientation;

public class CompositeSlideContent implements ICompositeSlideContent {
	
	protected final List<ISlideContent> contents;
	protected final Orientation orientation;

	public CompositeSlideContent(Orientation orientation, List<ISlideContent> contents) {
		this.contents = contents;
		this.orientation = orientation;		
	}
	
	public CompositeSlideContent(Orientation orientation, ISlideContent... contents) {
		this.contents = Arrays.asList(contents);
		this.orientation = orientation;
	}

	public List<ISlideContent> getContents() {
		return contents;
	}

	public Orientation getOrientation() {
		return orientation;
	}

}
