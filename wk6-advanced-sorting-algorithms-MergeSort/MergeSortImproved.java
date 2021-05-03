import java.util.Arrays;

public class MergeSortImproved {
	//constant which marks when the insertion sort begins
	public static final int K = 5;
	//insertion sort algorithm
	public static void insertionSort(int Array[], int p, int q) {
	    for (int i = p; i < q; i++) {
	        int tempValue = Array[i + 1];
	        int j = i + 1;
	        while (j > p && Array[j - 1] > tempValue) {
	            Array[j] = Array[j - 1];
	            j--;
	        }
	        Array[j] = tempValue;
	    }
	    int[] temporary = Arrays.copyOfRange(Array, p, q +1);
	    Arrays.stream(temporary).forEach(i -> System.out.print(i + " "));
	    System.out.println();
	}

	//merge algorithm for mergesort
	public static void merge(int ArrayInQuestion[], int p, int q, int r) {
	    int n1 = q - p + 1;
	    int n2 = r - q;
	    int[] LeftA = Arrays.copyOfRange(ArrayInQuestion, p, q +1);
	    int[] RightA = Arrays.copyOfRange(ArrayInQuestion, q+1, r +1);
	    int RIDX = 0;
	    int LIDX = 0;
	    for (int i = p; i < r - p + 1; i++) {
	        if (RIDX == n2) {
	        	ArrayInQuestion[i] = LeftA[LIDX];
	            LIDX++;
	        } else if (LIDX == n1) {
	        	ArrayInQuestion[i] = RightA[RIDX];
	            RIDX++;
	        } else if (RightA[RIDX] > LeftA[LIDX]) {
	        	ArrayInQuestion[i] = LeftA[LIDX];
	            LIDX++;
	        } else {
	        	ArrayInQuestion[i] = RightA[RIDX];
	            RIDX++;
	        }
	    }
	}
	//sort algorithm with insertion sort as required
	public static void sort(int A[], int p, int r) {
	    if (r - p > K) {
	        int q = (p + r) / 2;
	        sort(A, p, q);
	        sort(A, q + 1, r);
	        merge(A, p, q, r);
	    } else {
	    	//insertion sort starts when size is equal to 5
	        insertionSort(A, p, r);
	    }
	}
}
