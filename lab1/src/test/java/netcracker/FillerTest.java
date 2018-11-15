package netcracker;

import netcracker.fillers.*;
import netcracker.sorters.QuickSort;
import netcracker.sorters.Sorter;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

public class FillerTest {

    private List<Filler> fillerList = new ArrayList<>();
    private Sorter sorter;

    @Before
    public void init(){
        sorter = new QuickSort();

        Filler sortedAsc = new AscFiller(sorter);
        Filler sortedDesc = new DescFiller(sorter);

        fillerList.add(sortedAsc);
        fillerList.add(sortedDesc);
    }

    @Test
    public void test(){
        final int MIN = -10;
        final int MAX = 10;
        final int SIZE = 10;

        for(Filler filler : fillerList){
            int[] testArray = new int[SIZE];
            filler.fill(testArray,  MIN, MAX);
            int[] properArray;
            properArray = Arrays.copyOf(testArray, testArray.length);

            if(filler instanceof AscFiller && filler.getOptions().isEmpty()){
                Arrays.sort(properArray);
                assertArrayEquals(testArray, properArray);
                assertTrue(properArray[0] >= MIN);
                assertTrue(properArray[properArray.length - 1] <= MAX);
            }

            if(filler instanceof DescFiller && filler.getOptions().isEmpty()){
                sorter.descSort(properArray);
                assertArrayEquals(testArray, properArray);
                assertTrue(properArray[0] <= MAX);
                assertTrue(properArray[properArray.length - 1] >= MIN);
            }

        }
    }

}
