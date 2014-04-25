/* 
 * Name: Hogwarts.java
 * This class contains .
 */

import acm.program.*;

public class Hogwarts extends ConsoleProgram {
	public void run() {
		bludger(2001);
	}
	private void bludger(int y) {
		int x = y / 1000;
		int z = (x + y);
		x = quaffle(z, y);
		println("bludger: x = " + x + ", y = " + y + ", z = " + z);
	}
	private int quaffle(int x, int y) {
		int z = snitch(x + y, y);
		y /= z;
		println("quaffle: x = " + x + ", y = " + y + ", z = " + z);
		return z;
	}
	private int snitch(int x, int y) {
		y = x / (x % 10);
		println("snitch: x = " + x + ", y = " + y);
		return y;
	}
}
