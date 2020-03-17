package task_reg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//10 darbinieki kopā
public class Employee {
	String name;
	//Map laikam jāveido zemāk metodē, jāsaliek arraylist pa katru mēnesi un tad tos jāapvieno vienā map
	Map<String, Integer> taskCountGotEachMonth=new HashMap<String, Integer>(); //katram mēnesism saliek saņemto darbu skaitu
	Map<String, String> timeWorkedEachMonth=new HashMap<String, String>();; //katram mēnesism saliek darba laiku summu par mēnesi
	Map<String, Integer> taskCountAssignedToOthersEachMonth=new HashMap<String, Integer>();; //katrā mēnesī saliek darbu skaitu kurus uzdevis citiem 
	Tasks tasks; //katra mēneša darba profils konkrētajai personai
	
	public Employee(String name) {
		this.name = name;
		this.tasks = new Tasks(this);
	}	
	
	//nosaka saņemto uzdevumu skaitu atbilstoši katram mēnesim
	public void getTaskCountPerMonth(ArrayList<String> monthTaskList, String month){
		int taskCounter = 0;
		for (String row:monthTaskList) {
			if(row.length()>0){
				if(this.name.equals(row.split(Main.delimiter)[1])) taskCounter++;
			}
		}
		this.taskCountGotEachMonth.put(month,taskCounter);
	}
	
	//nosaka citiem deleģēto uzdevumu skaitu atbilstoši katram mēnesim
	public void getAssignedTaskCountPerMonth(ArrayList<String> monthTaskList, String month){
		int taskCounter = 0;
		for (String row:monthTaskList) {
			if(row.length()>0){
				if(this.name.equals(row.split(Main.delimiter)[0])) taskCounter++;
			}
		}
		this.taskCountAssignedToOthersEachMonth.put(month,taskCounter);
	}
	
	//saskaita visu saņemto darbu daudzumu pa gadu
	public int getTotalTaskCount() {
		int taskCount = 0;
		for (String m : Main.months) {
			taskCount+=taskCountGotEachMonth.get(m);
		}
		return taskCount;
	}
	
	//saskaita cietiem iedoto darbu skaitu pa gadu
	public int getTotalAssignedTaskCount() {
		int taskCount = 0;
		for (String m : Main.months) {
			taskCount+=taskCountAssignedToOthersEachMonth.get(m);
		}
		return taskCount;
	}
	
	//saliek cik stundas:minūtes:sekundes ir nostrādātas konkrētajā mēnesī
	public void getTimeWorkedPerMonth(ArrayList<String> monthTaskList, String month){
		int hourCounter = 0;
		int minCounter = 0;
		int secCounter = 0;
		String timeWorked = "";
		for (String row:monthTaskList) {
				if(row.length()>0&&this.name.equals(row.split(Main.delimiter)[1])) {
					String[] timeArr = row.split(Main.delimiter)[3].split(":");
					int[] timeArrInInt = new int[2];
					timeArrInInt[0]=Integer.parseInt(timeArr[0]);
					timeArrInInt[1]=Integer.parseInt(timeArr[1]);
					minCounter+=timeArrInInt[0];
					if(secCounter+timeArrInInt[1]>=60) {
						minCounter+=timeArrInInt[1];
						secCounter+=timeArrInInt[1]-60;
						continue;
					}
					secCounter+=timeArrInInt[1];
			}
		}
		hourCounter = minCounter/60;
		minCounter = minCounter%60;
		timeWorked += hourCounter+":"+minCounter+":"+secCounter;
		System.out.println(this.name+" worked in "+month +" "+hourCounter+"h "+minCounter+"min "+secCounter+"s.");
		this.timeWorkedEachMonth.put(month,timeWorked);
	}
	

}
