package netcracker.fillers;

import netcracker.utils.ArrayUtils;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Class containing static methods for filling an array
 *
 * @author Zakh
 */
public class Fillers {

    /**
     * <pre>
     * Fills an array in ascending order
     * Fills an array using {@link Fillers#randomFill(int[], int, int)}
     * Ensures it's ascending order sorting it with {@link Arrays#sort(int[])}
     * </pre>
     *
     * @param arr An array to fill
     * @param min Minimum element value
     * @param max Maximum element value
     * @throws IllegalArgumentException Parameters correctness test failed
     */
    @Filler(name = "Asc filler")
    public static void ascFill(int[] arr, int min, int max) {
        randomFill(arr, min, max);
        Arrays.sort(arr);
    }

    /**
     * <pre>
     * Fills an array in ascending order with last element being random
     * Fills an array using {@link Fillers#ascFill(int[], int, int)}
     * Puts a random int in the last element of an array
     * </pre>
     *
     * @param arr An array to fill
     * @param min Minimum element value
     * @param max Maximum element value
     * @throws IllegalArgumentException Parameters correctness test failed
     */
    @Filler(name = "Asc filler with last random")
    public static void ascFillLastRandom(int[] arr, int min, int max) {
        ascFill(arr, min, max);
        arr[arr.length - 1] = ThreadLocalRandom.current().nextInt(min, max);
    }

    /**
     * <pre>
     * Fills an array in descending order
     * Fills an array using {@link Fillers#ascFill(int[], int, int)}
     * Ensures it descending order using {@link ArrayUtils#flipArray(int[])}
     * </pre>
     *
     * @param arr An array to fill
     * @param min Minimum element value
     * @param max Maximum element value
     * @throws IllegalArgumentException Parameters correctness test failed
     * @see ArrayUtils#flipArray(int[])
     */
    @Filler(name = "Desc filler")
    public static void descFill(int[] arr, int min, int max) {
        ascFill(arr, min, max);
        ArrayUtils.flipArray(arr);
    }

    /**
     * Fills an array in random order
     * <br>
     * Checks arguments for correctness
     *
     * @param arr An array to fill
     * @param min Minimum element value
     * @param max Maximum element value
     * @throws IllegalArgumentException Parameters correctness test failed
     */
    @Filler(name = "Random filler")
    public static void randomFill(int[] arr, int min, int max){
        if(arr == null || arr.length == 0 || min > max) throw new IllegalArgumentException();
        int limit = max - min + 1;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = min + ThreadLocalRandom.current().nextInt(limit);
        }
    }

}
