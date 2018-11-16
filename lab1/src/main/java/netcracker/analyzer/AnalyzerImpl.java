package netcracker.analyzer;

import netcracker.fillers.Filler;
import netcracker.sorters.Sorter;

import java.util.*;

public class AnalyzerImpl implements Analyzer {

    @Override
    public List<AnalyzerResult> analyzeDifficulty(List<Sorter> sorters, List<Filler> fillers, int... sizes) {
        List<AnalyzerResult> results = new ArrayList<>();

        Map<Filler, Map<Integer, int[]>> testArrays = new HashMap<>();

        for (Filler filler : fillers) {
            Map<Integer, int[]> list = new HashMap<>();
            for (int currentSize : sizes) {
                int[] testArray = new int[currentSize];
                filler.fill(testArray, 0, 100000);
                list.put(currentSize, testArray);
            }
            testArrays.put(filler, list);
        }

        for (Sorter sorter : sorters) {
            for (Filler filler : fillers) {
                Map<Integer, Long> difficulty = new LinkedHashMap<>();
                for (int currentSize : sizes) {
                    difficulty.put(
                            currentSize,
                            doTest(testArrays.get(filler).get(currentSize), sorter));
                }
                results.add(new AnalyzerResult(sorter, filler, difficulty));
            }
        }

        return results;
    }

    private long doTest(int[] arr, Sorter sorter) {
        int[] substituteArray = Arrays.copyOf(arr, arr.length);
        return getTime(() -> sorter.ascSort(substituteArray));
    }

    private long getTime(Procedure proc) {
        long time = System.currentTimeMillis();
        proc.invoke();
        return System.currentTimeMillis() - time;
    }

}
