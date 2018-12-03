package netcracker.sorters;

/**
 * Bubble sort implementation, where the element goes from the beginning to the end
 *
 * @author Zakh
 * @see BubbleSorter
 */
@Sorter(name = "Bubble sort (bte)")
public class BubbleSortBtE extends BubbleSorter{

    /**
     * Sorts an array using bubble sort algorithm, where the element goes from the beginning to the end
     *
     * @param arr An array to sort
     * @param start Index of the beginning of the interval
     * @param end Index of the end of the interval
     */
    @Override
    protected void ascSort(int[] arr, int start, int end) {
        for(int i = 0; i < end + 1; i++){
            boolean flag = false;
            for(int j = 0; j < (end - i); j++){
                if(arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    flag = true;
                }
            }
            if(!flag) break;
        }
    }
}
