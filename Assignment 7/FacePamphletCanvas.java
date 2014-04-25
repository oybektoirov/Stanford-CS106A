/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */


import acm.graphics.*;
import java.awt.*;
import java.util.*;

public class FacePamphletCanvas extends GCanvas 
					implements FacePamphletConstants {
	
	/** 
	 * Constructor
	 * This method takes care of any initialization needed for 
	 * the display
	 */
	public FacePamphletCanvas() {
		//
	}

	
	/** 
	 * This method displays a message string near the bottom of the 
	 * canvas.  Every time this method is called, the previously 
	 * displayed message (if any) is replaced by the new message text 
	 * passed in.
	 */
	public void showMessage(String msg) {
		GLabel message = new GLabel(msg);
		message.setFont(MESSAGE_FONT);
		add(message, (getWidth() / 2) - (message.getWidth() / 2), 
				getHeight() - BOTTOM_MESSAGE_MARGIN);
	}
	
	
	/** 
	 * This method displays the given profile on the canvas.  The 
	 * canvas is first cleared of all existing items (including 
	 * messages displayed near the bottom of the screen) and then the 
	 * given profile is displayed.  The profile display includes the 
	 * name of the user from the profile, the corresponding image 
	 * (or an indication that an image does not exist), the status of
	 * the user, and a list of the user's friends in the social network.
	 */
	public void displayProfile(FacePamphletProfile profile) {
		removeAll();
		buildName(profile);
		buildImage(profile);
		buildStatus(profile);
		buildFriends(profile);
	}
	
	private void buildName(FacePamphletProfile profile) {
		GLabel name = new GLabel(profile.getName());
		name.setColor(Color.BLUE);
		name.setFont(PROFILE_NAME_FONT);
		add(name, LEFT_MARGIN, TOP_MARGIN);
	}
	
	private void buildImage(FacePamphletProfile profile) {
		GRect imageBorder = new GRect(IMAGE_WIDTH, IMAGE_HEIGHT);
		GLabel imageLabel = new GLabel("No Image");
		imageLabel.setFont(PROFILE_IMAGE_FONT);
		
		if (profile.getImage() != null) {
			GImage image = profile.getImage();
			image.setSize(IMAGE_WIDTH, IMAGE_HEIGHT);
			add(image, LEFT_MARGIN, TOP_MARGIN + IMAGE_MARGIN);
		} else {
			add(imageBorder, LEFT_MARGIN, TOP_MARGIN + IMAGE_MARGIN);
			add(imageLabel, LEFT_MARGIN + (IMAGE_WIDTH / 2) 
					- (imageLabel.getWidth() / 2),
					TOP_MARGIN + IMAGE_MARGIN + (IMAGE_HEIGHT / 2) 
					- (imageLabel.getDescent() / 2));
		}
	}
	
	private void buildStatus(FacePamphletProfile profile) {
		GLabel statusLabel = new GLabel("Status: ");
		GLabel statusComment = new GLabel(profile.getStatus());
		statusLabel.setFont(PROFILE_STATUS_FONT);
		statusComment.setFont(PROFILE_STATUS_FONT);
		
		add(statusLabel, LEFT_MARGIN,
				TOP_MARGIN + IMAGE_MARGIN + IMAGE_HEIGHT + STATUS_MARGIN);
		add(statusComment, LEFT_MARGIN + statusLabel.getWidth(),
				TOP_MARGIN + IMAGE_MARGIN + IMAGE_HEIGHT + STATUS_MARGIN);
	}
	
	private void buildFriends(FacePamphletProfile profile) {
		int margin = 0;
		
		GLabel friendLabel = new GLabel("Friend:");
		friendLabel.setFont(PROFILE_FRIEND_LABEL_FONT);
		add(friendLabel, getWidth() / 2, TOP_MARGIN + IMAGE_MARGIN);
		
		for (String friend : profile.getFriends()) {
			margin++;
			
			GLabel label = new GLabel(friend);
			label.setFont(PROFILE_FRIEND_FONT);
			add(label, getWidth() / 2, 
					TOP_MARGIN + IMAGE_MARGIN + (friendLabel.getAscent() + 4) * margin);
		}
	}
}
