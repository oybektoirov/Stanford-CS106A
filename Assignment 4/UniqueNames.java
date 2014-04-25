import acm.program.*;
import java.util.*;

public class UniqueNames extends ConsoleProgram {
	private ArrayList<String> strlist = new ArrayList<String>();
	
	private void printUnique() {
		println("Unique name list contains:");
		for (int i = 0; i < strlist.size(); i++) {
			println(strlist.get(i));
		}
	}
	
	public void run() {
		while (true) {
			String name = readLine("Enter name: ");
			
			if (name.equals("") && strlist.size() == 0) {
				println("Please enter a name.");
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
		}
		println();
		printUnique();
	}
}
