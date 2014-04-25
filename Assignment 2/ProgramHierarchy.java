/*
 * File: ProgramHierarchy.java
 * Name:
 * Section Leader:
 * ---------------------------
 * This file is the starter file for the ProgramHierarchy problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class ProgramHierarchy extends GraphicsProgram {	
	public void run() {
		
		putProgram();
		putGraphicsProgram();
		putConsoleProgram();
		putDialogProgram();
		putLines();
		
	}
	int width = 150;
	int height = 50;
	private void putProgram() {
		int x = (getWidth() - width)/2;
		int y = getHeight()/3 - height;
		GRect box = new GRect(x, y, width, height);
		add(box);
		
		GLabel label = new GLabel("Program", x, y);
		double a = width/3;
		double b = height/2;
		label.setLocation(x+a, y+b);
		add(label);
	}
	private void putGraphicsProgram() {
		int x = (getWidth() - width)/2 - width;
		int y = getHeight()/2 - height + 10;
		GRect box = new GRect(x, y, width, height);
		add(box);
		
		GLabel label = new GLabel("GraphicsProgram", x, y);
		double a = width/7;
		double b = height/2;
		label.setLocation(x+a, y+b);
		add(label);
	}
	private void putConsoleProgram() {
		int x = (getWidth() - width)/2;
		int y = getHeight()/2 - height + 10;
		GRect box = new GRect(x, y, width, height);
		add(box);
		
		GLabel label = new GLabel("ConsoleProgram", x, y);
		double a = width/7;
		double b = height/2;
		label.setLocation(x+a, y+b);
		add(label);
	}
	private void putDialogProgram() {
		int x = (getWidth() - width)/2 + width;
		int y = getHeight()/2 - height + 10;
		GRect box = new GRect(x, y, width, height);
		add(box);
		
		GLabel label = new GLabel("DialogProgram", x, y);
		double a = width/7;
		double b = height/2;
		label.setLocation(x+a, y+b);
		add(label);
	}
	private void putLines() {
		//starting points
		int x = ((getWidth() - width)/2) + (width/2);
		int y = ((getHeight()/3 - height) + height);
		
		//ending points
		int x1 = ((getWidth() - width)/2 - width) + width/2;
		int y1 = (getHeight()/2 - height + 10);
		GLine graphicsline = new GLine(x, y, x1, y1);
		add(graphicsline);
		
		int x2 = ((getWidth() - width)/2) + width/2;
		int y2 = (getHeight()/2 - height + 10);
		GLine consoleline = new GLine(x, y, x2, y2);
		add(consoleline);
		
		int x3 = ((getWidth() - width)/2) + width + width/2;
		int y3 = (getHeight()/2 - height + 10);
		GLine dialogline = new GLine(x, y, x3, y3);
		add(dialogline);
	}
}

