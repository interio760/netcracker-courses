package netcracker.sorters;

import netcracker.utils.ArrayUtils;

/**
 * <pre>
 * Abstract class representing an array sorter
 * Provides argument verification and default descending order sorting implementation
 * {@code
 * AbstractSorter sorter = new SorterImpl();
 * sorter.sort(some_array);
 * }
 * </pre>
 *
 * @author Zakh
 */
public abstract class AbstractSorter {

    /**
     * Sorting implementation
     *
     * @param arr An array to sort
     * @param start Index of the beginning of the interval
     * @param end Index of the end of the interval
     */
    protected abstract void ascSort(int[] arr, int start, int end);

    /**
     * <pre>
     * Sorts the whole array in ascending order
     * Default implementation provides additional parameters correctness test
     * </pre>
     *
     * @param arr An array to sort
     * @throws IllegalArgumentException Parameters correctness test failed
     */
    public void sort(int[] arr){
        if(arr == null || arr.length == 0) throw new IllegalArgumentException();
        ascSort(arr, 0, arr.length - 1);
    }

    /**
     * <pre>
     * Sorts an interval in the array in ascending order
     * Default implementation provides additional parameters correctness test
     * </pre>
     * @param arr An array to sort
     * @param start Index of the beginning of the interval
     * @param end Index of the end of the interval
     * @throws IllegalArgumentException Parameters correctness test failed
     */
    public void sort(int[] arr, int start, int end){
        checkAndSort(arr, start, end);
    }

    /**
     * <pre>
     * Sorts the whole array in descending order
     * Default implementation provides additional parameters correctness test
     * Descending order is provided through {@link ArrayUtils#flipArray(int[])}
     * </pre>
     *
     * @param arr An array to sort
     * @throws IllegalArgumentException Parameters correctness test failed
     * @see ArrayUtils#flipArray(int[])
     */
    public void descSort(int[] arr){
        if(arr == null || arr.length == 0) throw new IllegalArgumentException();
        descSort(arr, 0, arr.length - 1);
    }

    /**
     * <pre>
     * Sorts an interval in the array in descending order
     * Default implementation provides additional parameters correctness test
     * Descending order is provided through {@link ArrayUtils#flipArray(int[])}
     * </pre>
     *
     * @param arr An array to sort
     * @param start Index of the beginning of the interval
     * @param end Index of the end of the interval
     * @throws IllegalArgumentException Parameters correctness test failed
     * @see ArrayUtils#flipArray(int[])
     */
    public void descSort(int[] arr, int start, int end){
        checkAndSort(arr, start, end);
        ArrayUtils.flipArray(arr);
    }

    /**
     * Swaps two elements in the array
     *
     * @param arr The array to swap elements in
     * @param i Index of the first element to swap
     * @param j Index of the second element to swap
     */
    protected void swap(int[] arr, int i, int j){
        int swap = arr[i];
        arr[i] = arr[j];
        arr[j] = swap;
    }

    /**
     * Checks arguments for correctness and then sorts in ascending order
     *
     * @param arr The array to sort
     * @param start Index of the beginning of the interval
     * @param end Index of the end of the interval
     * @throws IllegalArgumentException Parameters correctness test failed
     * @see AbstractSorter#ascSort(int[], int, int)
     */
    private void checkAndSort(int[] arr, int start, int end){
        if (
                arr == null || arr.length == 0 ||
                start > end || end > arr.length - 1 ||
                start > arr.length - 1 || start < 0 ||
                end < 0

        ) throw new IllegalArgumentException();

        ascSort(arr, start, end);
    }
}