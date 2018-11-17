package netcracker.fillers;

import netcracker.sorters.Sorter;

import java.util.concurrent.ThreadLocalRandom;

public class AscFiller extends Filler {

    private final Sorter sorter;
    private ThreadLocalRandom random = ThreadLocalRandom.current();

    public AscFiller(Sorter sorter) {
        this.sorter = sorter;
    }

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

    @Override
    public String toString() {
        return "Ascending order" + optionsToString();
    }

}
