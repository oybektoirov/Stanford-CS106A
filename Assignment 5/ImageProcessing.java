import acm.graphics.*;
import acm.io.*;
import acm.program.*;

import java.util.*;
import java.io.*;
import java.lang.reflect.Array;

public class ImageProcessing extends GraphicsProgram {
	
	public void run() {
		GImage image = new GImage("milkmaid.jpg");
		GImage flipped = flipHorizontal(image);
		
		double x = image.getWidth()/3;
		double y = image.getHeight()/3;
		
		image.setSize(x, y);
		add(image, 50, 50);
		
		flipped.setSize(x, y);
		add(flipped, 50 + y + 50, 50);
	}
	
	private GImage flipHorizontal(GImage image) {
		int[][] array = image.getPixelArray();
		int height = array.length;
		int width = array[0].length;
		int[][] newarray = new int[height][width];
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				newarray[i][j] = array[i][width - j - 1];
			}
		}
		
		return new GImage(newarray);
	}

}
