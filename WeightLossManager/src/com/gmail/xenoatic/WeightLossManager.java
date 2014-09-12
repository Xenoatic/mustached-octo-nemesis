package com.gmail.xenoatic;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class WeightLossManager extends JFrame {

	private static final long serialVersionUID = -8875629257421445617L;

	private JPanel contentPane;
	private JTextField textField;
	private JFreeChart chart;
	private ChartPanel chartPanel;
	
	private String fileLocation = "C:/InputOutput.txt";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {}

	/**
	 * Create the frame.
	 */
	public WeightLossManager() {
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
		final XYDataset dataset = createDataset();
		chart = createChart(dataset);
		final ChartPanel chartPanel = new ChartPanel(chart);
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
				
		        System.out.print("Insert your Weight! \n");
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
				
				JOptionPane.showMessageDialog(null, "You pressed the button!"); //TODO delete
			}
		});
		
		

		
	}
	
	/**For creating the testdataset
	 * @return
	 */
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
	
	private JFreeChart createChart(final XYDataset dataset) {
		
		//creating the chart
		final JFreeChart chart = ChartFactory.createXYLineChart(
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

        plot.setRenderer(renderer);
        
        // changing the auto tick unit selection to interger units only
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        //rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setAutoRangeIncludesZero(false);
        rangeAxis.setAutoRangeMinimumSize(150);
        
        //end of optional customization
	
		return chart;
	}
	
}// end program




























