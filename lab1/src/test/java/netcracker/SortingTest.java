package netcracker;

import netcracker.sorters.*;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class SortingTest {

    private List<Sorter> sorterList;

    @Before
    public void init(){
        Sorter arraysSort = new ArraysSort();
        Sorter bteSort = new BubbleSortBtE();
        Sorter etbSort = new BubbleSortEtB();
        Sorter quickSort = new QuickSort();
        sorterList = Arrays.asList(
                arraysSort,
                bteSort,
                etbSort,
                quickSort,
                new HalfDivisionSort(arraysSort),
                new HalfDivisionSort(bteSort),
                new HalfDivisionSort(etbSort),
                new HalfDivisionSort(quickSort)
        );
    }

    @Test
    public void test(){
        int[] arrDef = new int[]{1, -3, 3, 6, -23, 33};
        int[] ascArr = new int[]{-23, -3, 1, 3, 6, 33};
        int[] descArr = new int[]{33, 6, 3, 1, -3, -23};

        for(Sorter sorter : sorterList){
            doSortTest(ascArr, descArr, arrDef, sorter);
        }
    }

    private void doSortTest(int[] expectAsc, int[] expectDesc, int[] original, Sorter sorter){
        int[] arr = Arrays.copyOf(original, original.length);
        sorter.ascSort(arr);
        assertArrayEquals(expectAsc, arr);
        sorter.descSort(arr);
        assertArrayEquals(expectDesc, arr);
    }
}
