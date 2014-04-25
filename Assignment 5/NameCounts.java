import acm.program.*;

import java.util.*;

public class NameCounts extends ConsoleProgram {

	public void run() {
		while (true) {
			String name = readLine("Enter name: ");
			
			if (name.equals("") && strlist.size() == 0) {
				println("Please enter someting.");
			}
			
			if (name.equals("") && strlist.size() > 0) break;
			if (!name.equals("") && strlist.size() > 0) {
				int count = 0;
				
				for (int i = 0; i < strlist.size(); i++) {
					String lastname = strlist.get(i);
					if (name.equals(lastname)) {
						count++;
					}
				}
				
				if (count < 1) {
					strlist.add(name);
				}
				
			}
			
			if (!name.equals("") && strlist.size() == 0) {
				strlist.add(name);
			}
			
			//checks whether the entry name contains in the map; if contains then it replaces old one with new value.
			//if the name does not contain then it's simply creates a new key with value 1
			if (map.containsKey(name)) {
				int newvalue = map.get(name) + 1;
				map.put(name, newvalue);
			} else {
				map.put(name, 1);
			}
			
		}
		
		println();
		printNameCount();
		
	}
	
	private void printNameCount() {
		println("Unique name list contains:");
		
		for (int i = 0; i < strlist.size(); i++) {
			println("Entry " + "[" + strlist.get(i) + "] " 
						+ "has count " + map.get(strlist.get(i)));
		}
	}
	
	private ArrayList<String> strlist = new ArrayList<String>();
	private HashMap<String, Integer> map = new HashMap<String, Integer>(); 
}