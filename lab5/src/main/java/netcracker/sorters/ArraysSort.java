package netcracker.sorters;

import java.util.Arrays;

/**
 * Sorter implementation based on Arrays.sort
 *
 * @author Zakh
 * @see AbstractSorter
 */
@Sorter(name = "Arrays.sort")
public class ArraysSort extends AbstractSorter {

    /**
     * Sorts an array using Arrays.sort
     *
     * @param arr An array to sort
     * @param start Index of the beginning of the interval
     * @param end Index of the end of the interval
     */
    @Override
    protected void ascSort(int[] arr, int start, int end){
        Arrays.sort(arr, start, end + 1);
    }
}
