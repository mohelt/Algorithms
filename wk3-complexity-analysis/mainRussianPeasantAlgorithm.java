import java.math.BigInteger;
import java.util.Scanner;

public class mainRussianPeasantAlgorithm {
	public static void main(String args[]) {

		//scanner to accept user data
		Scanner s = new Scanner(System.in);

		//text to tell user to input the first number
		System.out.println("Please enter the first integer");

		//accept values for bigIntegers
		BigInteger number1 = s.nextBigInteger();
		System.out.println("Please enter the second integer");
		BigInteger number2 = s.nextBigInteger();


		//some variables to allow for russian peasant algorithm in BigInteger
		BigInteger result = new BigInteger("0");
		BigInteger mod2 = new BigInteger("2");
		BigInteger mod2answer = number1.mod(mod2);
		BigInteger one = new BigInteger("1");

		//first part of the russian peasant algorithm
		//if the first number is odd, add the second number to the result
		if(number1.mod(mod2).equals(one)) {
			result =result.add(number2);
		}
		//while the first number != one divide number1 by two
		//and multiply number2 by two
		while(!(number1.equals(one))) {
			number1 = number1.divide(mod2);
			number2= number2.multiply(mod2);
			if(number1.mod(mod2).equals(one)) {
				//if the first number is odd, add the second number to the result
				result =result.add(number2);
			}
		}
		System.out.println(result);
	}
}