package netcracker.sorters;

import java.util.Arrays;
import java.util.List;

public class Sorters {

    public static List<AbstractSorter> getSortersForTesting(){
        AbstractSorter arraysSort = new ArraysSort();
        AbstractSorter bteSort = new BubbleSortBtE();
        AbstractSorter etbSort = new BubbleSortEtB();
        AbstractSorter quickSort = new QuickSort();
        AbstractSorter parallelMergeSort = new ParallelMergeSort();

        return Arrays.asList(
                arraysSort,
                bteSort,
                etbSort,
                quickSort,
                parallelMergeSort,
                new HalfDivisionSort(arraysSort),
                new HalfDivisionSort(bteSort),
                new HalfDivisionSort(etbSort),
                new HalfDivisionSort(quickSort),
                new HalfDivisionSort(parallelMergeSort)
        );
    }

}
