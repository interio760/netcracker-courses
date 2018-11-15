package netcracker.sorters;

import java.util.Arrays;

public class ArraysSort implements Sorter {

    @Override
    public void ascSort(int[] arr, int start, int end){
        if (arr == null || arr.length == 0) return;
        Arrays.sort(arr, start, end + 1);
    }

}
