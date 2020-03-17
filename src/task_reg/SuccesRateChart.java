package task_reg;

import java.util.ArrayList;
import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

public class SuccesRateChart extends ApplicationFrame{
	
	   public SuccesRateChart( String applicationTitle , String chartTitle ) {
		      super( applicationTitle );        
		      JFreeChart barChart = ChartFactory.createBarChart(
		         chartTitle,           
		         "Month",            
		         "Succes Rate (%)",            
		         createDataset(),          
		         PlotOrientation.VERTICAL,           
		         true, true, false);
		         
		      ChartPanel chartPanel = new ChartPanel( barChart );        
		      chartPanel.setPreferredSize(new java.awt.Dimension( 560 , 367 ) );        
		      setContentPane( chartPanel ); 
		   }
	   
	   private CategoryDataset createDataset( ) {
		   final DefaultCategoryDataset dataset = 
				      new DefaultCategoryDataset( );  
		   for(String m:Main.months) {
			   dataset.addValue( getSuccessRate(m) , m, " "); 
		   }
		   return dataset;
	   }
		   
		   public double getSuccessRate(String month) {
			   double successRate = 0;
			   double allTaskCount = 0;
			   double doneTaskCount = 0;
			   Map<String, ArrayList<String>> taskListPerDate = Main.monthList.get(month).taskListPerDate;
			   for(String d:Main.monthDateFilenames.get(month)) {
				   for (String r:taskListPerDate.get(d)) {
					   if(r.length()>0) {
						   allTaskCount++;
						   if(r.split(Main.delimiter)[2].equals("DONE")) {
							   doneTaskCount++;
							}
						}
				   }
			   }
			   successRate = Methods.calcucalteSuccessRate(doneTaskCount, allTaskCount);
			   return successRate;
		   }
		   
	   
}
