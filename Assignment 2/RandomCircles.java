/* Name: RobotFace.java
 * This class contains Robot's face building.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

public class RandomCircles extends GraphicsProgram {
	/** Number of circles */
	private static final int NCIRCLES = 10;
	/** Minimum radius */
	private static final double MIN_RADIUS = 5;
	/** Maximum radius */
	private static final double MAX_RADIUS = 50;

	public void run() {
		for (int i = 0; i < NCIRCLES; i++) {
			double r = rgen.nextDouble(MIN_RADIUS, MAX_RADIUS);
			double x = rgen.nextDouble(0, getWidth() - 2 * r);
			double y = rgen.nextDouble(0, getHeight() - 2 * r);
			GOval circle = new GOval(x, y, 2 * r, 2 * r);
			circle.setFilled(true);
			circle.setFillColor(rgen.nextColor());
			add(circle);
		}
	}
	
	
	
	/* Private instance variable */
	private RandomGenerator rgen = RandomGenerator.getInstance();
	
}
