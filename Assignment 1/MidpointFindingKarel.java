/*
 * File: MidpointFindingKarel.java
 * -------------------------------
 * When you finish writing it, the MidpointFindingKarel class should
 * leave a beeper on the corner closest to the center of 1st Street
 * (or either of the two central corners if 1st Street has an even
 * number of corners).  Karel can put down additional beepers as it
 * looks for the midpoint, but must pick them up again before it
 * stops.  The world may be of any size, but you are allowed to
 * assume that it is at least as tall as it is wide.
 */

import stanford.karel.*;

public class MidpointFindingKarel extends SuperKarel {

	public void run() {
		
		while (frontIsClear()) {
			move();
			putBeeper();
		}
		
		//
		if (frontIsBlocked()) {
			pickBeeper();
			turnAround();
			move();
		}
		while (beepersPresent()) {
			move();
		}
		if (frontIsBlocked()) {
			turnAround();
			move();
		}
		while (beepersPresent()) {
			pickBeeper();
		}
		
		//
		if (frontIsClear()) {
			move();
		}
		while (beepersPresent()) {
			move();
		}
		if (frontIsBlocked()) {
			turnAround();
			move();
		}
		if (beepersPresent()) {
			pickBeeper();
		}
		
		////
		while (frontIsClear()) {
			if (frontIsClear()) {
				move();
				while (beepersPresent()) {
					move();
				}
				if (noBeepersPresent()) {
					move();
					if (noBeepersPresent()) {
						turnAround();
						move();
						move();
						if (beepersPresent()) {
							move();
							if (beepersPresent()) {
								turnAround();
								move();
								pickBeeper();
								turnAround();
							} else {
								turnAround();
								move();
								turnLeft();
							}
						}
					}
				}
			}
		}

		
 	}
}

/*
if (frontIsClear()) {
	move();
}
while (beepersPresent()) {
	move();
}
if (frontIsClear()) {
	turnAround();
	move();
}
if (beepersPresent()) {
	pickBeeper();
}
*/