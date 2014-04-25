/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

/** Dimensions of game board
 *  Should not be used directly (use getWidth()/getHeight() instead).
 *  * */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

/** Separation between bricks */
	private static final int BRICK_SEP = 4;

/** Width of a brick */
	private static final int BRICK_WIDTH = 
		(WIDTH - (NBRICKS_PER_ROW * BRICK_SEP)) / NBRICKS_PER_ROW;

/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;
	
/** Diameter of the ball in pixels */
	private static final int BALL_DIAM = BALL_RADIUS * 2;

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;
	
	private static int bricksLeft = NBRICKS_PER_ROW * NBRICK_ROWS;
	private int gold;
	private double yVel = +8.0;
	
	private static final int DELAY = 20;
	
/** GRect object called "paddle" */
	private GRect paddle, bricks;
	
/** GOval object called "ball" */
	private GOval ball;

/** X and Y coordinates for paddle */
	private int x, y;
	
/** X and Y coordinates for ball */
	private int xBall, yBall;	

/** Velocity of the ball */	
	private double vx, vy;
	
/** Random numbers generator */		
	private RandomGenerator rgen = RandomGenerator.getInstance();
	
	AudioClip bounceClip = MediaTools.loadAudioClip("bounce.au");

/** Runs the game. */
	public void run() {
		setup();
		play();
		
	}
	// 1. Makes the paddle follow mouse location.
	// 2. The if statement makes sure that the paddle never 
	//    crosses application borders. 
	public void mouseMoved(MouseEvent e) {
		if (!gameOver()) {
			if (e.getX() < PADDLE_WIDTH/2) {
				paddle.setLocation(0, y);
			} else if (e.getX() > getWidth() - PADDLE_WIDTH/2) {
				paddle.setLocation(getWidth() - PADDLE_WIDTH, y);
			} else {
				paddle.setLocation(e.getX() - PADDLE_WIDTH/2, y);
			}
		}
	}
	
	private void setup() {
		buildBricks();
		buildPaddle();
		buildBall();
		buildCounter();
		startTimer();
		addMouseListeners();
	}
	
	private void play() {
		startBall();
		while (!gameOver()) {
			bounceBall();
			speedUp();
			checkForCollisions();
			pause(DELAY);
		}
		if (bricksLeft == 0) {
			printGameWin();
		} else {
			printGameOver();
		}
	}
	
	//builds bricks with colors
	private void buildBricks() {
		for (int i = 0; i < NBRICK_ROWS; i++) {
			for (int j = 0; j < NBRICKS_PER_ROW; j++) {
				bricks = new GRect((BRICK_WIDTH * j) + (BRICK_SEP * j) + BRICK_SEP,
						(BRICK_HEIGHT * i) + (BRICK_SEP * i) + BRICK_Y_OFFSET,
						BRICK_WIDTH, BRICK_HEIGHT);
				bricks.setFilled(true);
				if (i == 2 || i == 3) {
					bricks.setColor(Color.ORANGE);
				} else if (i == 4 || i == 5) {
					bricks.setColor(Color.YELLOW);
				} else if (i == 6 || i == 7) {
					bricks.setColor(Color.GREEN);
				} else if (i == 8 || i == 9) {
					bricks.setColor(Color.CYAN.darker());
				} else {
					bricks.setColor(Color.RED);
				}
				add(bricks);
			}
		}
	}
	
	//builds the paddle
	private void buildPaddle() {	
		x = getWidth()/2 - PADDLE_WIDTH/2;
		y = getHeight() - PADDLE_Y_OFFSET;
		paddle = new GRect(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setFilled(true);
		paddle.setColor(Color.BLACK);
		add(paddle);
	}
	
	//builds the ball
	private void buildBall() {
		xBall = getWidth()/2 - BALL_RADIUS;
		yBall = getHeight()/2 - BALL_RADIUS;
		ball = new GOval(xBall, yBall, BALL_DIAM, BALL_DIAM);
		ball.setFilled(true);
		ball.setColor(Color.BLACK);
		add(ball);
	}
	
	//builds the brick and gold counters
	private void buildCounter() {
		brickcounter = new GLabel("Bricks left: " + bricksLeft, 10, getHeight() - 6);
		brickcounter.setFont("Helvetica-bold-12");
		brickcounter.setColor(Color.GRAY);
		add(brickcounter);
		
		goldcounter = new GLabel("Golds: " + gold);
		goldcounter.setFont("Helvetica-bold-12");
		goldcounter.setColor(Color.ORANGE);
		add(goldcounter, getWidth() - goldcounter.getWidth() - 20, getHeight() - 6);
	}
	
	//the animation of timer Ready... Go!
	private void startTimer() {
		ready = new GLabel("Ready...");
		ready.setFont("Helvetica-bold-22");
		ready.setColor(Color.RED);
		ready.setLocation(getWidth()/2 - ready.getWidth()/2,
				getHeight()/2 - ready.getAscent()/2 - 10);
		add(ready);
		
		pause(2000);
		remove(ready);
		
		go = new GLabel("Go!");
		go.setFont("Helvetica-bold-22");
		go.setColor(Color.RED);
		go.setLocation(getWidth()/2 - go.getWidth()/2,
				getHeight()/2 - go.getAscent()/2 - 10);
		add(go);
		
		pause(2000);
		remove(go);
	}
	
	//first starting direction of the ball
	private void startBall() {
		vy = yVel;
		
		//random X velocity generator
		vx = rgen.nextDouble(1.0, 4.0);
		if (rgen.nextBoolean(0.5)) vx = -vx;
		
		ball.move(vx, vy);
	}
	
	//makes the ball bounce around within the application borders
	private void bounceBall() {
		if (ball.getX() + BALL_DIAM >= getWidth()) {
			vx = -vx;
			if (ball.getX() < getWidth()) {
				ball.move(vx, vy);
			}
		} else if (ball.getX() <= 0) {
			vx = -vx;
			if (ball.getX() + BALL_DIAM < getWidth()) {
				ball.move(vx, vy);
			}
		} else if (ball.getY() <= 0) {
			vy = yVel;
			if (ball.getY() + BALL_DIAM < getHeight()) {
				ball.move(vx, vy);
			}
		} else {
			ball.move(vx, vy);
		}
	}

	
	//checks whether the ball collides with objects
	private void checkForCollisions() {
		ballCorners();
		paddleCorners();
		collideWithBricks();
		collideWithPaddle();
	}

	//gets an element at four corners of the ball
	private void ballCorners() {
		upperLeft = getElementAt(ball.getX(), ball.getY());
		upperRight = getElementAt(ball.getX() + BALL_DIAM, ball.getY());
		lowerLeft = getElementAt(ball.getX(), ball.getY() + BALL_DIAM);
		lowerRight = getElementAt(ball.getX() + BALL_DIAM, ball.getY() + BALL_DIAM);
	}
	
	private void paddleCorners() {
		leftEdge = getElementAt(paddle.getX(), paddle.getY() + PADDLE_HEIGHT);
		rightEdge = getElementAt(paddle.getX() + PADDLE_WIDTH, paddle.getY() + PADDLE_HEIGHT);
	}

	//removes the bricks when the ball collides with them,
	//counts the amount of bricks left and gold points
	//and then reverses the ball direction after collision
	private void collideWithBricks() {
		if (upperLeft != null && upperLeft != paddle &&
				upperLeft != brickcounter && upperLeft != goldcounter) {
			remove(upperLeft);
			Counter();
			vy = -vy;
		} else if (upperRight != null && upperRight != paddle &&
				upperRight != brickcounter && upperRight != goldcounter) {
			remove(upperRight);
			Counter();
			vy = -vy;
		} else if (lowerLeft != null && lowerLeft != paddle &&
				lowerLeft != brickcounter && lowerLeft != goldcounter) {
			remove(lowerLeft);
			Counter();
			vy = -vy;
		} else if (lowerRight != null && lowerRight != paddle && 
				lowerRight != brickcounter && lowerRight != goldcounter) {
			remove(lowerRight);
			Counter();
			vy = -vy;
		}
	}
	
	//makes the ball reverse when it collides with the paddle
	private void collideWithPaddle() {
		if (lowerLeft == paddle || lowerRight == paddle) {
			bounceClip.play();
			vy = -yVel;
		}
	}
	
	//increases Y velocity of the ball when 
	//less bricks left - to make the game harder
	private void speedUp() {
		if (bricksLeft < 80 && bricksLeft > 60) {
			if (vy == -8.0 || vy == +8.0) {
				vy = vy * 1.10;
			}
		} else if (bricksLeft < 60 && bricksLeft > 40) {
			if (vy == -8.8 || vy == -8.0)  {
				vy = -8.0 * 1.20;
			} else if (vy == +8.8 || vy == +8.0) {
				vy = +8.0 * 1.20;
			}
		} else if (bricksLeft < 40 && bricksLeft > 20) {
			if (vy == -9.6 || vy == -8.0) {
				vy = -8.0 * 1.30;
			} else if (vy == +9.6 || vy == +8.0) {
				vy = +8.0 * 1.30;
			}
		} else if (bricksLeft < 20) {
			if (vy == -10.4 || vy == -8.0) {
				vy = -8.0 * 1.50;
			} else if (vy == +10.4 || vy == +8.0) {
				vy = +8.0 * 1.50;
			}
		}
	}
	
	//counts the amount of bricks left and gold points
	private void Counter() {
		bricksLeft -= 1;
		brickcounter.setLabel("Bricks left: " + bricksLeft);
		gold += 1;
		goldcounter.setLabel("Golds: " + gold);
	}
	
	/** determines if game is over -- true if either
	* the UFO is destroyed or if the UFO lands */
	private boolean gameOver() {
		return (bricks == null) ||
				(ball.getY() + BALL_RADIUS >= getHeight() || bricksLeft == 0);
	}
	
	private void printGameWin() {
		win = new GLabel("You Won!");
		win.setFont("Helvetica-bold-30");
		win.setColor(Color.BLACK);
		double xwin = getWidth()/2 - (win.getWidth()/2);
		double ywin = getHeight()/2 - (win.getAscent()/2);
		add(win, xwin, ywin);
		
		brickcounter.setLocation(xwin, ywin + 30);
	}
	
	private void printGameOver() {
		lost = new GLabel("Game Over!");
		lost.setFont("Helvetica-bold-30");
		lost.setColor(Color.BLACK);
		double xlost = getWidth()/2 - (lost.getWidth()/2);
		double ylost = getHeight()/2 - (lost.getAscent()/2);
		add(lost, xlost, ylost);
	}
	
	private GObject upperLeft, upperRight,
		lowerLeft, lowerRight, leftEdge, rightEdge;
	
	private GLabel lost, win, ready, go, brickcounter, goldcounter;
}
