package netcracker.sorters;

import java.util.Arrays;

public class ArraysSort extends AbstractSorter {

    @Override
    protected void ascSort(int[] arr, int start, int end){
        Arrays.sort(arr, start, end + 1);
    }

    @Override
    public String toString() {
        return "Arrays.sort";
    }

}
