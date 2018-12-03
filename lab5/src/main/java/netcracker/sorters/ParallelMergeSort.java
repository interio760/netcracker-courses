package netcracker.sorters;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * Parallel merge sort
 *
 * @author Zakh
 * @see AbstractSorter
 */
@Sorter(name = "Parallel merge sort")
public class ParallelMergeSort extends AbstractSorter {

    private static class ParallelMergeSortWorker extends RecursiveAction {
        private final int[] arr1;
        private final int[] arr2;
        private final int start;
        private final int end;

        ParallelMergeSortWorker(int[] arr1, int start, int end) {
            this.arr1 = arr1;
            this.arr2 = new int[arr1.length];
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute() {
            if (start < end) {
                int pivot = (start + end) / 2;
                ParallelMergeSortWorker left = new ParallelMergeSortWorker(arr1, start, pivot);
                ParallelMergeSortWorker right = new ParallelMergeSortWorker(arr1, pivot + 1, end);
                invokeAll(left, right);
                merge(arr1, arr2, start, pivot, end);
            }
        }
    }

    /**
     * Sorts an array using parallel merge sort
     *
     * @param arr An array to sort
     * @param start Index of the beginning of the interval
     * @param end Index of the end of the interval
     */
    @Override
    protected void ascSort(int[] arr, int start, int end) {
        if(arr.length == 1) return;

        ForkJoinPool forkJoinPool =
                new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        forkJoinPool.invoke(new ParallelMergeSortWorker(arr, 0, arr.length - 1));
    }

    protected static void merge(int[] arr1, int[] arr2, int start, int pivot, int end) {
        if (end + 1 - start >= 0) System.arraycopy(arr1, start, arr2, start, end + 1 - start);

        int left = start;
        int right = pivot + 1;
        int current = start;

        while (left <= pivot && right <= end) {
            if (arr2[left] <= arr2[right]) {
                arr1[current] = arr2[left++];
            } else {
                arr1[current] = arr2[right++];
            }
            current++;
        }

        while (left <= pivot) {
            arr1[current++] = arr2[left++];
        }
    }

}
