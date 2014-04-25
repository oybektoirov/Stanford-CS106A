/*
 * File: CheckerboardKarel.java
 * ----------------------------
 * When you finish writing it, the CheckerboardKarel class should draw
 * a checkerboard using beepers, as described in Assignment 1.  You
 * should make sure that your program works for all of the sample
 * worlds supplied in the starter folder.
 */

import stanford.karel.*;

public class CheckerboardKarel extends SuperKarel {

	public void run() {
		
		//for 1x8 checkerboard
		if (frontIsClear()) {
			//do nothing
		} else {
			turnLeft();
		}
		
		//first layer
		while (frontIsClear()) {
			if (beepersPresent()) {
				move();
				if (frontIsClear()) {
					move();
				} else {
					//do nothing
				}
			} else {
				putBeeper();
				move();
				if (frontIsClear()) {
					move();
					if (beepersPresent()) {
						
					} else {
						putBeeper();
					}
				} else {
					//do nothing
				}
			}
		}
		
		//first turning point
		if (leftIsClear()) {
			turnLeft();
			move();
			turnLeft();
		}
		
		//second layer
		while (frontIsClear()) {
			if (beepersPresent()) {
				move();
				if (frontIsClear()) {
					move();
					putBeeper();
				} else {
					//do nothing
				}
			} else {
				turnLeft();
				move();
				if (beepersPresent()) {
					turnBack();
					move();
					turnLeft();
					move();
					putBeeper();
				} else {
					turnBack();
					move();
					putBeeper();
					turnLeft();
				}
			}
		}
		
		//second turning point
		if (rightIsClear()) {
			turnRight();
			move();
			turnRight();
		}	
		
		
	}
	
	public void turnBack() {
		for (int i = 0; i < 2; i++) {
			turnLeft();
		}
	}
	

}

// CODE FOR JUST WALKING
/* 
while (frontIsClear()) {
	move();
}
if (leftIsClear()) {
	turnLeft();
	move();
	turnLeft();
}
while (frontIsClear()) {
	move();
}
if (rightIsClear()) {
	turnRight();
	move();
	turnRight();
}	
*/
