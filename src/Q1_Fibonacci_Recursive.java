
public class Q1_Fibonacci_Recursive {
	
	static int fibonacciRecursive(int n) {
		//base case if n <=1 return the value of n
		if (n <=1) {
			return n;
		}
		// recursively figuring out the nth term of the fibonacci sequence
		//as the nth term is equal to the two term before it summed up
		return  fibonacciRecursive(n-1)+ fibonacciRecursive(n-2);
	}
	
	public static void main(String[] args) {
	int n = 9;
	System.out.println(fibonacciRecursive(n));
	}

}
