import java.awt.Color;

import org.jfree.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
//importing everything



public class LineChart1 extends ApplicationFrame {
	
	/** this probly need to be in weightlossmanager
	 * Setup the chart
	 * @param title
	 */
	public LineChart1(final String title) {
		
		super(title);
		
		//is the set of data to be analized
		//create dataset is method for example data
		final XYDataset dataset = createDataset();
		//this creates the chart using the dataset
		final JFreeChart chart = createChart(dataset);
		
		//this stuff needs to be set in the layout, but
		//for now it's for demonstration purposes.
		final ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
		setContentPane(chartPanel);
		
		
	}
	//TODO we are probly going to have to create an new XYDataset that can
	//deal with dates
	private XYDataset createDataset() {
		
        final XYSeries series1 = new XYSeries("weight");
        series1.add(1, 282);
        series1.add(2, 280);
        series1.add(3, 270);
        series1.add(4, 275);
        series1.add(5.0, 270);
        series1.add(6.0, 250);
        series1.add(7.0, 240);
        series1.add(8.0, 260);
        
        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
		
		return dataset;
	}
	/** This is where the chart is created
	 * @param dataset
	 * @return
	 */
	private JFreeChart createChart(final XYDataset dataset) {
		
		//creating the chart
		final JFreeChart chart = ChartFactory.createXYLineChart(
				"Weight over time",              //title of the chart
				"Weight",                        // x axis label
				"Date",                        // y axis label
				dataset,                    // data
				PlotOrientation.VERTICAL,   
				true,                       // include legend
				true,                       // tooltips
				false                       // urls
			);
		
		//Customization of the chart
		chart.setBackgroundPaint(Color.white); //background of box
		
		//get a reference to the plot for further customization...
        final XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        
        //this connects the lines on the chart
        final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        //this changes the color of the plots
        renderer.setSeriesPaint(0, Color.BLACK);
        //this connects the lines
        renderer.setSeriesLinesVisible(0, true);
        renderer.setSeriesShapesVisible(1, false);

        plot.setRenderer(renderer);
        
        // changing the auto tick unit selection to interger units only
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        //end of optional customization
	
		return chart;
	}
	public static void main(final String[] args) {
		
        final LineChart1 demo = new LineChart1("Line Chart Demo 6");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
		
		
	}
	
}
















