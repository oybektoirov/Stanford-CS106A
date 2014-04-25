import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import acm.graphics.*;
import acm.program.*;

public class BoxDiagram extends GraphicsProgram {
	
	public void init() {
		buildScaffolding();
	}
	
	public void actionPerformed(ActionEvent e) {
		name = nameField.getText();
		if (e.getActionCommand().equals("Add") && !name.equals("")) {
			add(Box(), getWidth()/2 - BOX_WIDTH/2, 
					getHeight()/2 - BOX_HEIGHT/2);
		} else if (e.getActionCommand().equals("Remove") && !name.equals("")) {
			remove(boxMap.get(name));
		} else if (e.getActionCommand().equals("Clear")) {
			for (String boxName : boxMap.keySet()) {
				remove(boxMap.get(boxName));
			}
		}
	}
	
	public void mousePressed(MouseEvent e) {
		last = new GPoint(e.getPoint());
		gobj = getElementAt(last);
	}
	
	public void mouseDragged(MouseEvent e) {
		if (gobj != null) {
			gobj.move(e.getX() - last.getX(), e.getY() - last.getY());
			last = new GPoint(e.getPoint());
		}
	}
	
	public void mouseClicked(MouseEvent e) {
		if (gobj != null) gobj.sendToFront(); 
	}

	private GCompound Box() {
		GCompound box = new GCompound();
		GRect rect = new GRect(0, 0, BOX_WIDTH, BOX_HEIGHT);
		GLabel label = new GLabel(name);
		
		double x = BOX_WIDTH/2 - label.getWidth()/2;
		double y = BOX_HEIGHT/2 + label.getAscent()/2;
		
		box.add(rect);
		box.add(label, x, y);
		
		boxMap.put(name, box);
		return box;
	}
	
	private void buildScaffolding() {
		nameField = new JTextField(20);
		nameField.setActionCommand("Add");
		nameField.setActionCommand("Remove");
		nameField.addActionListener(this);
		
		add(new JLabel("Name"), SOUTH);
		add(nameField, SOUTH);
		
		add(new JButton("Add"), SOUTH);
		add(new JButton("Remove"), SOUTH);
		add(new JButton("Clear"), SOUTH);
		
		addActionListeners();
		addMouseListeners();
	}
	
	private String name;
	private GPoint last;
	private GObject gobj;
	private JTextField nameField;
	private Map<String, GObject> boxMap = new HashMap<String, GObject>();
	
	private static final double BOX_WIDTH = 120;
	private static final double BOX_HEIGHT = 50;

}
