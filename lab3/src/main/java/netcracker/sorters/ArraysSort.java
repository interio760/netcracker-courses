package netcracker.sorters;

import java.util.Arrays;

public class ArraysSort extends AbstractSorter {

    @Override
    public void ascSort(int[] arr, int start, int end){
        if (arr == null || arr.length == 0) return;
        Arrays.sort(arr, start, end + 1);
    }

    @Override
    public String toString() {
        return "Arrays.sort";
    }

}
