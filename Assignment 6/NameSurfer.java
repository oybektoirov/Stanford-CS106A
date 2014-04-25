/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import acm.graphics.GLabel;
import acm.program.Program;

public class NameSurfer extends Program implements NameSurferConstants {
/**
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the bottom of the window.
 */
	public void init() {
		buildScaffolding();
	}
	
	public void actionPerformed(ActionEvent e) {
		String text = textField.getText();
		if (e.getActionCommand().equals("Graph")) {
			if (dataBase.findEntry(text) == null) {
				graph.doesNotExist();
			} else {
				graph.addEntry(dataBase.findEntry(text));
			}
		} else if (e.getActionCommand().equals("Clear")) {
			graph.clear();
		}
	}
	
	private void buildScaffolding() {
	    textField = new JTextField(10);
	    textField.setActionCommand("Graph");
	    textField.setActionCommand("Clear");
	    textField.addActionListener(this);

	    add(new JLabel("Name"), SOUTH);
	    add(textField, SOUTH);
	    
	    add(new JButton("Graph"), SOUTH);
	    add(new JButton("Clear"), SOUTH);
	    
	    dataBase = new NameSurferDataBase(NAMES_DATA_FILE);
	    graph = new NameSurferGraph();
	    
	    add(graph);
	    addActionListeners();
	}
	
	private JTextField textField;
	private NameSurferGraph graph;
	private NameSurferDataBase dataBase;
	
	
}
