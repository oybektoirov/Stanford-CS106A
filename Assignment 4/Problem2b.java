import acm.program.*;

public class Problem2b extends ConsoleProgram {
	public void run() {
		int num1 = 2;
		int num2 = 13;
		println("The 1st number is: " + Mystery(num1, 6));
		println("The 2nd number is: " + Mystery(num2 % 5, 1 + num1 * 2));
	}
	private int Mystery(int num1, int num2) {
		num1 = Unknown(num1, num2);
		num2 = Unknown(num2, num1);
		return(num2);
	}
	private int Unknown(int num1, int num2) {
		int num3 = num1 + num2;
		num2 += num3 * 2;
		return(num2);
	}
}
