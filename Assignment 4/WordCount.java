import acm.program.*;
import acm.util.*;
import java.io.*;
import java.util.*;

public class WordCount extends ConsoleProgram {
	
	ArrayList<String> strlist = new ArrayList<String>();
	int lines = 0;
	int words = 0;
	int chars = 0;
	
	//checks whether the file exists
	private BufferedReader openFile(String prompt) {
		BufferedReader rd = null;
		while (rd == null) {
			try {
				String filename = readLine(prompt);
				rd = new BufferedReader(new FileReader(filename));
			} catch (IOException ex) {
				println("That file doesn't exist. Try again.");
			}
		}
		return rd;
	}
	
	//filters the words and creates an arraylist consisting clean words
	private void filterWords(String dirtyline) {
		StringTokenizer tokenizer = new StringTokenizer(dirtyline);
		String cleanline = "";
		for (int i = 0; tokenizer.hasMoreTokens(); i++) {
			String token = tokenizer.nextToken();
			for (int j = 0; j < token.length(); j++) {
				char ch = token.charAt(j);
				if (Character.isLetterOrDigit(ch)) {
					cleanline += ch;
				}
				if (ch == '\'') {
					cleanline += " ";
				}
			}
			cleanline += " ";
		}
		strlist.add(cleanline);
	}
	
	//counts the words(i.e letters or digits) in the arraylist
	private void countWords() {
		for (int i = 0; i < strlist.size(); i++) {
			StringTokenizer tokenizer = new StringTokenizer(strlist.get(i));
			words += tokenizer.countTokens();
		}
	}
	
	public void run() {
		BufferedReader rd = openFile("File: ");
		
		try {
			while (true) {
				String rdline = rd.readLine();
				if (rdline == null) break;
				lines++;
				filterWords(rdline);
				chars += rdline.length();
			}
			rd.close();
		} catch (IOException ex){
			throw new ErrorException(ex);
		}
		
		countWords();
		println("Lines: " + lines);
		println("Words: " + words);
		println("Chars: " + chars);
	}
}
