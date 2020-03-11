package com.onpositive.gifpresentation.render.swing;

import java.awt.Container;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.onpositive.gifpresentation.core.impl.FlowSlideLayout;
import com.onpositive.gifpresentation.core.model.ICompositeSlideContent;
import com.onpositive.gifpresentation.core.model.IContentSlide;
import com.onpositive.gifpresentation.core.model.IListSlideContent;
import com.onpositive.gifpresentation.core.model.IListSlideContent.ListType;
import com.onpositive.gifpresentation.core.model.ISlide;
import com.onpositive.gifpresentation.core.model.ISlideContent;
import com.onpositive.gifpresentation.core.model.ISlideLayout;
import com.onpositive.gifpresentation.core.model.ITextSlideContent;
import com.onpositive.gifpresentation.core.model.ITitleSlide;
import com.onpositive.gifpresentation.core.model.Orientation;

public class SwingComponentCreator implements IComponentCreator {
	
	protected Font titleFont = new Font("arial", Font.BOLD, 25);

	@Override
	public JComponent createComponent(ISlide slide) {
		JPanel container = new JPanel();
		setupLayout(container, slide);
		ISlideLayout slideLayout = slide.getLayout();
		int spacing = -1;
		if (slideLayout instanceof FlowSlideLayout) {
			spacing = ((FlowSlideLayout) slideLayout).getSpacing();
		}
		if (slide instanceof ITitleSlide) {
			String title = ((ITitleSlide) slide).getTitle();
			if (title != null) {
				JLabel label = new JLabel(title, JLabel.CENTER);
				label.setFont(titleFont);
				container.add(label);
			}
			
		}
		if (slide instanceof IContentSlide) {
			ISlideContent content = ((IContentSlide) slide).getContent();
			if (content != null) {
				if (container.getComponentCount() > 0 && spacing > 0) {
					container.add(Box.createVerticalStrut(spacing));
				}
				container.add(createComponent(content));
			}
			
		}
		return container;
	}

	protected JComponent createComponent(ISlideContent content) {
		if (content instanceof ICompositeSlideContent) {
			ICompositeSlideContent compositeContent = (ICompositeSlideContent) content;
			boolean vertical = ((ICompositeSlideContent) content).getOrientation() == Orientation.VERTICAL;
			JPanel container = new JPanel();
			container.setLayout(new BoxLayout(container, vertical ? BoxLayout.Y_AXIS : BoxLayout.X_AXIS));
			int count = compositeContent.getContents().size();
			for (int i = 0; i < count; i++) {
				container.add(createComponent(compositeContent.getContents().get(i)));
				if (i < count - 1) {
					container.add(vertical ? Box.createVerticalStrut(15) : Box.createHorizontalStrut(15)); //TODO make this configurable
				}
			}
			return container;
		}
		if (content instanceof ITextSlideContent) {
			ITextSlideContent textContent = (ITextSlideContent) content;
			JEditorPane editorPane = createHTMLViewer();    
			editorPane.setText( textContent.getText() );
			return editorPane;
		}
		if (content instanceof IListSlideContent) {
			IListSlideContent listContent = (IListSlideContent) content;
			boolean bulleted = listContent.getListType() == ListType.BULLETED;
			StringBuilder builder = new StringBuilder();
			builder.append(bulleted ? "<ul>" : "<ol>");
			builder.append("\n");
			for (String item : listContent.getListItems()) {
				builder.append("<li>");
				builder.append(item);
				builder.append("</li>");
				builder.append("\n");
			}
			builder.append(bulleted ? "</ul>" : "</ol>");
			JEditorPane editorPane = createHTMLViewer();    
			editorPane.setText( builder.toString() );
			return editorPane;
		}
		if (content instanceof ComponentContent) {
			return ((ComponentContent) content).getComponent();			
		}
		return null;
	}

	protected JEditorPane createHTMLViewer() {
		JEditorPane editorPane = new JEditorPane();
		editorPane.setEditable(false);
		editorPane.setContentType( "text/html" );
		return editorPane;
	}

	protected void setupLayout(Container container, ISlide slide) {
		ISlideLayout slideLayout = slide.getLayout();
		int axis = BoxLayout.Y_AXIS;
		if (slideLayout instanceof FlowSlideLayout) {
			axis = ((FlowSlideLayout) slideLayout).getOrientation() == Orientation.VERTICAL ? BoxLayout.Y_AXIS : BoxLayout.X_AXIS;
		}
		container.setLayout(new BoxLayout(container, axis));
		if (slideLayout != null) {
			container.getInsets().set(slideLayout.getTopMargin(), slideLayout.getLeftMargin(), slideLayout.getBottomMargin(), slideLayout.getRightMargin());
		}
	}
	

}
