[![Build Status](https://travis-ci.com/32kda/gif_presentation.svg?branch=development)](https://travis-ci.com/32kda/gif_presentation)

# gif_presentation
Java library for generating animated GIF for displaying a simple presentation. 
Basic renderening implementation is based on simple HTML rendering using _JTextPane_, which has small CSS subset support.

## Sample code

### Simple presentation with title, text and list
```
ISlide titleSlide = BasicSlide.newBuilder().withTitle("Lorem Ipsum").build();
		ISlide textSlide = BasicSlide.newBuilder().withTitle("de Finibus Bonorum et Malorum").withText(
				"Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt.")
				.build();
		List<String> listItems = new ArrayList<>();
		for (int i = 0; i < 7; i++) {
			listItems.add("Item " + i);
		}
		ISlide listSlide = BasicSlide.newBuilder().withTitle("Item list").withList(listItems).build();
		IPresentation presentation = new BasicPresentation(titleSlide, textSlide, listSlide);
		File cssFile = new File(getClass().getClassLoader().getResource("sample.css").getFile());
		GifPresentationRenderer presentationRenderer = new GifPresentationRenderer(
				new SwingSlideRenderer(new SwingComponentCreator(cssFile)));
		File outputFile = new File("sample.gif");
		presentationRenderer.renderPresentation(presentation, 1024, 768, 2000, outputFile);
```

### Custom component demo - create presentation with JFreeChart-based chart

```
public void chartTest() {
		ISlide titleSlide = BasicSlide.newBuilder().withTitle("Lorem Ipsum").build();
		ISlide chartSlide = BasicSlide.newBuilder().withTitle("de Finibus Bonorum et Malorum")
				.withContent(new ComponentContent(new ChartPanel(createChart()))).build();
		IPresentation presentation = new BasicPresentation(titleSlide, chartSlide);
		File cssFile = new File(getClass().getClassLoader().getResource("sample.css").getFile());
		GifPresentationRenderer presentationRenderer = new GifPresentationRenderer(
				new SwingSlideRenderer(new SwingComponentCreator(cssFile)));
		File outputFile = new File("chart.gif");
		presentationRenderer.renderPresentation(presentation, 800, 600, 2000, outputFile);
	}

	private JFreeChart createChart() {
   ...  // Create chart here
  }   
```
![Resulting chart presentation](https://user-images.githubusercontent.com/820526/76825803-73a22c80-684d-11ea-98ab-aacffc49a484.gif)
