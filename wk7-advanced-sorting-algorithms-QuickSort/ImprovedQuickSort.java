import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
 
class ImprovedQuickSort
{
    // Function that preforms insertion sort on a array
    public static void insertionSort(int[] arrayInQuestion, int lowNumber, int number)
    {
    	//start from the second element
        for (int i = lowNumber + 1; i <= number; i++)
        {
            int value = arrayInQuestion[i];
            int j = i;
 
            //find the j index with in the sorted subset where array ith belongs to
            while (j > lowNumber && arrayInQuestion[j - 1] > value)
            {
                arrayInQuestion[j] = arrayInQuestion[j - 1];
                j--;
            }
 
            arrayInQuestion[j] = value;
        }
    }
    
    public static int partition(int[] a, int lowNumber, int highNumber)
    {
    	//pick the most right element as the pivot from the array
        int pivotValue = a[highNumber];
 
        //elements less than pivots go right of partitionIndex, else left
        int partitionIndex = lowNumber;
        
        //each time theres an element less than or equal to 
        //the pivot partiitonIndex is increments and element is placed before pivot
        for (int i = lowNumber; i < highNumber; i++)
        {
            if (a[i] <= pivotValue)
            {
                int temp = a[i];
                a[i] = a[partitionIndex];
                a[partitionIndex] = temp;
 
                partitionIndex++;
            }
        }
        //swap the partitionIndex with the pivot
        int temp = a[highNumber];
        a[highNumber] = a[partitionIndex];
        a[partitionIndex] = temp;
 
        // return `partitionIndex
        return partitionIndex;
    }
 
    public static void quicksort(int[] a, int lowNumber, int highNumber)
    {
    	//base case
        if (lowNumber >= highNumber) {
            return;
        }
 
        // rearrange elements across pivot
        //rearrange elements across the pivot variable
        int pivot = partition(a, lowNumber, highNumber);
 
        //recursion on array containing elements less than pivot
        quicksort(a, lowNumber, pivot - 1);

        //recursion on array containing elements greater than pivot
        quicksort(a, pivot + 1, highNumber);
    }
 
    public static void optimizedQuicksort(int[] A, int lowNumber, int highNumber)
    {
        while (lowNumber < highNumber)
        {
            // if the size is 10 or smaller switch to insertion sort 
            if (highNumber - lowNumber < 10)
            {
                insertionSort(A, lowNumber, highNumber);
                break;
            }
            else {
                int pivot = partition(A, lowNumber, highNumber);
                
                //tail call optimizations recurison on smaller subarray
                if (pivot - lowNumber < highNumber - pivot)
                {
                    optimizedQuicksort(A, lowNumber, pivot - 1);
                    lowNumber = pivot + 1;
                }
                else {
                    optimizedQuicksort(A, pivot + 1, highNumber);
                    highNumber = pivot - 1;
                }
            }
        }
    }
    // Implementing Fisher–Yates shuffle
    static void shuffleArray(int[] ar)
    {
      // If running on Java 6 or older, use `new Random()` on RHS here
      Random rnd = ThreadLocalRandom.current();
      for (int i = ar.length - 1; i > 0; i--)
      {
        int index = rnd.nextInt(i + 1);
        // Simple swap
        int a = ar[index];
        ar[index] = ar[i];
        ar[i] = a;
      }
    }
    public static void main(String args[])
    {
      int[] solutionArray = { 1, 2, 3, 4, 5, 6, 16, 15, 14, 13, 12, 11 };

      shuffleArray(solutionArray);
      optimizedQuicksort(solutionArray,0,9);
    }
}
