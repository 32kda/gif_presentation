package com.onpositive.gifpresentation.core.model;

import java.util.List;

public interface ICompositeSlideContent extends ISlideContent {
	
	public List<ISlideContent> getContents();
	
	public Orientation getOrientation();

}
