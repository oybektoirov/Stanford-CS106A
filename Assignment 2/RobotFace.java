/* 
 * Name: RobotFace.java
 * This class contains Robot's face building.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class RobotFace extends GraphicsProgram {
	
	private static final int HEAD_WIDTH = 250;
	private static final int HEAD_HEIGHT = 300;
	private static final int EYE_RADIUS = HEAD_WIDTH/5;
	private static final int MOUTH_WIDTH = HEAD_WIDTH/2;
	private static final int MOUTH_HEIGHT = HEAD_HEIGHT/6;
	
	
	public void run() {
		putRobotFace();
	}
	

	
	private void putRobotFace() {
		int xHead = (getWidth()/2) - (HEAD_WIDTH/2);
		int yHead = (getHeight()/2) - (HEAD_HEIGHT/2);
		
		//Head 
		GRect head = new GRect(xHead, yHead, HEAD_WIDTH, HEAD_HEIGHT);
		head.setColor(Color.black);
		head.setFilled(true);
		head.setFillColor(Color.gray);
		add(head);
		
		//Left-eye
		GOval LeftEye = new GOval(xHead + HEAD_WIDTH/4,
				(yHead - EYE_RADIUS) + (HEAD_HEIGHT/4),
				EYE_RADIUS, EYE_RADIUS);
		LeftEye.setFilled(true);
		LeftEye.setColor(Color.YELLOW);
		add(LeftEye);
		
		//Right-eye
		GOval RightEye = new GOval((xHead + HEAD_WIDTH) - EYE_RADIUS - (HEAD_WIDTH/4),
				(yHead - EYE_RADIUS) + (HEAD_HEIGHT/4),
				EYE_RADIUS, EYE_RADIUS);
		RightEye.setFilled(true);
		RightEye.setColor(Color.YELLOW);
		add(RightEye);
		
		//Mouth
		GRect mouth = new GRect((xHead + MOUTH_WIDTH/2),
				(yHead + HEAD_HEIGHT) - (HEAD_HEIGHT/4),
				MOUTH_WIDTH, MOUTH_HEIGHT);
		mouth.setFilled(true);
		mouth.setFillColor(Color.WHITE);
		add(mouth);
	}

}
