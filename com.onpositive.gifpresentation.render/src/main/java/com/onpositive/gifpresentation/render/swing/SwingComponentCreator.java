package com.onpositive.gifpresentation.render.swing;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;

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
	protected Color foregrund = null;
	protected Color background = null;
	
	private StyleSheet styleSheet;

	public SwingComponentCreator(File styleSheetFile) {
		try (Reader reader = new BufferedReader(new FileReader(styleSheetFile))) {
			init(reader);
		} catch (IOException e) {
			e.printStackTrace(); // TODO log this
		}
	}
	
	public SwingComponentCreator(Reader reader) {
		try {
			init(reader);
		} catch (IOException e) {
			e.printStackTrace(); // TODO log this
		}
	}
	
	public SwingComponentCreator(String stylesString) {
		this(new StringReader(stylesString));
	}

	protected void init(Reader reader) throws IOException {
		styleSheet = new StyleSheet();
		styleSheet.loadRules(reader, null);
		Object bgColorVal = tryGetStyleAttr(styleSheet, StyleConstants.Background, "body","html", "p");
		Object fgColorVal = tryGetStyleAttr(styleSheet, StyleConstants.Foreground, "body","html", "p");
		if (bgColorVal instanceof Color) {
			background = (Color) bgColorVal;				
		}
		if (fgColorVal instanceof Color) {
			foregrund = (Color) fgColorVal;				
		}		
	}

	public SwingComponentCreator() {
	}

	@Override
	public JComponent createComponent(ISlide slide) {
		JPanel container = new JPanel();
		styleComponent(container);
		setupLayout(container, slide);
		ISlideLayout slideLayout = slide.getLayout();
		int spacing = -1;
		if (slideLayout instanceof FlowSlideLayout) {
			spacing = ((FlowSlideLayout) slideLayout).getSpacing();
		}
		boolean hasContent = (slide instanceof IContentSlide) && ((IContentSlide) slide).getContent() != null; 
		if (slide instanceof ITitleSlide) {
			String title = ((ITitleSlide) slide).getTitle();
			if (title != null) {
				JLabel label = new JLabel(title, JLabel.CENTER);
				label.setAlignmentX(0.5f);
				label.setFont(titleFont);
				styleComponent(label);
				container.add(label);
			}
			if (!hasContent) {
				container.setLayout(new GridBagLayout());
			}			
		}
		if (hasContent) {
			ISlideContent content = ((IContentSlide) slide).getContent();
			if (container.getComponentCount() > 0 && spacing > 0) {
				container.add(Box.createVerticalStrut(spacing));
			}
			container.add(createComponent(content));

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
					container.add(vertical ? Box.createVerticalStrut(15) : Box.createHorizontalStrut(15)); // TODO make
				}
			}
			return container;
		}
		if (content instanceof ITextSlideContent) {
			ITextSlideContent textContent = (ITextSlideContent) content;
			JEditorPane editorPane = createHTMLViewer();
			editorPane.setText(textContent.getText());
			styleComponent(editorPane);
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
			editorPane.setText(builder.toString());
			styleComponent(editorPane);
			return editorPane;
		}
		if (content instanceof ComponentContent) {
			JComponent component = ((ComponentContent) content).getComponent();
			styleComponent(component);
			return component;
		}
		return null;
	}

	protected JEditorPane createHTMLViewer() {
		JEditorPane editorPane = new JEditorPane();
		editorPane.setEditable(false);
		editorPane.setContentType("text/html");
		if (styleSheet != null) {
			HTMLEditorKit htmlEditorKit = (HTMLEditorKit) editorPane.getEditorKit();
			htmlEditorKit.setStyleSheet(styleSheet);
		}
		return editorPane;
	}

	protected void setupLayout(JPanel container, ISlide slide) {
		ISlideLayout slideLayout = slide.getLayout();
		int axis = BoxLayout.Y_AXIS;
		if (slideLayout instanceof FlowSlideLayout) {
			axis = ((FlowSlideLayout) slideLayout).getOrientation() == Orientation.VERTICAL ? BoxLayout.Y_AXIS
					: BoxLayout.X_AXIS;
		}
		container.setLayout(new BoxLayout(container, axis));
		if (slideLayout != null) {
			container.setBorder(BorderFactory.createEmptyBorder(slideLayout.getTopMargin(), slideLayout.getLeftMargin(),
					slideLayout.getBottomMargin(), slideLayout.getRightMargin()));
		}
	}
	
	protected Object tryGetStyleAttr(StyleSheet styleSheet, Object key, String... selectors) {
		for (String selector : selectors) {			
			Style rule = styleSheet.getRule(selector);
			if (rule != null) {
				Object attribute = rule.getAttribute(key);
				if (attribute != null) {
					return attribute;
				}
			}
		}
		return null;
	}
	
	protected void styleComponent(Component component) {
		if (background != null) {
			component.setBackground(background);
		}
		if (foregrund != null) {
			component.setForeground(foregrund);
		}
	}

}
