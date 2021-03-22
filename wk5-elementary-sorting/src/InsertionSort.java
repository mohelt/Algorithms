
public class InsertionSort {
	public void sort(int array[]) {  
		int size = array.length;  
		for (int j = 1; j < size; j++) {  
			int key = array[j];  
			int i = j-1;  
			while ( (i > -1) && ( array [i] > key ) ) {  
				array [i+1] = array [i];  
				i--;  
			}  
			array[i+1] = key;  
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
	public static void main(String[] args) {

		InsertionSort ob = new InsertionSort();
		int array[] = ob.createArray(100000);
		long startTime = System.nanoTime();
		ob.sort(array); 
		long endTime = System.nanoTime();
		System.out.println("Took "+(endTime - startTime) + " ns");
	}

}
