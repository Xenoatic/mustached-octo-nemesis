package com.gmail.xenoatic;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;

/** Main GUI part of the application
 *  Does have some actual code
 * @author Josh
 *
 */
public class WeightLossManager extends JFrame {

	private static final long serialVersionUID = -8875629257421445617L;

	private JPanel contentPane;
	private JTextField textField;
	private JFreeChart chart;
	
	private String fileLocation = "C:/InputOutput.txt"; //TODO needs to be user selectable

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public WeightLossManager() throws IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 483, 457);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Weight Textfield
		textField = new JTextField();
		textField.setBounds(83, 11, 180, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		//weight textfield label
		JLabel lblWeight = new JLabel("Weight");
		lblWeight.setBounds(27, 14, 46, 14);
		contentPane.add(lblWeight);
		
		//this creates a new Jpanel that will contain the Graph
		JPanel panel = new JPanel();
		panel.setLayout(new java.awt.BorderLayout());
		panel.setBounds(10, 40, 447, 368);
		contentPane.add(panel);
		
		
		//table/graph TODO THIS SUCKS
		Backend b = new Backend(this.fileLocation);
		XYDataset dataset = b.getDataset();
		chart = createChart(dataset);
		 ChartPanel chartPanel = new ChartPanel(chart);
		panel.add(chartPanel, java.awt.BorderLayout.CENTER);
		panel.validate();
		
		//button for entering weight
		JButton btnEnterWeight = new JButton("Enter Weight");
		btnEnterWeight.setBounds(273, 10, 184, 23);
		contentPane.add(btnEnterWeight);
		

		
		//making the btnEnterWeight function
		btnEnterWeight.addActionListener(new ActionListener() {
			/**TODO most of this can be deleted, this is what
			 * happens when you press the weight button
			 */
			public void actionPerformed(ActionEvent e) {
				
				//weight in the textbox
				double weight = 0;
		        //this is making sure a double was inserted
		        try{
		        	
		        	weight = Double.parseDouble(textField.getText());
		        }catch(NumberFormatException nfe){
		            System.out.println("You didn't enter a number!");
		     	}
				
				//create a new backend
				Backend backend = new Backend(fileLocation);
				try {
					backend.fileIO(new File(fileLocation), weight);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
		});
	}

	/**TODO this needs to go into backend
	 * @param dataset dataset for the chart
	 * @return
	 */
	private JFreeChart createChart(XYDataset dataset) {
		
		//creating the chart //TODO http://stackoverflow.com/questions/21503426/jfreechart-date-axis-formatting-issue
		JFreeChart chart = ChartFactory.createXYLineChart(
				"Weight over time",              //title of the chart
				"Date",                        // x axis label
				"Weight",                        // y axis label
				dataset,                    // data
				PlotOrientation.VERTICAL,   
				true,                       // include legend
				true,                       // tooltips
				false                       // urls
			);
		
		//Customization of the chart
		chart.setBackgroundPaint(Color.white); //background of box
		
		//get a reference to the plot for further customization...
        XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        
        //this connects the lines on the chart
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        //this changes the color of the plots
        renderer.setSeriesPaint(0, Color.BLACK);
        //this connects the lines
        renderer.setSeriesLinesVisible(0, true);

        plot.setRenderer(renderer);
        
        // changes the rangeAxis (y axis duh) to start at 150 instead of 0
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setAutoRangeIncludesZero(false);
        rangeAxis.setAutoRangeMinimumSize(150);
        
        // changes the DomainAxis (x axis duh) to display as simpledateformat
        DateAxis domainAxis = (DateAxis) plot.getDomainAxis();
        domainAxis.setDateFormatOverride(new SimpleDateFormat("HH:mm:ss"));
        //end of optional customization
	
		return chart;
	}
	
}// end program




























