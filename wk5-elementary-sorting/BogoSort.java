
public class BogoSort {
	int[] createArray(int size){
		int[] array = new int[size];
		for(int i=0;i<size;i++) {
			array[i]= size-i;
		}
		return array;
	}
	public void sort(int[] i) {
		int counter = 0;
		System.out.println("I'll sort " + i.length + " elements...");
		while(!isSorted(i)) {
			shuffle(i);
			counter++;
		}
		System.out.println("Solution found! (shuffled " + counter + " times)");
	}
	 void printArray(int array[]) 
	    { 
	        int n = array.length; 
	        for (int i=0; i<n; ++i) 
	            System.out.print(array[i]+" "); 
	        System.out.println(); 
	    } 
	 private void shuffle(int[] i) {
			for(int x = 0; x < i.length; ++x) {
				int index1 = (int) (Math.random() * i.length),
					index2 = (int) (Math.random() * i.length);
				int a = i[index1];
				i[index1] = i[index2];
				i[index2] = a;
			}
		}
	 private boolean isSorted(int[] i) {
			for(int x = 0; x < i.length - 1; ++x) {
				if(i[x] > i[x+1]) {
					return false;
				}
			}
			return true;
		}
	 public static void main(String args[]) {
		    BogoSort ob = new BogoSort();
	        int array[] = ob.createArray(100);
	        long startTime = System.nanoTime();
	        ob.sort(array); 
	        long endTime = System.nanoTime();
	        System.out.println("Took "+(endTime - startTime) + " ns");
		
	 }
}
