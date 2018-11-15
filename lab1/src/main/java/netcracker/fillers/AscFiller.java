package netcracker.fillers;

import netcracker.sorters.Sorter;

public class AscFiller extends Filler {

    public AscFiller(Sorter sorter) {
        this.sorter = sorter;
    }

    private final Sorter sorter;

    @Override
    public void fill(int[] arr, int min, int max) {
        if(arr == null) return;
        if(min > max) throw new IllegalArgumentException();
        int limit = max - min + 1;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = min + random.nextInt(limit);
        }
        sorter.ascSort(arr);
        applyOptions(arr);
    }

}
