package netcracker.analyzer;

import java.util.List;

/**
 * Interface that defines array analyzer methods
 *
 * @author Zakh
 */
public interface Analyzer {

    /**
     * Tests sorters time usage using different arrays
     *
     * @param sizes An array of array sizes for testing
     * @return {@link List} of {@link AnalyzerResult}
     */
    List<AnalyzerResult> analyzeDifficulty(int ... sizes);

}
