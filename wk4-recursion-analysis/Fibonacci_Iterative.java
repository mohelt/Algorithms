

public class Fibonacci_Iterative {

	//iterative function for fibonacci sequence
	static int fibonacciIterative(int n) {
		
		//base case if n is less than or equal to one
		if (n <=1) {
			return 1;
		}
		
		//variables
		int fib =1;
		int prevFib = 1;
		
		//iterating through the fibonacci sequence
		for(int i = 2;i<n;i++) {
			
			//creating a temp variable and using it to store previous value
			int temp = fib;
			fib = fib + prevFib;
			prevFib = temp;
		}
		//return the value of the nth fibonacci term
		return fib;
	}
	
	public static void main(String[] args) {
	int n = 9;
	System.out.println(fibonacciIterative(n));
	}

}

