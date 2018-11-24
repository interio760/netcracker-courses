package netcracker.fillers;

import netcracker.sorters.QuickSort;
import org.junit.Test;

import java.util.Arrays;
import static org.junit.Assert.*;

public class FillerTest {

    @Test(timeout = 1000)
    public void ascFillIllegalArgumentsTest(){
        testAscFill(null, 0, 10);
        testAscFill(new int[0], 0, 10);
        testAscFill(new int[10], 10, 0);
    }

    @Test(timeout = 1000)
    public void ascFillWithLastRandomIllegalArgumentsTest(){
        testAscWithLastRandomFill(null, 0, 10);
        testAscWithLastRandomFill(new int[0], 0, 10);
        testAscWithLastRandomFill(new int[10], 10, 0);
    }

    @Test(timeout = 1000)
    public void descFillIllegalArgumentsTest(){
        testDescFill(null, 0, 10);
        testDescFill(new int[0], 0, 10);
        testDescFill(new int[10], 10, 0);
    }

    @Test(timeout = 1000)
    public void randomIllegalArgumentsTest(){
        testRandomFill(null, 0, 10);
        testRandomFill(new int[0], 0, 10);
        testRandomFill(new int[10], 10, 0);
    }

    @Test(timeout = 1000)
    public void ascFillCorrectnessTest(){
        int[] testArr = new int[10];
        Fillers.ascFill(testArr, -100, 100);
        int[] ascArr = Arrays.copyOf(testArr, testArr.length);
        Arrays.sort(ascArr);
        assertArrayEquals(testArr, ascArr);
    }

    @Test(timeout = 1000)
    public void ascFillWithLastRandomCorrectnessTest(){
        int[] testArr = new int[10];
        Fillers.ascFillLastRandom(testArr, -100, 100);
        int[] ascArr = Arrays.copyOf(testArr, testArr.length - 1);
        Arrays.sort(ascArr);
        int[] testArrWithoutLast = new int[9];
        System.arraycopy(testArr, 0, testArrWithoutLast, 0, testArr.length - 1);
        assertArrayEquals(testArrWithoutLast, ascArr);
    }

    @Test(timeout = 1000)
    public void descFillCorrectnessTest(){
        int[] testArr = new int[10];
        Fillers.descFill(testArr, -100, 100);
        int[] descArr = Arrays.copyOf(testArr, testArr.length);
        new QuickSort().descSort(descArr);
        assertArrayEquals(testArr, descArr);
    }

    private void testAscFill(int[] arr, int min, int max){
        try{
            Fillers.ascFill(arr, min, max);
        }catch (IllegalArgumentException e){
            return;
        }
        fail("" +
                "Fillers.ascFill no exception with arguments: arr="
                + (arr == null ? "null" : "[" + arr.length + "]")
                + " min=" + min + " max=" + max
                + " | Expected: IllegalArgumentException"
        );
    }

    private void testDescFill(int[] arr, int min, int max){
        try{
            Fillers.descFill(arr, min, max);
        }catch (IllegalArgumentException e){
            return;
        }
        fail(
                "Fillers.descFill no exception with arguments: arr="
                + (arr == null ? "null" : "[" + arr.length + "]")
                + " min=" + min + " max=" + max
                + " | Expected: IllegalArgumentException"
        );
    }

    private void testAscWithLastRandomFill(int[] arr, int min, int max){
        try{
            Fillers.ascFillLastRandom(arr, min, max);
        }catch (IllegalArgumentException e){
            return;
        }
        fail(
                "Fillers.ascFillLastRandom no exception with arguments: arr="
                + (arr == null ? "null" : "[" + arr.length + "]")
                + " min=" + min + " max=" + max
                + " | Expected: IllegalArgumentException"
        );
    }

    private void testRandomFill(int[] arr, int min, int max){
        try{
            Fillers.randomFill(arr, min, max);
        }catch (IllegalArgumentException e){
            return;
        }
        fail(
                "Fillers.randomFill no exception with arguments: arr="
                + (arr == null ? "null" : "[" + arr.length + "]")
                + " min=" + min + " max=" + max
                + " | Expected: IllegalArgumentExceptions"
        );
    }

}
