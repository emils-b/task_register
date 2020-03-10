package task_reg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
//jāsaliek getters and setters
public class Employee {
	String name;
	//Map laikam jāveido zemāk metodē, jāsaliek arraylist pa katru mēnesi un tad tos jāapvieno vienā map
	Map<String, Integer> taskCountGotEachMonth; //katram mēnesism saliek saņemto darbu skaitu
	Map<String, ArrayList<String>> timeWorkedEachMonth; //katram mēnesism saliek darbu laiku summu par mēnesi
	Map<String, Integer> tasksAssignedEachMonthCount; //katrā mēnesī saliek darbu skaitu kurus uzdevis citiem 
	Map<String, Tasks> tasks; //katra mēneša darba profils konkrētajai personai
	
	public Employee(String name) {
		this.name=name;
	}
	
	//nedarbojas
	public void getAssignetTaskCountPerMonthForGivenEmployee(ArrayList<String> monthTaskList, String month){
		this.taskCountGotEachMonth.put(month,taskPerMonthForGivenEmployee(monthTaskList));
	}

	public int taskPerMonthForGivenEmployee(ArrayList<String> monthTaskList) {
		int taskCounter = 0;
		for (String row:monthTaskList) {
		if(this.name.equals(row.split(";")[1])) taskCounter++;
		}
		return taskCounter;
	}

}
