import java.util.*;
import acm.program.*;
import java.io.*;
import acm.util.*;

public class Histogram extends ConsoleProgram {

	private int[] score = new int[11]; 
	
	private void printScoreList() {
		String score1 = "";
		for (int i = 0; i < score[0]; i++) {
			score1 += "*";
		}
		
		String score2 = "";
		for (int i = 0; i < score[1]; i++) {
			score2 += "*";
		}
		
		String score3 = "";
		for (int i = 0; i < score[2]; i++) {
			score3 += "*";
		}
		
		String score4 = "";
		for (int i = 0; i < score[3]; i++) {
			score4 += "*";
		}
		
		String score5 = "";
		for (int i = 0; i < score[4]; i++) {
			score5 += "*";
		}
		
		String score6 = "";
		for (int i = 0; i < score[5]; i++) {
			score6 += "*";
		}
		
		String score7 = "";
		for (int i = 0; i < score[6]; i++) {
			score7 += "*";
		}
		
		String score8 = "";
		for (int i = 0; i < score[7]; i++) {
			score8 += "*";
		}
		
		String score9 = "";
		for (int i = 0; i < score[8]; i++) {
			score9 += "*";
		}
		
		String score10 = "";
		for (int i = 0; i < score[9]; i++) {
			score10 += "*";
		}
		
		String score11 = "";
		for (int i = 0; i < score[10]; i++) {
			score11 += "*";
		}
		println("00-09: " + score1);
		println("10-19: " + score2);
		println("20-29: " + score3);
		println("30-39: " + score4);
		println("40-49: " + score5);
		println("50-59: " + score6);
		println("60-69: " + score7);
		println("70-79: " + score8);
		println("80-89: " + score9);
		println("90-99: " + score10);
		println("  100: " + score11);
	}
	
	public void run() {
		try {
			BufferedReader rd = new BufferedReader(new FileReader("MidtermScores.txt"));
			
			while (true) {
				String line = rd.readLine();
				if (line == null) break;
				int a;
				try {
					a = Integer.parseInt(line);
				} catch (NumberFormatException ex) {
					throw new ErrorException(ex);
				}
				
				if (a >= 0 && a <= 9) {
					score[0] += 1;
				} else if (a >= 10 && a <= 19) {
					score[1] += 1;
				} else if (a >= 20 && a <= 29) {
					score[2] += 1;
				} else if (a >= 30 && a <= 39) {
					score[3] += 1;
				} else if (a >= 40 && a <= 49) {
					score[4] += 1;
				} else if (a >= 50 && a <= 59) {
					score[5] += 1;
				} else if (a >= 60 && a <= 69) {
					score[6] += 1;
				} else if (a >= 70 && a <= 79) {
					score[7] += 1;
				} else if (a >= 80 && a <= 89) {
					score[8] += 1;
				} else if (a >= 90 && a <= 99) {
					score[9] += 1;
				} else {
					score[10] += 1;
				}
			}			
			rd.close();
			printScoreList();
		} catch (IOException ex) {
			println("Bad file. Try again.");
		}

	}
}
