/*
 * File: Target.java
 * Name:
 * Section Leader:
 * -----------------
 * This file is the starter file for the Target problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {	
	public void run() {
		
		putOuterOval();
		putInnerOval();
		putCenterOval();
		
	}
	
	int radius = 72;
	
	private void putOuterOval() {
		int diameter = radius*2;
		int x = getWidth()/2 - radius;
		int y = getHeight()/2 - radius;
		GOval OuterOval = new GOval(x, y, diameter, diameter);	//creating new oval object (x, y, width, height)
		OuterOval.setColor(Color.RED); 							//setting line color
		OuterOval.setFilled(true); 								//adding fill
		OuterOval.setFillColor(Color.RED);						//setting fill color
		add(OuterOval);											//finally, adding the object
	}
	private void putInnerOval() {
		double diameter = 93.6;
		double x = getWidth()/2 - diameter/2;
		double y = getHeight()/2 - diameter/2;
		GOval InnerOval = new GOval(x, y, diameter, diameter);		
		InnerOval.setColor(Color.WHITE); 		
		InnerOval.setFilled(true); 				
		InnerOval.setFillColor(Color.WHITE);	
		add(InnerOval);
	}
	private void putCenterOval() {
		double diameter = 43.2;
		double x = getWidth()/2 - diameter/2;
		double y = getHeight()/2 - diameter/2;
		GOval CenterOval = new GOval(x, y, diameter, diameter);		
		CenterOval.setColor(Color.RED); 		
		CenterOval.setFilled(true); 				
		CenterOval.setFillColor(Color.RED);	
		add(CenterOval);
	}
}
