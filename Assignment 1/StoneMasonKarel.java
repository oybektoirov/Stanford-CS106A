/*
 * File: StoneMasonKarel.java
 * --------------------------
 * The StoneMasonKarel subclass as it appears here does nothing.
 * When you finish writing it, it should solve the "repair the quad"
 * problem from Assignment 1.  In addition to editing the program,
 * you should be sure to edit this comment so that it no longer
 * indicates that the program does nothing.
 */

import stanford.karel.*;

public class StoneMasonKarel extends SuperKarel {

	public void run() {
		
		if (frontIsClear()) {
			turnLeft();
			while (frontIsClear()) {
				if (beepersPresent()) {
					//pickBeeper();
				} else {
					putBeeper();
				}
				move();
			}
		} else {
			turnLeft();
			while (frontIsClear()) {
				if (beepersPresent()) {
					//pickBeeper();
				} else {
					putBeeper();
				}
				move();
			}
		}

		if (frontIsClear()) {
			move();
		} else {
			turnBack();
			while (frontIsClear()) {
				if (beepersPresent()) {
					//pickBeeper();
				} else {
					putBeeper();
				}
				move();
			}
		}
		if (leftIsClear()) {
			turnLeft();
		} else {
			turnLeft();
		}
		if (frontIsClear()) {
			moveFourTimes();
		}
	}
				
	public void moveFourTimes() {
		for (int i = 0; i < 4; i++) {
			move();
		}
	}
	
	public void turnBack() {
		turnLeft();
		turnLeft();
	}
	
}

/*		if (frontIsClear()) {
			turnLeft();
			while (frontIsClear()) {
				move();
			}
		} 
		if (rightIsClear()) {
			turnBack();
			while (frontIsClear()) {
				move();
			}
		}
		if (leftIsClear()) {
			turnLeft();
			moveFourTimes();
		}
*/		
