/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes or the window is resized.
 */

import java.awt.Color;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import acm.graphics.*;
import acm.util.RandomGenerator;

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {
	/**
	* Creates a new NameSurferGraph object that displays the data.
	*/
	public NameSurferGraph() {
		addComponentListener(this);
		
		colorMap = new HashMap<String, Color>();
		graphMap = new HashMap<String, NameSurferEntry>();
		update();
	}
	
	/**
	* Clears the list of name surfer entries stored inside this class.
	*/
	public void clear() {
		graphMap.clear();
		update();
	}
	
	/**
	* Adds a new NameSurferEntry to the list of entries on the display.
	* Note that this method does not actually draw the graph, but
	* simply stores the entry; the graph is drawn by calling update.
	*/
	public void addEntry(NameSurferEntry entry) {
		graphMap.put(entry.getName(), entry);
		update();
	}
	
	
	
	/**
	* Updates the display image by deleting all the graphical objects
	* from the canvas and then reassembling the display according to
	* the list of entries. Your application must call update after
	* calling either clear or addEntry; update is also called whenever
	* the size of the canvas changes.
	*/
	public void update() {
		
        if (graphMap.isEmpty()) { // update is called from clear
        	
			removeAll();
			colorMap.clear();
			buildScaffolding();
			
		} else { //update is called from addEntry
			
			removeAll();
			buildScaffolding();
			
			for (String name: graphMap.keySet()) {
				
				setupColor(name);
				addedEntry = graphMap.get(name);
				
				int nextActualRank;
				int nextRank;
				
				for (int d = 1; d <= NDECADES; d++) {
					int actualRank = addedEntry.getRank(d);
					int rank = ((getHeight() - (2 * GMS)) * actualRank) / MAX_RANK;
					
					if (d + 1 == 12) {
						nextActualRank = addedEntry.getRank(d);
						nextRank = ((getHeight() - (2 * GMS)) * nextActualRank) / MAX_RANK;
					} else {
						nextActualRank = addedEntry.getRank(d + 1); 
						nextRank = ((getHeight() - (2 * GMS)) * nextActualRank) / MAX_RANK;
					}
					
					//building graphs
					if (actualRank == 0 && d == NDECADES || actualRank == 0) {
						
						GLabel label = new GLabel(addedEntry.getName() + "*",
								getWidth() / 11 * (d - 1) + 4, getHeight() - GMS - 4);
						label.setColor(colorMap.get(name));
						add(label);
						
					} else if (d == NDECADES) {
						
						GLabel label = new GLabel(addedEntry.getName() + " " + actualRank,
								getWidth() / 11 * (d - 1) + 4, GMS + rank - 4);
						label.setColor(colorMap.get(name));
						add(label);
						
					} else {
						
						if (nextActualRank == 0) {
							
							GLabel label = new GLabel(addedEntry.getName() + " " + actualRank,
									getWidth() / 11 * (d - 1) + 4, GMS + rank - 4);
							GLine line = new GLine(getWidth() / 11 * (d - 1), GMS + rank,
									getWidth() / 11 * d, getHeight() - GMS);
							label.setColor(colorMap.get(name));
							add(label);
							add(line);
							
						} else {
							
							GLabel label = new GLabel(addedEntry.getName() + " " + actualRank,
									getWidth() / 11 * (d - 1) + 4, GMS + rank - 4);
							GLine line = new GLine(getWidth() / 11 * (d - 1), GMS + rank,
									getWidth() / 11 * d, GMS + nextRank);
							label.setColor(colorMap.get(name));
							add(label);
							add(line);
							
						}	
					}	
				}
			}
		}
	}
	
	public void doesNotExist() {
		GLabel label = new GLabel("Name does not exist. Try another one.");
		add(label, (getWidth() - label.getWidth()) / 2, GMS - 2);
	}
	
	private void buildScaffolding() {
		GLine topLine = new GLine(0, (GMS * getHeight()) / APPLICATION_HEIGHT,
				getWidth(), (GMS * getHeight()) / APPLICATION_HEIGHT);
		GLine bottomLine = new GLine(0, getHeight() - (GMS * getHeight()) / APPLICATION_HEIGHT,
				getWidth(), getHeight() - (GMS * getHeight()) / APPLICATION_HEIGHT);
		
		add(topLine);
		add(bottomLine);
		
		for (int i = 1; i <= 10; i++) {
			vertical = new GLine(getWidth() / 11 * i, 0,
					getWidth() / 11 * i, getHeight());
			add(vertical);
		}
		
		for (int i = 0; i <= 10; i++) {
			String year = Integer.toString(1900 + (10 * i));
			decade = new GLabel(year);
			
			double marginX = (((getWidth() / 11) / 2) - (decade.getWidth() / 2)) + ((getWidth() / 11) * i);
			double marginY = ((getHeight() - (GMS / 2)) + (decade.getAscent() / 2));
			
			decade.setLocation(marginX, marginY);
			add(decade);
		}
		
	}
	
	//using colors by colorList order
	private void setupColor(String name) {
		
		if (colorMap.isEmpty()) {
			
			colorMap.put(name, colorList[colorOrder]);
			
			if (colorOrder < 3) {
				colorOrder++; 
			} else {
				colorOrder -= 3;
			}
			
		} else if (!colorMap.containsKey(name)) {
			
			colorMap.put(name, colorList[colorOrder]);
			
			if (colorOrder < 3) {
				colorOrder++; 
			} else {
				colorOrder -= 3;
			}	
			
		}
		
	}
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
	
	private int colorOrder;
	private GLabel decade;
	private GLine vertical;
	private NameSurferEntry addedEntry;
	private HashMap<String, Color> colorMap;
	private HashMap<String, NameSurferEntry> graphMap;
	private Color[] colorList = {Color.black, Color.red, Color.blue, Color.magenta};
	
}
