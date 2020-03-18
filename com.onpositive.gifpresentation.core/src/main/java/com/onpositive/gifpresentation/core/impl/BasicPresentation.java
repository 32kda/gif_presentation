package com.onpositive.gifpresentation.core.impl;

import java.util.ArrayList;
import java.util.List;

import java.util.Collections;

import com.onpositive.gifpresentation.core.model.IPresentation;
import com.onpositive.gifpresentation.core.model.ISlide;

public class BasicPresentation implements IPresentation {
	
	private List<ISlide> slides = new ArrayList<>();
 
	public BasicPresentation(List<ISlide> slides) {
		super();
		this.slides.addAll(slides);
	}
	
	public BasicPresentation(ISlide... slides) {
		super();
		for (ISlide slide : slides) {
			this.slides.add(slide);
		}
	}

	@Override
	public List<ISlide> getSlides() {
		return Collections.unmodifiableList(slides);
	}
	
	public void addSlide(ISlide slide) {
		this.slides.add(slide);
	}

}
