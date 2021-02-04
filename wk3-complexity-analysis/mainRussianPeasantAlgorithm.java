import java.util.Scanner;

public class mainRussianPeasantAlgorithm {
	public static void main(String args[]) {
		System.out.println("Please enter the first integer");
		Scanner s = new Scanner(System.in);
		int number1 = s.nextInt();
		System.out.println("Please enter the second integer");
		int number2 = s.nextInt();
		int result = 0;
		if(number1 % 2 == 1) {
			result += number2;
		}
		while(number1 !=1) {
			
			number1 =number1 / 2;
			number2 = number2 *2;
			if(number1 % 2 == 1) {
				result =result+ number2;
			}
		}
		System.out.println(result);
	}
}