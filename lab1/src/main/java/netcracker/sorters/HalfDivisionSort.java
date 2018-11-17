package netcracker.sorters;

import java.util.Arrays;

@SortWithDependency
public class HalfDivisionSort implements Sorter {

    private final Sorter sorter;

    public HalfDivisionSort(Sorter sorter) {
        this.sorter = sorter;
    }

    @Override
    public void ascSort(int[] arr) {
        ascSort(arr, 0, arr.length - 1);
    }

    @Override
    public void ascSort(int[] arr, int start, int end) {
        if (arr == null || arr.length == 0) return;
        int[][] arrays = divide(arr, arr.length / 2);
        for(int[] array : arrays){
            sorter.ascSort(array);
        }
        System.arraycopy(merge(arrays[0], arrays[1]), 0, arr, 0, arr.length - 1);
    }

    public Sorter getSorter() {
        return sorter;
    }

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

    private int[][] divide(int[] arr, int index){
        int[][] arrays = new int[2][];
        arrays[0] = Arrays.copyOfRange(arr, 0, index);
        arrays[1] = Arrays.copyOfRange(arr, index, arr.length - 1);
        return arrays;
    }

    @Override
    public String toString() {
        return sorter.toString() + " (with half division)";
    }

}
