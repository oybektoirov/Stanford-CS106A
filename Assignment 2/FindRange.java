/*
 * File: FindRange.java
 * Name:
 * Section Leader:
 * --------------------
 * This file is the starter file for the FindRange problem.
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {
	
	private static final int sentinel = 0;
	
	public void run() {
		displayWelcomeMessage();
		findRange();
	}
	private void displayWelcomeMessage() {
		println("This program finds the largest and smallest numbers");
	}
	private void findRange() {
		int firstNumber = readInt("? "); //asks user for the first number
		if (firstNumber == sentinel) {
			println("This is not a valid number. Please try again.");
		} //if sentinel is entered, above message shows up
		int smallestNumber = firstNumber; //the smallest number is currently first number
		int largestNumber = firstNumber; //the largest number is also fist number
		
		/*Pre-condition: the first number does not equal to the sentinal.
		 * compares each new number the user enters to the existing smallest and largest numbers, 
		 * and stores them as the smallest or largest if they are smallest or largest
		 */
		
		while (firstNumber != sentinel) {
			int secondNumber = readInt("? ");
			if (secondNumber < smallestNumber) {
				if (secondNumber != sentinel) {
					smallestNumber = secondNumber;
				}
			}
			if (secondNumber > largestNumber) {
				if (secondNumber != sentinel) {
					largestNumber = secondNumber;
				}
			}
			if (secondNumber == sentinel) {
				println("Smallest: " + smallestNumber);
				println("Largest: " + largestNumber);
				firstNumber = sentinel;
			}
		}
	}
	
}

