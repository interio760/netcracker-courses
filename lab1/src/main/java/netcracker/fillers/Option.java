package netcracker.fillers;

public abstract class Option {

    protected final static int LOW_PRIORITY = 2;
    protected final static int MEDIUM_PRIORITY = 1;
    protected final static int HIGH_PRIORITY = 0;

    protected abstract void execute(int[] arr);

    protected abstract int getPriority();

}
