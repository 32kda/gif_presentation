package com.onpositive.gifpresentation.core.impl;

import java.util.Arrays;
import java.util.List;

import com.onpositive.gifpresentation.core.model.IContentSlide;
import com.onpositive.gifpresentation.core.model.ISlide;
import com.onpositive.gifpresentation.core.model.ISlideContent;
import com.onpositive.gifpresentation.core.model.ISlideLayout;
import com.onpositive.gifpresentation.core.model.ITitleSlide;

public class BasicSlide implements ITitleSlide, IContentSlide {
	
	private String title;
	private ISlideContent content;
	private ISlideLayout layout = new FlowSlideLayout();
	
	private BasicSlide() {
	}

	public BasicSlide(String title, ISlideContent content) {
		this.title = title;
		this.content = content;
	}
	
	public BasicSlide(String title, String content) {
		this.title = title;
		this.content = new TextSlideContent(content);
	}
	
	public BasicSlide(String title) {
		this(title, (ISlideContent) null);
	}

	public ISlideLayout getLayout() {
		return layout;
	}

	public ISlideContent getContent() {
		return content;
	}

	public String getTitle() {
		return title;
	}

	public static Builder newBuilder() {
		return new BasicSlide().new Builder();
	}
	
	public class Builder {
		
		private Builder() {
		}
		
		public Builder withTitle(String title) {
			BasicSlide.this.title = title;
			return this;
		}
		
		public Builder withText(String text) {
			BasicSlide.this.content = new TextSlideContent(text);
			return this;
		}
		
		public Builder withList(String... items) {
			BasicSlide.this.content = new ListSlideContent(Arrays.asList(items));
			return this;
		}
		
		public Builder withList(List<String> items) {
			BasicSlide.this.content = new ListSlideContent(items);
			return this;
		}
		
		public Builder withLayout(ISlideLayout layout) {
			BasicSlide.this.layout = layout;
			return this;
		}

		public ISlide build() {
			return BasicSlide.this;
		}		
		
	}

	@Override
	public String toString() {
		return "BasicSlide [title=" + title + ", content=" + content + ", layout=" + layout + "]";
	}

}
