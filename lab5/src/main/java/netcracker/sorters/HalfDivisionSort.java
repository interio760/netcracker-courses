package netcracker.sorters;

import java.util.Arrays;

/**
 * Decorates a sorter by applying half-division method
 *
 * @author Zakh
 * @see AbstractSorter
 */
@Sorter(name = "Half Division sort")
public class HalfDivisionSort extends AbstractSorter {

    private final AbstractSorter sorter;

    /**
     * Constructor taking a sorter to decorate
     *
     * @param sorter Base sorter
     */
    public HalfDivisionSort(AbstractSorter sorter) {
        this.sorter = sorter;
    }

    /**
     * Sorts an array using sorter and half-division method
     *
     * @param arr An array to sort
     * @param start Index of the beginning of the interval
     * @param end Index of the end of the interval
     */
    @Override
    protected void ascSort(int[] arr, int start, int end) {
        if(arr.length == 1) return;

        int[][] arrays = divide(arr, arr.length / 2);
        for(int[] array : arrays){
            sorter.sort(array);
        }
        System.arraycopy(merge(arrays[0], arrays[1]), 0, arr, 0, arr.length - 1);
    }

    /**
     * Returns a sorter
     *
     * @return Sorter
     */
    public AbstractSorter getSorter() {
        return sorter;
    }

    /**
     * Merges two sorted arrays into one sorted array
     *
     * @param arr1 First sorted array
     * @param arr2 Second sorted array
     * @return Merged and sorted array
     */
    private int[] merge(int[] arr1, int[] arr2){
        int a = 0, b = 0;
        int fullLength = arr1.length + arr2.length;
        int[] result = new int[fullLength];

        for (int i = 0; i < fullLength; i++) {
            if (b < arr2.length && a < arr1.length) {
                if (arr1[a] > arr2[b]){
                    result[i] = arr2[b++];
                }
                else {
                    result[i] = arr1[a++];
                }
            } else if (b < arr2.length) {
                result[i] = arr2[b++];
            } else {
                result[i] = arr1[a++];
            }
        }

        return result;
    }

    /**
     * Divides an array into two by the pivot index
     *
     * @param arr An array to divide
     * @param index A pivot index
     * @return A matrix containing two arrays
     */
    private int[][] divide(int[] arr, int index){
        int[][] arrays = new int[2][];
        arrays[0] = Arrays.copyOfRange(arr, 0, index);
        arrays[1] = Arrays.copyOfRange(arr, index, arr.length - 1);
        return arrays;
    }

    @Override
    public String toString() {
        return sorter.getClass().getAnnotation(Sorter.class).name() + " (with half division)";
    }

}
