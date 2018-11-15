package netcracker.sorters;

public interface Sorter {

    void ascSort(int[] arr, int start, int end);

    default void ascSort(int[] arr){
        ascSort(arr, 0, arr.length - 1);
    }

    default void descSort(int[] arr){
        ascSort(arr);
        for(int i = 0; i < arr.length / 2; i++){
            int temp = arr[i];
            arr[i] = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = temp;
        }
    }

    default void swap(int[] arr, int i, int j){
        int swap = arr[i];
        arr[i] = arr[j];
        arr[j] = swap;
    }
}