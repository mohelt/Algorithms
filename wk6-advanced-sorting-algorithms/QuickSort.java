// Java QuickSort from the practical

 
public class QuickSort{
     
// useful function to swap two elements together
static void swapElements(int[] arrayIn, int i, int j)
{
    int temporary = arrayIn[i];
    arrayIn[i] = arrayIn[j];
    arrayIn[j] = temporary;
}
 
/
//function takes last element to be used as pivot, then places the pivot in its correct position 
//in the array places all the smaller than pivot to the left and greater to the right
static int partitionIt(int[] arr, int lowNumber, int highNumber)
{
     
    // pivot
    int pivotVariable = arr[highNumber];
 
    //index is of the smaller element and indicates the righter position of the pivot that was found so far
    int i = (lowNumber - 1);
 
    for(int j = lowNumber; j <= highNumber - 1; j++)
    {
         
        // If current element is smaller
        // than the pivot
        if (arr[j] < pivotVariable)
        {
             
            // Increment index of
            // smaller element
            i++;
            swapElements(arr, i, j);
        }
    }
    swapElements(arr, i + 1, highNumber);
    return (i + 1);
}
 

//main sorting function
//sorts array to be sorted, starting index and ending index
static void quickSortAlgorithm(int[] array, int lowNumber, int highNumber)
{
    if (lowNumber < highNumber)
    {
         
        // pi is the partitioning index in question 
        int partitioningIndex = partitionIt(array, lowNumber, highNumber);
 
        //sort elements before the partition and after
        quickSortAlgorithm(array, lowNumber, partitioningIndex - 1);
        quickSortAlgorithm(array, partitioningIndex + 1, highNumber);
    }
}
