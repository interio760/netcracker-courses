package netcracker.sorters;

/**
 * Sorter implementation based on quicksort algorithm
 *
 * @author Zakh
 * @see AbstractSorter
 */
public class QuickSort extends AbstractSorter {

    /**
     * Sorts an array using {@link QuickSort#quickSort(int[], int, int)}
     *
     * @param arr An array to sort
     * @param start Index of the beginning of the interval
     * @param end Index of the end of the interval
     */
    @Override
    protected void ascSort(int[] arr, int start, int end) {
        quickSort(arr, start, end);
    }

    /**
     * Sorts an array recursively using quicksort algorithm
     *
     * @param arr An array to sort
     * @param start Index of the beginning of the interval
     * @param end Index of the end of the interval
     */
    private void quickSort(int[] arr, int start, int end) {
        if (start >= end) return;

        int mid = start + (end - start) / 2;
        int pivot = arr[mid];
        int i = start, j = end;

        while (i <= j) {
            while (arr[i] < pivot) i++;
            while (arr[j] > pivot) j--;

            if (i <= j) {
                swap(arr, i, j);
                i++;
                j--;
            }
        }

        if (start < j) quickSort(arr, start, j);

        if (end > i) quickSort(arr, i, end);
    }

    @Override
    public String toString() {
        return "Quicksort";
    }

}
