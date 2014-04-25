/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */
import acm.program.*;
import acm.util.*;
import java.util.*;
import java.io.*;

public class HangmanLexicon extends ConsoleProgram {
	ArrayList<String> strlist = new ArrayList<String>();
	
	// This is the HangmanLexicon constructor
	public void HangmanLexicon() {	
		try {
			BufferedReader rd = new BufferedReader(new FileReader("ShorterLexicon.txt"));
			while (true) {
				String rdline = rd.readLine();
				if (rdline == null) break;
				strlist.add(rdline);
			}
			rd.close();
		} catch (IOException ex){
			throw new ErrorException(ex);
		}		
	}


/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		return strlist.size();
	}

/** Returns the word at the specified index. */
	public String getWord(int index) {
		HangmanLexicon();
		String word = "";
		if (strlist.get(index) != null) {
			word = strlist.get(index);
		}
		return word;
	}
}
