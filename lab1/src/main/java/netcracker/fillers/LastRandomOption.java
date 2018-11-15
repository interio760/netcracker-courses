package netcracker.fillers;

import java.util.concurrent.ThreadLocalRandom;

public class LastRandomOption extends Option {

    private int min;
    private int max;

    public LastRandomOption(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    protected void execute(int[] arr) {
        arr[arr.length - 1] = ThreadLocalRandom.current().nextInt(min, max);
    }

    @Override
    protected int getPriority() {
        return LOW_PRIORITY;
    }

}
