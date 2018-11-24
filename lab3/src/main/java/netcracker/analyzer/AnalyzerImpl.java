package netcracker.analyzer;

import netcracker.sorters.AbstractSorter;

import java.util.*;

/**
 * Implementation of {@link Analyzer} that uses {@link Provider} to get {@link AbstractSorter} and Fillers lists
 * <br>
 *
 * <pre>{@code
 * Analyzer analyzer = new AnalyzerImpl(new ReflectionProvider("path_to_sorters_package", "path_to_fillers_package");
 * List<AnalyzerResult> = analyzer.analyzeDifficulty(10000, 100000);
 * }</pre>
 *
 * @author Zakh
 * @see Provider
 * @see AbstractSorter
 * @see netcracker.fillers.Fillers
 */
public class AnalyzerImpl implements Analyzer {

    private final Provider provider;

    /**
     * Constructor for provider
     *
     * @param provider Provider of sorters and fillers
     */
    public AnalyzerImpl(Provider provider) {
        this.provider = provider;
    }

    /**
     * Analyzes sorters time usage with different fillers and array sizes
     * <br>
     * Sorters and fillers lists a took from {@link Provider}
     *
     * @param sizes An array of array sizes for testing
     * @return List of {@link AnalyzerResult}
     * @throws IllegalArgumentException Sizes is null or zero
     */
    @Override
    public List<AnalyzerResult> analyzeDifficulty(int... sizes){
        if(sizes == null || sizes.length == 0) throw new IllegalArgumentException();

        // map: ( filler name ) - map( test array size - test array )
        Map<String, Map<Integer, int[]>> testSuite = getSuite(sizes);

        List<AbstractSorter> sorters = getSorters();

        return doAnalyze(sorters, testSuite);
    }

    /**
     * Returns provider's sorters
     *
     * @return {@link List} of sorters
     */
    protected List<AbstractSorter> getSorters(){
        return provider.getSorters();
    }

    /**
     * Returns provider's test suite
     *
     * @param sizes An array of array sizes for testing
     * @return {@link Map} of Filler name - Map (Array size - Array)
     */
    protected Map<String, Map<Integer, int[]>> getSuite(int ... sizes){
        return provider.getSuite(sizes);
    }

    /**
     * Tests different sorters
     *
     * @param sorters List of sorters
     * @param testSuite {@link Map} of Filler name - Map (Array size - Array)
     * @return {@link List} of {@link AnalyzerResult}
     */
    private List<AnalyzerResult> doAnalyze(List<AbstractSorter> sorters, Map<String, Map<Integer, int[]>> testSuite) {
        List<AnalyzerResult> results = new ArrayList<>();

        if(sorters == null || sorters.isEmpty()
                || testSuite == null || testSuite.isEmpty()) return results;

        for (AbstractSorter sorter : sorters) {

            for(Map.Entry<String, Map<Integer, int[]>> entry : testSuite.entrySet()){

                Map<Integer, Long> elapsedTime = new HashMap<>();
                String filler = entry.getKey();
                Map<Integer, int[]> testArrays = entry.getValue();

                for(Map.Entry<Integer, int[]> subject : testArrays.entrySet()){
                    elapsedTime.put(subject.getKey(), doTest(subject.getValue(), sorter));
                }

                results.add(
                        new AnalyzerResult(
                                sorter,
                                filler,
                                elapsedTime
                        )
                );
            }
        }

        return results;
    }

    /**
     * Tests a sorter for time usage
     *
     * @param arr An array for sorting
     * @param sorter A sorter to test
     * @return Elapsed time
     */
    private long doTest(int[] arr, AbstractSorter sorter) {
        int[] substituteArray = Arrays.copyOf(arr, arr.length);
        return getTime(() -> sorter.sort(substituteArray));
    }

    /**
     * Captures time used to run a function
     *
     * @param proc Test procedure
     * @return Elapsed time
     */
    private long getTime(Procedure proc) {
        long time = System.currentTimeMillis();
        proc.invoke();
        return System.currentTimeMillis() - time;
    }

}
