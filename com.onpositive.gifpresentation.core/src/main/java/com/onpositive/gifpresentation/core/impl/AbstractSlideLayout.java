package com.onpositive.gifpresentation.core.impl;

import com.onpositive.gifpresentation.core.model.ISlideLayout;

public abstract class AbstractSlideLayout implements ISlideLayout {
	
	protected static final int DEFAULT_MARGIN = 15;
	
	private int leftMargin;
	private int topMargin;
	private int rightMargin;
	private int bottomMargin;
	
	public AbstractSlideLayout() {
		this(DEFAULT_MARGIN);
	}

	public AbstractSlideLayout(int margin) {
		this(margin,margin,margin,margin);
	}
	
	public AbstractSlideLayout(int horizontalMargin, int verticalMargin) {
		this(horizontalMargin,verticalMargin,horizontalMargin,verticalMargin);
	}
	
	public AbstractSlideLayout(int leftMargin, int topMargin, int rightMargin, int bottomMargin) {
		this.leftMargin = leftMargin;
		this.topMargin = topMargin;
		this.rightMargin = rightMargin;
		this.bottomMargin = bottomMargin;
	}

	public int getLeftMargin() {
		return leftMargin;
	}

	public void setLeftMargin(int leftMargin) {
		this.leftMargin = leftMargin;
	}

	public int getTopMargin() {
		return topMargin;
	}

	public void setTopMargin(int topMargin) {
		this.topMargin = topMargin;
	}

	public int getRightMargin() {
		return rightMargin;
	}

	public void setRightMargin(int rightMargin) {
		this.rightMargin = rightMargin;
	}

	public int getBottomMargin() {
		return bottomMargin;
	}

	public void setBottomMargin(int bottomMargin) {
		this.bottomMargin = bottomMargin;
	}

	
}
