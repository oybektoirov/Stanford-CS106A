/*
 * File: Hailstone.java
 * Name:
 * Section Leader:
 * --------------------
 * This file is the starter file for the Hailstone problem.
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram {

	public void run() {
		
		int number = readInt("Enter a positive number: ");
		int steps = 0;

		
		while (number != 1) {
			if (number == 0) {
				println("Invalid number. Please try again.");
				break;
			}
			if (number % 2 == 0) {
				println(number + " is even, so I take half: " + number/2);
				number = (number / 2);
				steps++;
			} else {
				println(number + " is odd, so I make 3n + 1: " + ((3 * number) + 1));
				number = ((3 * number) + 1);
				steps++;
			}
		}
		println("The process took " + steps + " steps to reach 1");
	}
}

