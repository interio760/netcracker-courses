package netcracker.analyzer;

import netcracker.sorters.AbstractSorter;

import java.util.List;
import java.util.Map;

/**
 * Abstract class that provides a {@link List} of sorters and a {@link Map} of Filler name - Map (Array size - Array)
 *
 * @author Zakh
 */
public abstract class Provider {

    /**
     * Returns a List of sorters
     *
     * @return List of sorters
     */
    protected abstract List<AbstractSorter> getSorters();

    /**
     * Returns a Map of Filler name - Map (Array size - Array)
     *
     * @param sizes An array of array sizes for testing
     * @return Map of Filler name - Map (Array size - Array)
     */
    protected abstract Map<String, Map<Integer, int[]>> getSuite(int ... sizes);

}
