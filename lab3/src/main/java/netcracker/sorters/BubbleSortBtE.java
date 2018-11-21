package netcracker.sorters;

public class BubbleSortBtE extends BubbleSorter{

    @Override
    public void ascSort(int[] arr, int start, int end) {
        if (arr == null || arr.length == 0) return;
        for(int i = 0; i < end + 1; i++){
            boolean flag = false;
            for(int j = 0; j < (end - i); j++){
                if(arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    flag = true;
                }
            }
            if(!flag) break;
        }
    }

    @Override
    public String toString() {
        return "Bubble sort (bte)";
    }

}