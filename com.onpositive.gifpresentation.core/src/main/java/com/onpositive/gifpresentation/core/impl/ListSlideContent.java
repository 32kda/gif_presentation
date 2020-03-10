package com.onpositive.gifpresentation.core.impl;

import java.util.Arrays;
import java.util.List;

import com.onpositive.gifpresentation.core.model.IListSlideContent;

public class ListSlideContent implements IListSlideContent {
	
	private final List<String> slideContent;
	private final ListType listType;
	
	public ListSlideContent(ListType listType, List<String> slideContent) {
		super();
		this.listType = listType;
		this.slideContent = slideContent;
	}
	public ListSlideContent(List<String> slideContent) {
		this(ListType.BULLETED, slideContent);
	}
	
	public ListSlideContent(ListType listType,String... items) {
		this.listType = listType;
		this.slideContent = Arrays.asList(items);		
	}
	
	public ListSlideContent(String... items) {
		this.listType = ListType.BULLETED;
		this.slideContent = Arrays.asList(items);		
	}

	public List<String> getListItems() {
		return slideContent;
	}

	@Override
	public ListType getListType() {
		return listType;
	}

}
