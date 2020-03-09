package task_reg;

import java.util.ArrayList;
import java.util.Map;
//jāsaliek getters and setters
public class Employee {
	String name;
	Map<String, ArrayList<String>> tasksGotEachMonth; //katram mēnesism saliek izdarīto darbu skaitu
	Map<String, ArrayList<String>> timeWorkedEachMonth; //katram mēnesism saliek darbu laiku summu
	Map<String, ArrayList<String>> tasksAssignedEachMonth; //katrā mēnesī saliek darbus kurus uzdevis citiem 
	Map<String, Tasks> tasks; //katra mēneša darba profils konkrētajai personai
	
	public Employee() {
		
	}
	
	

}
