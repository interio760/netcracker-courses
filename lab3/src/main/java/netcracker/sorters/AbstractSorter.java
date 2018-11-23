package netcracker.sorters;

public abstract class AbstractSorter {

    public abstract void ascSort(int[] arr, int start, int end);

    public void sort(int[] arr){
        if(arr == null) throw new IllegalArgumentException();
        ascSort(arr, 0, arr.length - 1);
    }

    public void descSort(int[] arr){
        if(arr == null) throw new IllegalArgumentException();
        descSort(arr, 0, arr.length - 1);
    }

    public void descSort(int[] arr, int start, int end){
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