/*
 * File: AddCommas.java
 * Name: Oybek Toirov
 * Section Leader: Mehran Sahami
 * --------------------
 * This file is the starter file for the FindRange problem.
 */

import acm.program.*;

public class AddCommas extends ConsoleProgram {
	private String addCommasToNumericString(String digits) {
		String result = "";
		int len = digits.length();
		int nDigits = 0;
		for (int i = len - 1; i >= 0; i--) {
			result = digits.charAt(i) + result;
			nDigits++;
			if (((nDigits % 3) == 0) && (i > 0)) {
				result = "," + result;
			}
		}
		return result;
	}
	
	public void run() {
		while (true) {
			String digits = readLine("Enter a numeric string: ");
			if (digits.length() == 0) break;
			println(addCommasToNumericString(digits));
		}
	}
}
