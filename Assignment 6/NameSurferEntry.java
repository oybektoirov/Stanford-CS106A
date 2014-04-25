import java.util.StringTokenizer;

/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database. Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */


public class NameSurferEntry implements NameSurferConstants {

/**
 * Creates a new NameSurferEntry from a data line as it appears
 * in the data file. Each line begins with the name, which is
 * followed by integers giving the rank of that name for each
 * decade.
 */
	public NameSurferEntry(String line) {
		StringTokenizer tokenizer = new StringTokenizer(line);
		
		//for getName method
		name = tokenizer.nextToken();
		
		//for getRank method
		for (int i = 0; i < 11; i++) {
			int rank = Integer.parseInt(tokenizer.nextToken());
			rankList[i][1] = rank;
		}
		
		//for toString method
		entryLine = line;
	}

/**
 * Returns the name associated with this entry.
 */
	public String getName() {
		return name;
	}

/**
 * Returns the rank associated with an entry for a particular
 * decade.  The decade value is an integer indicating how many
 * decades have passed since the first year in the database,
 * which is given by the constant START_DECADE.  If a name does
 * not appear in a decade, the rank value is 0.
 */
	public int getRank(int decade) {
		int rank = rankList[decade - 1][1];
		return rank;
	}

/**
 * Returns a string that makes it easy to see the value of a
 * NameSurferEntry.
 */
	public String toString() {
		return entryLine;
	}
	
	private String name;
	private String entryLine;
	private int[][] rankList = new int[11][2];
}

