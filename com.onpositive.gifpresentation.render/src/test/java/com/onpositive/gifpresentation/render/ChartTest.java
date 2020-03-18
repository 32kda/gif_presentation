package com.onpositive.gifpresentation.render;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.statistics.HistogramDataset;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import com.madgag.gif.fmsware.GifDecoder;
import com.onpositive.gifpresentation.core.impl.BasicPresentation;
import com.onpositive.gifpresentation.core.impl.BasicSlide;
import com.onpositive.gifpresentation.core.model.IPresentation;
import com.onpositive.gifpresentation.core.model.ISlide;
import com.onpositive.gifpresentation.render.gif.GifPresentationRenderer;
import com.onpositive.gifpresentation.render.gif.SwingSlideRenderer;
import com.onpositive.gifpresentation.render.swing.ComponentContent;
import com.onpositive.gifpresentation.render.swing.SwingComponentCreator;

public class ChartTest {

	@Test
	public void chartTest(@TempDir Path tempDir) throws FileNotFoundException {
		ISlide titleSlide = BasicSlide.newBuilder().withTitle("Lorem Ipsum").build();
		ISlide chartSlide = BasicSlide.newBuilder().withTitle("de Finibus Bonorum et Malorum")
				.withContent(new ComponentContent(new ChartPanel(createChart()))).build();
		IPresentation presentation = new BasicPresentation(titleSlide, chartSlide);
		File cssFile = new File(getClass().getClassLoader().getResource("sample.css").getFile());
		GifPresentationRenderer presentationRenderer = new GifPresentationRenderer(
				new SwingSlideRenderer(new SwingComponentCreator(cssFile)));
		File outputFile = new File(tempDir.toFile(), "chart.gif");
		presentationRenderer.renderPresentation(presentation, 800, 600, 2000, outputFile);
		GifDecoder decoder = new GifDecoder();		
		assertEquals(0, decoder.read(new FileInputStream(outputFile)));
		assertEquals(2, decoder.getFrameCount());
		assertEquals(new Dimension(800,600), decoder.getFrameSize());
	}

	private JFreeChart createChart() {
		double[] vals = {

				0.71477137, 0.55749811, 0.50809619, 0.47027228, 0.25281568, 0.66633175, 0.50676332, 0.6007552,
				0.56892904, 0.49553407, 0.61093935, 0.65057417, 0.40095626, 0.45969447, 0.51087888, 0.52894806,
				0.49397198, 0.4267163, 0.54091298, 0.34545257, 0.58548892, 0.3137885, 0.63521146, 0.57541744,
				0.59862265, 0.66261386, 0.56744017, 0.42548488, 0.40841345, 0.47393027, 0.60882106, 0.45961208,
				0.43371424, 0.40876484, 0.64367337, 0.54092033, 0.34240811, 0.44048106, 0.48874236, 0.68300902,
				0.33563968, 0.58328107, 0.58054283, 0.64710522, 0.37801285, 0.36748982, 0.44386445, 0.47245989,
				0.297599, 0.50295541, 0.39785732, 0.51370486, 0.46650358, 0.5623638, 0.4446957, 0.52949791, 0.54611411,
				0.41020067, 0.61644868, 0.47493691, 0.50611458, 0.42518211, 0.45467712, 0.52438467, 0.724529,
				0.59749142, 0.45940223, 0.53099928, 0.65159718, 0.38038268, 0.51639554, 0.41847437, 0.46022878,
				0.57326103, 0.44913632, 0.61043611, 0.42694949, 0.43997814, 0.58787928, 0.36252603, 0.50937634,
				0.47444256, 0.57992527, 0.29381335, 0.50357977, 0.42469464, 0.53049697, 0.7163579, 0.39741694,
				0.41980533, 0.68091159, 0.69330702, 0.50518926, 0.55884098, 0.48618324, 0.48469854, 0.55342267,
				0.67159111, 0.62352006, 0.34773486 };

		HistogramDataset dataset = new HistogramDataset();
		dataset.addSeries("key", vals, 50);

		JFreeChart histogram = ChartFactory.createHistogram("Normal distribution", "y values", "x values", dataset);
		return histogram;
	}

}
