/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import java.util.*;
import acm.io.*;
import acm.program.*;
import acm.util.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
	
	public static void main(String[] args) {
		new Yahtzee().start(args);
	}
	
	public void run() {
		IODialog dialog = getDialog();
		nPlayers = dialog.readInt("Enter number of players");
		playerNames = new String[nPlayers];	
		
		for (int i = 1; i <= nPlayers; i++) {
			playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
		}
		
		categoryList = new int[nPlayers][N_SCORING_CATEGORIES + 1];
		
	    /* 1st column is players, 2nd is upperscore total, 
		3rd is lowerscore total and 4th is total of upper and lower scores */
		trackList = new int[nPlayers][4]; 
		
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
		playGame();
	}
	
	//yahtzee game starts
	private void playGame() {
		for (int c = 0; c < N_SCORING_CATEGORIES; c++) {
			for (int player = 1; player <= nPlayers; player++) {
				display.printMessage(playerNames[player - 1] + ", roll the dice.");
	
				//roll 1
				display.waitForPlayerToClickRoll(player);
				for (int i = 0; i < N_DICE; i++) {
					dice[i] = rgen.nextInt(1, 6);
				}
				display.displayDice(dice);
				display.printMessage(playerNames[player - 1] 
						+ ", you have two rolls left. Please roll the dice.");
				
				//roll 2
				display.waitForPlayerToSelectDice();
				for (int i = 0; i < N_DICE; i++) { 
					if (display.isDieSelected(i)) {
						dice[i] = rgen.nextInt(1, 6);
					}
				}
				display.displayDice(dice);
				display.printMessage(playerNames[player - 1] 
						+ ", you have one roll left. Please roll the dice.");
				
				
				//roll 3
				display.waitForPlayerToSelectDice();
				for (int i = 0; i < N_DICE; i++) { 
					if (display.isDieSelected(i)) {
						dice[i] = rgen.nextInt(1, 6);
					}
				}
				display.displayDice(dice);
				display.printMessage(playerNames[player - 1] 
						+ ", select a category to receive a score.");
				
				int category = display.waitForPlayerToSelectCategory();
				category = checkCategory(category, player - 1);
				int score = countScore(category);
				display.updateScorecard(category, player, score);
				
				trackScore(category, player - 1, score);
			}
		}
		
		//computes final scores onto display
		int highest = 0;
		int winner = 0;
		
		for (int i = 1; i <= nPlayers; i++) {
			if (trackList[i - 1][1] >= 63) {
				display.updateScorecard(UPPER_BONUS, i, 35);
			} else {
				display.updateScorecard(UPPER_BONUS, i, 0);
			}
			display.updateScorecard(UPPER_SCORE, i, trackList[i - 1][1]);
			display.updateScorecard(LOWER_SCORE, i, trackList[i - 1][2]);
			display.updateScorecard(TOTAL, i, trackList[i - 1][3]);
			
			//computes winner
			if (highest == 0) {
				highest = trackList[i - 1][3];
			} else if (highest < trackList[i - 1][3]) {
				highest = trackList[i - 1][3];
				winner = i - 1;
			}
		}
		
		display.printMessage("The winner is " 
				+ playerNames[winner].toUpperCase() + "!");
		
	}
	
	//keeps track of scores for each player
	private void trackScore(int category, int player, int score) {
		int upperscore = 1;
		int lowerscore = 2;
		int totalscore = 3;
		
		if (category < UPPER_SCORE) {
			trackList[player][upperscore] += score;
		} else {
			trackList[player][lowerscore] += score;
		}
		trackList[player][totalscore] = trackList[player][upperscore] 
				+ trackList[player][lowerscore];
	}
	
	//checks whether selected category was selected before, and keeps track of selected categories 
	private int checkCategory(int category, int player) {
		while (category == categoryList[player][1] ||
				category == categoryList[player][2] ||
				category == categoryList[player][3] ||
				category == categoryList[player][4] ||
				category == categoryList[player][5] ||
				category == categoryList[player][6] ||
				category == categoryList[player][7] ||
				category == categoryList[player][8] ||
				category == categoryList[player][9] ||
				category == categoryList[player][10] ||
				category == categoryList[player][11] ||
				category == categoryList[player][12] ||
				category == categoryList[player][13]) {
			display.printMessage("You have to select a category that wasn't selected before!");
			category = display.waitForPlayerToSelectCategory();
		}
		
		if (categoryList[player][1] == 0) {
			categoryList[player][1] = category;
		} else if (categoryList[player][2] == 0) {
			categoryList[player][2] = category;
		} else if (categoryList[player][3] == 0) {
			categoryList[player][3] = category;
		} else if (categoryList[player][4] == 0) {
			categoryList[player][4] = category;
		} else if (categoryList[player][5] == 0) {
			categoryList[player][5] = category;
		} else if (categoryList[player][6] == 0) {
			categoryList[player][6] = category;
		} else if (categoryList[player][7] == 0) {
			categoryList[player][7] = category;
		} else if (categoryList[player][8] == 0) {
			categoryList[player][8] = category;
		} else if (categoryList[player][9] == 0) {
			categoryList[player][9] = category;
		} else if (categoryList[player][10] == 0) {
			categoryList[player][10] = category;
		} else if (categoryList[player][11] == 0) {
			categoryList[player][11] = category;
		} else if (categoryList[player][12] == 0) {
			categoryList[player][12] = category;
		} else if (categoryList[player][13] == 0) {
			categoryList[player][13] = category;
		}	
		return category;
	}
	
	//computes score for selected category
	private int countScore(int category) {
		int score = 0;
		if (category == CHANCE) {
			
			for (int i = 0; i < N_DICE; i++) {
				score += dice[i];
			}
			
		} else if (category == ONES) {
			
			for (int i = 0; i < N_DICE; i++) {
				if (dice[i] == ONES) {
					score += dice[i];
				}
			}
			
		} else if (category == TWOS) {
			
			for (int i = 0; i < N_DICE; i++) {
				if (dice[i] == TWOS) {
					score += dice[i];
				}
			}
			
		} else if (category == THREES) {
			
			for (int i = 0; i < N_DICE; i++) {
				if (dice[i] == THREES) {
					score += dice[i];
				}
			}
			
		} else if (category == FOURS) {
			
			for (int i = 0; i < N_DICE; i++) {
				if (dice[i] == FOURS) {
					score += dice[i];
				}
			}
			
		} else if (category == FIVES) {
			
			for (int i = 0; i < N_DICE; i++) {
				if (dice[i] == FIVES) {
					score += dice[i];
				}
			}
			
		} else if (category == SIXES) {
			
			for (int i = 0; i < N_DICE; i++) {
				if (dice[i] == SIXES) {
					score += dice[i];
				}
			}
			
		} else if (category == THREE_OF_A_KIND) { //three of the same type dice == sum of all dices
			int count = 0;
			
			for (int i = 0; i < N_DICE; i++) {
				for (int j = 0; j < N_DICE; j++) {
					if (dice[i] == dice[j]) {
						count++;
					}
				}
			}
			
			count /= 3;
			
			if (count >= 3) {
				for (int c = 0; c < N_DICE; c++) {
					score += dice[c];
				}
			}
			
		} else if (category == FOUR_OF_A_KIND) { //four of the same type dice == sum of all dices
			int count = 0;
			
			for (int i = 0; i < N_DICE; i++) {
				for (int j = 0; j < N_DICE; j++) {
					if (dice[i] == dice[j]) {
						count++;
					}
				}
			}
			
			count /= 4;
			
			if (count >= 4) {
				for (int c = 0; c < N_DICE; c++) {
					score += dice[c];
				}
			}
			
		} else if (category == FULL_HOUSE) { //three of one value and two of another == 25 points
			int count = 0;
			
			for (int i = 0; i < N_DICE; i++) {
				for (int j = 0; j < N_DICE; j++) {
					if (dice[i] == dice[j]) {
						count++;
					}
				}
			}
			
			if (count == 13) {
				score = 25;
			}
			
		} else if (category == SMALL_STRAIGHT) { //at least four consequtive numbers == 30 points
			int count = 0;
			
			for (int i = 0; i < 2; i++) {
				if (1 == dice[i + 1] - dice[i] &&
						1 == dice[i + 2] - dice[i + 1] &&
						1 == dice[i + 3] - dice[i + 2]) {
					count++;
				}
			}
			
			if (count >= 1) {
				score = 30;
			}
		} else if (category == LARGE_STRAIGHT) { //five consequtive numbers == 40 points
			int count = 0;
			if (1 == dice[1] - dice[0] &&
					1 == dice[2] - dice[1] &&
					1 == dice[3] - dice[2] &&
					1 == dice[4] - dice[3]) {
				count++;
			}
			if (count == 1) {
				score = 40;
			}
		} else if (category == YAHTZEE) { //all of the same type dice == 50 points
			int count = 0;
			for (int i = 0; i < N_DICE; i++) {
				for (int j = 0; j < N_DICE; j++) {
					if (dice[i] == dice[j]) {
						count++;
					}
				}
			}
			count /= 5;
			if (count == 5) {
				score = 50;
			}
		}
		return score;
	}
		
/* Private instance variables */
	private int[] dice = new int[N_DICE];
	private int[][] categoryList;
	private int[][] trackList;
	private int nPlayers;
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();
}
