package netcracker.analyzer;

import netcracker.fillers.Filler;
import netcracker.sorters.Sorter;

public class AnalyzerResult {

    private final Sorter sorter;
    private final Filler filler;
    private final long elapsedTime;

    public AnalyzerResult(Sorter sorter, Filler filler, long elapsedTime) {
        this.sorter = sorter;
        this.filler = filler;
        this.elapsedTime = elapsedTime;
    }

    public Sorter getSorter() {
        return sorter;
    }

    public Filler getFiller() {
        return filler;
    }

    public long getElapsedTime() {
        return elapsedTime;
    }

}
