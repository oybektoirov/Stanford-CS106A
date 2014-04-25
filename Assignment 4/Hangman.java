/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;

public class Hangman extends ConsoleProgram {
	
	private RandomGenerator rgen = RandomGenerator.getInstance();	
	private int guesses = 8;
	private HangmanLexicon lexicon = new HangmanLexicon();
	private String randomWord = lexicon.getWord(rgen.nextInt(0, 9));
	private HangmanCanvas canvas;
	String answer = "";
	String guessWord = "";
	char ch1;
	
	public void init() {
    	canvas = new HangmanCanvas();
    	add(canvas);
	}
	
	public void run() {
		canvas.reset();
    	println("Welcome to Hangman!");
    	play();

	}
	
	private void play() {
		
		println("");
       	println("The word now looks like this: " + minusSign());
   		println("You have " + guesses + " guesses left.");
       	answer = readLine("Your guess: ");
       	answer = answer.toUpperCase();
       	if (answer.length() == 1) {
       		guessCounter();
       		ch1 = answer.charAt(0);
       	} else if (answer.length() > 1) {
       		println("You cannot enter more than one letter.");
       	} else {
       		println("You must enter a letter.");
       	}
       	
     	while (!gameOver()) {
     		
   			println("");
       		println("The word now looks like this: " + openUp(ch1));
       		if (guessWord.equals(randomWord)) break;
   			println("You have " + guesses + " guesses left.");
       		answer = readLine("Your guess: ");
       		answer = answer.toUpperCase();
       		if (answer.length() == 1) {
           		guessCounter();
           		ch1 = answer.charAt(0);
           	} else if (answer.length() > 1) {
           		println("You cannot enter more than one letter.");
           	} else {
           		println("You must enter a letter.");
           	}
    	}
    	printGameOver();
	}
    
	private String minusSign() {
		for (int j = 0; j < randomWord.length(); j++) {
			guessWord += "-";
		}
		return guessWord;
	}
	
	private String openUp(char ch1) {
		for (int j = 0; j < randomWord.length(); j++) {
			char ch2 = randomWord.charAt(j);
			if (ch1 == ch2) {
				guessWord = guessWord.substring(0, j) + ch1 + guessWord.substring(j + 1);
			}
		}
		canvas.displayWord(guessWord);
		return guessWord;
	}
	
	private void guessCounter() {
		int count = 0;
		char ch1 = answer.charAt(0);
		for (int i = 0; i < randomWord.length(); i++) {
			char ch2 = randomWord.charAt(i);
			if (ch1 == ch2) {
    			count++;
    			if (count == 1) {
    				println("Your guess is correct!");
    			}
     		}
		}
		if (count == 0) {
			canvas.noteIncorrectGuess(ch1);
			guesses -= 1;
			println("Your guess is incorrect!");
		}
	}
	
	private boolean gameOver() {
		return (guesses == 0) ||
				guessWord.equals(randomWord);
	}
	
	private void printGameOver() {
		if (guesses == 0) {
			println("");
	    	println("You are completely hung.");
	    	println("The word was: " + randomWord);
	    	println("You lose!");
		} else {
			println("");
			println("You guessed the word: " + randomWord);
			println("You win!");
		}
		
	}
}
