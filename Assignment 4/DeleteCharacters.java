/*
 * File: DeleteCharacters.java
 * Name: Oybek Toirov
 * Section Leader: Mehran Sahami
 * --------------------
 * This file is the starter file for the FindRange problem.
 */

import acm.program.*;

public class DeleteCharacters extends ConsoleProgram {

	private String removeAllOccurrences(String str, char ch) {
		String result = "";
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) != ch) {
				result += str.charAt(i);
			}
		}
		return result;
	}
	
	public void run() {
		while (true) {
			String str = readLine("Enter a string: ");
			char ch = 'a';
			if (str.length() == 0) break;
			println(removeAllOccurrences(str, ch));
		}
	}
	
	/** String Scanner(unfinished)**/
	/*
	int number = 0;
	private char isOccurence() {
		for (int i = 0; i < str.length(); i++) {
			for (int j = 0; j < str.length(); j++) {
				if (str.charAt(i) == str.charAt(j)) {
					number++;
				}
				if (number > highestnumber) {
					highestnumber = number;
					occurence = str.charAt(i);
				}
			}
		}
		return occurence;
	}
	*/
}
