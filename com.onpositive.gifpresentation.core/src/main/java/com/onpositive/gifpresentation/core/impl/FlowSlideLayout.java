package com.onpositive.gifpresentation.core.impl;

import com.onpositive.gifpresentation.core.model.Orientation;

public class FlowSlideLayout extends AbstractSlideLayout {

	protected static final int DEFAULT_SPACING = 10;

	private Orientation orientation = Orientation.VERTICAL;
	private int spacing = DEFAULT_SPACING;

	public FlowSlideLayout() {
		super();
	}

	public FlowSlideLayout(Orientation orientation, int margin) {
		super(margin);
		this.orientation = orientation;
	}

	public FlowSlideLayout(Orientation orientation) {
		this(orientation, DEFAULT_MARGIN);
	}

	public Orientation getOrientation() {
		return orientation;
	}

	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}

	public int getSpacing() {
		return spacing;
	}

	public void setSpacing(int spacing) {
		this.spacing = spacing;
	}

	public static Builder newBuilder() {
		return new FlowSlideLayout().new Builder();
	}

	public class Builder {

		private Builder() {

		}

		public Builder withMargins(int leftMargin, int topMargin, int rightMargin, int bottomMargin) {
			FlowSlideLayout.this.setTopMargin(topMargin);
			FlowSlideLayout.this.setBottomMargin(bottomMargin);
			FlowSlideLayout.this.setLeftMargin(leftMargin);
			FlowSlideLayout.this.setRightMargin(rightMargin);
			return this;
		}

		public Builder withMargins(int horizontalMargin, int verticalMargin) {
			FlowSlideLayout.this.setTopMargin(verticalMargin);
			FlowSlideLayout.this.setBottomMargin(verticalMargin);
			FlowSlideLayout.this.setLeftMargin(horizontalMargin);
			FlowSlideLayout.this.setRightMargin(horizontalMargin);
			return this;
		}

		public Builder withMargin(int margin) {
			FlowSlideLayout.this.setTopMargin(margin);
			FlowSlideLayout.this.setBottomMargin(margin);
			FlowSlideLayout.this.setLeftMargin(margin);
			FlowSlideLayout.this.setRightMargin(margin);
			return this;
		}
		
		public Builder withOrientation(Orientation orientation) {
			FlowSlideLayout.this.orientation = orientation;
			return this;
		}
		
		public Builder withSpacing(int spacing) {
			FlowSlideLayout.this.spacing = spacing;
			return this;
		}
		
		public FlowSlideLayout build() {
			return FlowSlideLayout.this;
		}

	}

}
