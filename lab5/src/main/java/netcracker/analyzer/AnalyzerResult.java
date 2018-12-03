package netcracker.analyzer;

import netcracker.sorters.AbstractSorter;
import java.util.Map;

/**
 * Class representing {@link Analyzer} results
 *
 * @author Zakh
 * @see Analyzer
 */
public class AnalyzerResult {

    private final AbstractSorter sorter;
    private final String filler;
    private final Map<Integer, Long> elapsedTime;

    /**
     * Constructor that takes analyzer result and context
     *
     * @param sorter Sorted used in test
     * @param filler Filler used in test
     * @param elapsedTime Map (Test array size - Elapsed time)
     */
    public AnalyzerResult(AbstractSorter sorter, String filler, Map<Integer, Long> elapsedTime) {
        this.sorter = sorter;
        this.filler = filler;
        this.elapsedTime = elapsedTime;
    }

    /**
     * @return {@link AbstractSorter} used in testing
     */
    public AbstractSorter getSorter() {
        return sorter;
    }

    /**
     * @return A name of filler used in testing
     */
    public String getFiller() {
        return filler;
    }

    /**
     * @return {@link Map} Array size - Array
     */
    public Map<Integer, Long> getElapsedTime() {
        return elapsedTime;
    }

}
