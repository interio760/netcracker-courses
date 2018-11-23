package netcracker.sorters;

public abstract class AbstractSorter {

    protected abstract void ascSort(int[] arr, int start, int end);

    public void sort(int[] arr){
        if(arr == null || arr.length == 0) throw new IllegalArgumentException();
        ascSort(arr, 0, arr.length - 1);
    }

    public void sort(int[] arr, int start, int end){
        if (
                arr == null || arr.length == 0 ||
                start > end || end > arr.length - 1 ||
                start > arr.length - 1 || start < 0 || end < 0

        ) throw new IllegalArgumentException();

        ascSort(arr, start, end);
    }

    public void descSort(int[] arr){
        if(arr == null || arr.length == 0) throw new IllegalArgumentException();
        descSort(arr, 0, arr.length - 1);
    }

    public void descSort(int[] arr, int start, int end){
        if (
                arr == null || arr.length == 0 ||
                start > end || end > arr.length - 1 ||
                start > arr.length - 1 || start < 0 || end < 0

        ) throw new IllegalArgumentException();

        ascSort(arr, start, end);
        for(int i = 0; i < arr.length / 2; i++){
            int temp = arr[i];
            arr[i] = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = temp;
        }
    }

    protected void swap(int[] arr, int i, int j){
        int swap = arr[i];
        arr[i] = arr[j];
        arr[j] = swap;
    }
}