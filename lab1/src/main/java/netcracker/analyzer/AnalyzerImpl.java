package netcracker.analyzer;

import netcracker.fillers.Filler;
import netcracker.sorters.Sorter;

import java.util.*;

public class AnalyzerImpl implements Analyzer {

    public List<AnalyzerResult> analyze(List<Sorter> sorters, List<Filler> fillers, int size) {
        List<AnalyzerResult> results = new ArrayList<>();
        Map<Filler, int[]> testArrays = new HashMap<>();

        for (Filler filler : fillers) {
            int[] testArray = new int[size];
            filler.fill(testArray, 0, 100000);
            testArrays.put(filler, testArray);
        }

        for (Sorter sorter : sorters) {
            for (Filler filler : fillers) {
                results.add(new AnalyzerResult(
                        sorter,
                        filler,
                        doTest(testArrays.get(filler), sorter)
                ));
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
