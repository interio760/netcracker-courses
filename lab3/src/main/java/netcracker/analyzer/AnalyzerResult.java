package netcracker.analyzer;

import netcracker.sorters.AbstractSorter;
import java.util.Map;

public class AnalyzerResult {

    private final AbstractSorter sorter;
    private final String filler;
    private final Map<Integer, Long> elapsedTime;

    public AnalyzerResult(AbstractSorter sorter, String filler, Map<Integer, Long> elapsedTime) {
        this.sorter = sorter;
        this.filler = filler;
        this.elapsedTime = elapsedTime;
    }

    public AbstractSorter getSorter() {
        return sorter;
    }

    public String getFiller() {
        return filler;
    }

    public Map<Integer, Long> getElapsedTime() {
        return elapsedTime;
    }

}
