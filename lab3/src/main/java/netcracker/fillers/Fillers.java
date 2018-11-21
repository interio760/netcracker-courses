package netcracker.fillers;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Fillers {

    @Filler(name = "Asc filler")
    public static void ascFill(int[] arr, int min, int max) {
        randomFill(arr, min, max);
        Arrays.sort(arr);
    }

    @Filler(name = "Asc filler with last random")
    public static void ascFillLastRandom(int[] arr, int min, int max) {
        ascFill(arr, min, max);
        arr[arr.length - 1] = ThreadLocalRandom.current().nextInt(min, max);
    }

    @Filler(name = "Desc filler")
    public static void descFill(int[] arr, int min, int max) {
        ascFill(arr, min, max);
        for(int i = 0; i < arr.length / 2; i++)
        {
            int temp = arr[i];
            arr[i] = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = temp;
        }
    }

    @Filler(name = "Random filler")
    public static void randomFill(int[] arr, int min, int max){
        if(arr == null) return;
        if(min > max) throw new IllegalArgumentException();
        int limit = max - min + 1;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = min + ThreadLocalRandom.current().nextInt(limit);
        }
    }

}
