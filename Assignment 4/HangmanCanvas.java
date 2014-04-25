/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import java.awt.Color;

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {
	String incorrectLetters = "";
	int count = 0;

/** Resets the display so that only the scaffold appears */
	public void reset() {
		scaffold();
	}

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {
		String guessword = "";
		guessword += word;
		
		label.setLabel(guessword);
		label.setFont("Helvetica-20");
		
		int x = getWidth()/2 - BEAM_LENGTH;
		int y = getHeight() - 32;
		
		add(label, x, y);
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user. Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char letter) {
		count++;
		if (count == 1) {
			head();
		} else if (count == 2) {
			body();
		} else if (count == 3) {
			rightHand();
		} else if (count == 4) {
			leftHand();
		} else if (count == 5) {
			rightLeg();
		} else if (count == 6) {
			leftLeg();
		} else if (count == 7) {
			rightFoot();
		} else if (count == 8) {
			leftFoot();
		}
		updateIncorrectLetter(letter);
	}
	
	//adds the incorrect letter into incorrectLetters string
	private void updateIncorrectLetter(char letter) {
		incorrectLetters += letter;
		GLabel label = new GLabel(incorrectLetters);
		int x = getWidth()/2 - BEAM_LENGTH;
		int y = getHeight() - 10;
		add(label, x, y);
	}
	
	//the scaffold that the user is being hang from
	private void scaffold() {
		GLine scaffold = new GLine(getWidth()/2 - BEAM_LENGTH, getHeight()/10,
				getWidth()/2 - BEAM_LENGTH, getHeight()/10 + SCAFFOLD_HEIGHT);
		GLine beam = new GLine(getWidth()/2 - BEAM_LENGTH, getHeight()/10,
				getWidth()/2, getHeight()/10);
		GLine rope = new GLine(getWidth()/2, getHeight()/10,
				getWidth()/2, getHeight()/10 + ROPE_LENGTH);
		add(scaffold);
		add(beam);
		add(rope);
	}
	
	private void head() {
		GOval head = new GOval(getWidth()/2 - HEAD_RADIUS,
				getHeight()/10 + ROPE_LENGTH, HEAD_DIAM, HEAD_DIAM);
		add(head);
	}
	
	private void body() {
		GLine body = new GLine(getWidth()/2, getHeight()/10 + ROPE_LENGTH + HEAD_DIAM,
				getWidth()/2, getHeight()/10 + ROPE_LENGTH + HEAD_DIAM + BODY_LENGTH);
		add(body);
	}
	
	private void rightHand() {
		GLine upperarm = new GLine(getWidth()/2,
				getHeight()/10 + ROPE_LENGTH + HEAD_DIAM + ARM_OFFSET_FROM_HEAD,
				getWidth()/2 - UPPER_ARM_LENGTH,
				getHeight()/10 + ROPE_LENGTH + HEAD_DIAM + ARM_OFFSET_FROM_HEAD);
		add(upperarm);
		
		GLine lowerarm = new GLine(getWidth()/2 - UPPER_ARM_LENGTH,
				getHeight()/10 + ROPE_LENGTH + HEAD_DIAM + ARM_OFFSET_FROM_HEAD,
				getWidth()/2 - UPPER_ARM_LENGTH,
				getHeight()/10 + ROPE_LENGTH + HEAD_DIAM + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH);
		add(lowerarm);
	}
	
	private void leftHand() {
		GLine upperarm = new GLine(getWidth()/2,
				getHeight()/10 + ROPE_LENGTH + HEAD_DIAM + ARM_OFFSET_FROM_HEAD,
				getWidth()/2 + UPPER_ARM_LENGTH,
				getHeight()/10 + ROPE_LENGTH + HEAD_DIAM + ARM_OFFSET_FROM_HEAD);
		add(upperarm);
		
		GLine lowerarm = new GLine(getWidth()/2 + UPPER_ARM_LENGTH,
				getHeight()/10 + ROPE_LENGTH + HEAD_DIAM + ARM_OFFSET_FROM_HEAD,
				getWidth()/2 + UPPER_ARM_LENGTH,
				getHeight()/10 + ROPE_LENGTH + HEAD_DIAM + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH);
		add(lowerarm);
	}
	
	private void rightLeg() {
		GLine upperleg = new GLine(getWidth()/2,
				getHeight()/10 + ROPE_LENGTH + HEAD_DIAM + BODY_LENGTH,
				getWidth()/2 - HIP_WIDTH,
				getHeight()/10 + ROPE_LENGTH + HEAD_DIAM + BODY_LENGTH);
		add(upperleg);
		
		GLine lowerleg = new GLine(getWidth()/2 - HIP_WIDTH,
				getHeight()/10 + ROPE_LENGTH + HEAD_DIAM + BODY_LENGTH,
				getWidth()/2 - HIP_WIDTH,
				getHeight()/10 + ROPE_LENGTH + HEAD_DIAM + BODY_LENGTH + LEG_LENGTH);
		add(lowerleg);
	}
	
	private void leftLeg() {
		GLine upperleg = new GLine(getWidth()/2,
				getHeight()/10 + ROPE_LENGTH + HEAD_DIAM + BODY_LENGTH,
				getWidth()/2 + HIP_WIDTH,
				getHeight()/10 + ROPE_LENGTH + HEAD_DIAM + BODY_LENGTH);
		add(upperleg);
		
		GLine lowerleg = new GLine(getWidth()/2 + HIP_WIDTH,
				getHeight()/10 + ROPE_LENGTH + HEAD_DIAM + BODY_LENGTH,
				getWidth()/2 + HIP_WIDTH,
				getHeight()/10 + ROPE_LENGTH + HEAD_DIAM + BODY_LENGTH + LEG_LENGTH);
		add(lowerleg);
	}
	
	private void rightFoot() {
		GLine foot = new GLine(getWidth()/2 - HIP_WIDTH,
				getHeight()/10 + ROPE_LENGTH + HEAD_DIAM + BODY_LENGTH + LEG_LENGTH,
				getWidth()/2 - HIP_WIDTH - FOOT_LENGTH,
				getHeight()/10 + ROPE_LENGTH + HEAD_DIAM + BODY_LENGTH + LEG_LENGTH);
		add(foot);
	}
	
	private void leftFoot() {
		GLine foot = new GLine(getWidth()/2 + HIP_WIDTH,
				getHeight()/10 + ROPE_LENGTH + HEAD_DIAM + BODY_LENGTH + LEG_LENGTH,
				getWidth()/2 + HIP_WIDTH + FOOT_LENGTH,
				getHeight()/10 + ROPE_LENGTH + HEAD_DIAM + BODY_LENGTH + LEG_LENGTH);
		add(foot);
	}
	
/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int HEAD_DIAM = HEAD_RADIUS * 2;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;
	private GLabel label = new GLabel("");
}
