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
		      final String january = "January";        
		      final String february = "February";        
		      final String march = "March";        
		      final String april = "April";        
		      final String may = "May";        
		      final String june = "June";        
		      final String july = "July";       
		      final String august = "August";        
		      final String september = "September";        
		      final String october = "October";        
		      final String november = "November";        
		      final String december = "December";
		      final DefaultCategoryDataset dataset = 
		      new DefaultCategoryDataset( );  

		      dataset.addValue( getSuccessRate(january) , january, " ");        
		      dataset.addValue( getSuccessRate(february) , february, " ");        
		      dataset.addValue( getSuccessRate(march) , march, " "); 
		      dataset.addValue( getSuccessRate(april) , april, " ");           
		      dataset.addValue( getSuccessRate(april) , may, " ");        
		      dataset.addValue( getSuccessRate(june) , june, " ");       
		      dataset.addValue( getSuccessRate(july) , july, " ");        
		      dataset.addValue( getSuccessRate(august) , august, " ");
		      dataset.addValue( getSuccessRate(september) , september, " ");        
		      dataset.addValue( getSuccessRate(october) , october, " ");        
		      dataset.addValue( getSuccessRate(november) , november, " ");        
		      dataset.addValue( getSuccessRate(december) , december, " ");               

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
						   if(r.split(";")[2].equals("DONE")) {
							   doneTaskCount++;
							}
						}
				   }
			   }
			   successRate = Methods.calcucalteSuccessRate(doneTaskCount, allTaskCount);
			   return successRate;
		   }
		   
	   
}
