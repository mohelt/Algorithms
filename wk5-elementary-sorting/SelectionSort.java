
public class SelectionSort {
	void sort(int array[]) 
	{ 
		int n = array.length; 

		for (int i = 0; i < n-1; i++) 
		{ 
			int minIndex = i; 
			for (int j = i+1; j < n; j++) {
				if (array[j] < array[minIndex]) 
					minIndex = j; 
			}

			int temp = array[minIndex]; 
			array[minIndex] = array[i]; 
			array[i] = temp; 
		} 
	}
	int[] createArray(int size){
		int[] array = new int[size];
		for(int i=0;i<size;i++) {
			array[i]= size-i;
		}
		return array;
	}
	 void printArray(int array[]) 
	    { 
	        int n = array.length; 
	        for (int i=0; i<n; ++i) 
	            System.out.print(array[i]+" "); 
	        System.out.println(); 
	    } 
	 
	 public static void main(String args[]) 
	    { 
	        SelectionSort ob = new SelectionSort();
	        int array[] = ob.createArray(1000000);
	        long startTime = System.nanoTime();
	        ob.sort(array); 
	      long endTime = System.nanoTime();
	      System.out.println("Took "+(endTime - startTime) + " ns"); 
	    } 
}
