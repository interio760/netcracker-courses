package netcracker.fillers;

public class RandomFiller extends Filler {

    @Override
    public void fill(int[] arr, int min, int max) {
        if(min > max) throw new IllegalArgumentException();
        fillRandom(arr, min, max);
        applyOptions(arr);
    }

    protected void fillRandom(int[] arr, int min, int max) {
        for(int i = 0; i < arr.length; i++){
            arr[i] = random.nextInt(min, max);
        }
    }

    @Override
    public String toString() {
        return "Random order" + optionsToString();
    }
}
