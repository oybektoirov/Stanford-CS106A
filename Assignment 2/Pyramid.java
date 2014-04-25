/*
 * File: Pyramid.java
 * Name:
 * Section Leader:
 * ------------------
 * This file is the starter file for the Pyramid problem.
 * It includes definitions of the constants that match the
 * sample run in the assignment, but you should make sure
 * that changing these values causes the generated display
 * to change accordingly.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Pyramid extends GraphicsProgram {

	/** Width of each brick in pixels */
	private static final int BRICK_WIDTH = 30;

	/** Height of each brick in pixels */
	private static final int BRICK_HEIGHT = 12;

	/** Number of bricks in the base of the pyramid */
	private static final int BRICKS_IN_BASE = 14;

	public void run() {
	     
        for (int h = 0; h < BRICKS_IN_BASE; h++)
        {
                for (int i = 0; i < BRICKS_IN_BASE - h; i++)
                {
                    int k = i * BRICK_WIDTH;
                    int m = h * BRICK_HEIGHT;
        int x = ((getWidth() - ((BRICKS_IN_BASE + (-h)) * BRICK_WIDTH)) / 2) + k;
        int y = getHeight() - ((BRICK_HEIGHT + 1) + m);
        GRect brick = new GRect (x, y, BRICK_WIDTH, BRICK_HEIGHT);
        add(brick);
            }
        }               
    }   
}