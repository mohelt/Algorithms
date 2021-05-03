
public class MergeSort {
	//merge two subarray of arrarray	
	//first subarray from l to m
	//second subarray from m+1 to r
	    void merge(int arrInQuestion[], int l, int m, int r)
	    {
	    	//find the size of the two subarrays
	        int n1 = m - l + 1;
	        int n2 = r - m;
	 
	        //create temporary arrays
	        int Left[] = new int[n1];
	        int Right[] = new int[n2];
	 
	        //copy the data into arrays
	        for (int i = 0; i < n1; ++i)
	            Left[i] = arrInQuestion[l + i];
	        for (int j = 0; j < n2; ++j)
	            Right[j] = arrInQuestion[m + 1 + j];
	 
	        //merge the temporary arrays
	 
	        // Initial indexes of first and second array
	        int i = 0, j = 0;
	 
	        // Initial index of merged array
	        int k = l;
	        while (i < n1 && j < n2) {
	            if (Left[i] <= Right[j]) {
	                arrInQuestion[k] = Left[i];
	                i++;
	            }
	            else {
	                arrInQuestion[k] = Right[j];
	                j++;
	            }
	            k++;
	        }
	 
	        // copy over remaining elements left if any
	        while (i < n1) {
	            arrInQuestion[k] = Left[i];
	            i++;
	            k++;
	        }
	 
	        // Copy the remaining elements of Right if any 
	        while (j < n2) {
	            arrInQuestion[k] = Right[j];
	            j++;
	            k++;
	        }
	    }
	 
	    // function that sorts using merge
	    void sort(int arrInQuestion[], int l, int r)
	    {
	        if (l < r) {
	            // Find the mid point
	            int m =l+ (r-l)/2;
	 
	            // Sort first and second halfs
	            sort(arrInQuestion, l, m);
	            sort(arrInQuestion, m + 1, r);
	 
	            // Merge the sorted halfs
	            merge(arrInQuestion, l, m, r);
	        }
	    }
}
