package com.onpositive.gifpresentation.core.impl;

import java.util.Arrays;
import java.util.List;

import com.onpositive.gifpresentation.core.model.IListSlideContent;

public class ListSlideContent implements IListSlideContent {
	
	private final List<String> slideContent;
	
	public ListSlideContent(List<String> slideContent) {
		super();
		this.slideContent = slideContent;
	}
	
	public ListSlideContent(String... items) {
		super();
		this.slideContent = Arrays.asList(items);		
	}

	public List<String> getListItems() {
		return slideContent;
	}

}
