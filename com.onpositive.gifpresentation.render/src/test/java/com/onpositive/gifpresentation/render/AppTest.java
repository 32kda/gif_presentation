package com.onpositive.gifpresentation.render;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.onpositive.gifpresentation.core.impl.BasicPresentation;
import com.onpositive.gifpresentation.core.impl.BasicSlide;
import com.onpositive.gifpresentation.core.model.IPresentation;
import com.onpositive.gifpresentation.core.model.ISlide;
import com.onpositive.gifpresentation.render.gif.GifPresentationRenderer;
import com.onpositive.gifpresentation.render.gif.SwingSlideRenderer;
import com.onpositive.gifpresentation.render.swing.SwingComponentCreator;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for simple App.
 */
public class AppTest {

	/**
	 * Basic rendering test
	 */
	@Test
	public void basicTest() {
		ISlide titleSlide = BasicSlide.newBuilder().withTitle("Lorem Ipsum").build();
		ISlide textSlide = BasicSlide.newBuilder().withTitle("de Finibus Bonorum et Malorum").withText(
				"Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?")
				.build();
		ISlide textSlide1 = BasicSlide.newBuilder().withTitle("2.de Finibus Bonorum et Malorum").withText(
				"Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?")
				.build();
//		ISlide textSlide1 = BasicSlide.newBuilder().withTitle("de Finibus Bonorum et Malorum").withText(
//				"2.Preved Medved Moped?")
//				.build();
//		ISlide textSlide2 = BasicSlide.newBuilder().withTitle("de Finibus Bonorum et Malorum").withText(
//				"<html><body><p>3.Preved Medved Moped?</p></body></html>")
//				.build();
		List<String> listItems = new ArrayList<>();
		for (int i = 0; i < 7; i++) {
			listItems.add("Item " + i);
		}
		ISlide listSlide = BasicSlide.newBuilder().withTitle("Item list").withList(listItems).build();
		IPresentation presentation = new BasicPresentation(titleSlide, textSlide, textSlide1, listSlide);
		File cssFile = new File(getClass().getClassLoader().getResource("sample.css").getFile());
		GifPresentationRenderer presentationRenderer = new GifPresentationRenderer(
				new SwingSlideRenderer(new SwingComponentCreator(cssFile)));
		File outputFile = new File("sample.gif");
		presentationRenderer.renderPresentation(presentation, 1024, 768, 2000, outputFile);

		assertTrue(outputFile.exists());

	}
}
