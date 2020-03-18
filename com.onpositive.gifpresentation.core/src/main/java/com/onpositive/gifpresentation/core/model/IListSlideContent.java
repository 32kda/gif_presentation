package com.onpositive.gifpresentation.core.model;

import java.util.List;

public interface IListSlideContent extends ISlideContent{
	
	enum ListType {
		BULLETED, NUMBERED
	}
	
	public List<String> getListItems();
	
	public ListType getListType();

}
