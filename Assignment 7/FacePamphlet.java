import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import acm.graphics.GImage;
import acm.program.Program;
import acm.util.ErrorException;

/* 
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */
public class FacePamphlet extends Program 
					implements FacePamphletConstants {

	/**
	 * This method has the responsibility for initializing the 
	 * interactors in the application, and taking care of any other 
	 * initialization that needs to be performed.
	 */
	public void init() {
		buildScaffolding();
    }
    
  
    /**
     * This class is responsible for detecting when the buttons are
     * clicked or interactors are used, so you will have to add code
     * to respond to these actions.
     */
    public void actionPerformed(ActionEvent e) {
    	name = nameField.getText();
    	status = statusField.getText();
    	imageName = imageField.getText();
    	friend = friendField.getText();
    	
		if (e.getActionCommand().equals("Add") 
				&& !name.equals("")) {		
			addProfile();			
		} else if (e.getActionCommand().equals("Delete") 
				&& !name.equals("")) {
			deleteProfile();
		} else if (e.getActionCommand().equals("Lookup") 
				&& !name.equals("")) {
			lookupProfile();
		} else if (e.getActionCommand().equals("Change Status") 
				&& !status.equals("")) {
			changeStatus();
		} else if (e.getActionCommand().equals("Change Picture") 
				&& !imageName.equals("")) {
			changeImage();
		} else if (e.getActionCommand().equals("Add Friend") 
				&& !friend.equals("")) {
			addFriend();
		}
	}
    
    private void buildScaffolding() {
		//Namefield
		nameField = new JTextField(TEXT_FIELD_SIZE);
		nameField.setActionCommand("Add");
		nameField.setActionCommand("Delete");
		nameField.setActionCommand("Lookup");
		nameField.addActionListener(this);
		
		add(new JLabel("Name"), NORTH);
		add(nameField, NORTH);
		
		add(new JButton("Add"), NORTH);
		add(new JButton("Delete"), NORTH);
		add(new JButton("Lookup"), NORTH);
		
		//Statusfield
		statusField = new JTextField(TEXT_FIELD_SIZE);
		statusField.setActionCommand("Change Status");
		statusField.addActionListener(this);
		
		add(statusField, WEST);
		add(new JButton("Change Status"), WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);

		//Statusfield
		imageField = new JTextField(TEXT_FIELD_SIZE);
		imageField.setActionCommand("Change Picture");
		imageField.addActionListener(this);
		
		add(imageField, WEST);
		add(new JButton("Change Picture"), WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		
		//Friendfield
		friendField = new JTextField(TEXT_FIELD_SIZE);
		friendField.setActionCommand("Add Friend");
		friendField.addActionListener(this);
		
		add(friendField, WEST);
		add(new JButton("Add Friend"), WEST);
		
		//the rest
		canvas = new FacePamphletCanvas();
		add(canvas);
		
		database = new FacePamphletDatabase();
		addActionListeners();
    }
    
    private void addProfile() {
    	if (database.containsProfile(name) && currentProfile == null) {
			canvas.removeAll();
			canvas.showMessage("A profile " + name + " already exists.");
		} else if (database.containsProfile(name) && currentProfile != null) {
			canvas.removeAll();
			canvas.showMessage("A profile " + name + " already exists.");
			currentProfile = null;
		} else {
			profile = new FacePamphletProfile(name);
			database.addProfile(profile);
			canvas.displayProfile(profile);
			canvas.showMessage("New profile is created.");
			currentProfile = profile;
		}
    }
    
    private void deleteProfile() {
    	if (database.containsProfile(name) && currentProfile == null) {
    		
    		//removes the profile from other friends' list of friends
    		for (String s : database.getProfileList()) {
    			database.getProfile(s).removeFriend(name);
    		}
    		
			database.deleteProfile(name);
			canvas.removeAll();
			canvas.showMessage(name + " profile is deleted.");
			
		} else if (database.containsProfile(name) && currentProfile != null) {
			
			for (String s : database.getProfileList()) {
    			database.getProfile(s).removeFriend(name);
    		}
			
			database.deleteProfile(name);
			canvas.removeAll();
			canvas.showMessage(name + " profile is deleted.");
			currentProfile = null;
			
		} else {
			canvas.removeAll();
			canvas.showMessage("A profile with the name " + name + " does not exist.");
			currentProfile = null;
		}
    }
    
    private void lookupProfile() {
    	if (database.containsProfile(name) && currentProfile == null) {
    		
			canvas.displayProfile(database.getProfile(name));
			canvas.showMessage("A profile of " + name);
			currentProfile = database.getProfile(name);
			
		} else if (database.containsProfile(name) 
				&& currentProfile.getName().equals(name)) {
			
			canvas.displayProfile(database.getProfile(name));
			canvas.showMessage("Currently displaying " + name);
			
		} else if (database.containsProfile(name) 
				&& !currentProfile.getName().equals(name)) {
			
			canvas.displayProfile(database.getProfile(name));
			canvas.showMessage("A profile of " + name);
			currentProfile = database.getProfile(name);
			
		} else {
			
			canvas.removeAll();
			canvas.showMessage("A profile with the name " + name + " does not exist");
			currentProfile = null;
			
		}
    }
    
    private void changeStatus() {
    	if (currentProfile != null) {
			currentProfile.setStatus(status);
			canvas.displayProfile(currentProfile);
		} else {
			canvas.removeAll();
			canvas.showMessage("Please select a profile.");
			currentProfile = null;
		}
    }
    
    private void changeImage() {
    	if (currentProfile != null && !imageName.equals("")) {
    		
			try {
				GImage image = new GImage(imageName);
				currentProfile.setImage(image);
				canvas.displayProfile(currentProfile);
			} catch (ErrorException ex) {
				canvas.displayProfile(currentProfile);
				canvas.showMessage("Cannot find the image.");
			}
			
		} else {
			canvas.removeAll();
			canvas.showMessage("Please select a profile.");
		}
    }
    
    private void addFriend() {
    	if (currentProfile != null && database.containsProfile(friend)) {
    		
			if (currentProfile.addFriend(friend) == true) {
				canvas.displayProfile(currentProfile);
				canvas.showMessage(friend + " is added as a friend.");
				database.getProfile(friend).addFriend(currentProfile.getName());
			} else {
				canvas.displayProfile(currentProfile);
				canvas.showMessage(friend + " already exists in your friendlist.");
			}
			
		} else if (!database.containsProfile(friend) && currentProfile != null) {
			canvas.displayProfile(currentProfile);
			canvas.showMessage(friend + " does not exist.");
		} else {
			canvas.removeAll();
			canvas.showMessage("Please select a profile.");
		}
    }
    
    private JTextField nameField, statusField, imageField, friendField;
    private String name, status, imageName, friend;
    private FacePamphletDatabase database;
    private FacePamphletProfile profile, currentProfile;
    private FacePamphletCanvas canvas;
   

}
