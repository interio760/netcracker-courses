package netcracker.utils;

/**
 * A class containing useful utilites to work with arrays
 *
 * @author Zakh
 */
public class ArrayUtils {

    /**
     * Flips elements in array
     *
     * @param arr An array to flip
     */
    public static void flipArray(int[] arr){
        for(int i = 0; i < arr.length / 2; i++){
            int temp = arr[i];
            arr[i] = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = temp;
        }
    }

}
