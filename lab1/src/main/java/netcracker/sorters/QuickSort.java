package netcracker.sorters;

public class QuickSort implements Sorter {

    @Override
    public void ascSort(int[] arr, int start, int end) {
        if (arr == null || arr.length == 0) return;
        quickSort(arr, start, end);
    }

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
