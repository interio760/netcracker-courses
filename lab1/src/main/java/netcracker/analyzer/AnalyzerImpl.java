package netcracker.analyzer;

import netcracker.fillers.Filler;
import netcracker.sorters.Sorter;

import java.util.*;

public class AnalyzerImpl implements Analyzer {

    public List<AnalyzerResult> analyze(List<AnalyzeSet> analyzeSets, int size){
        List<AnalyzerResult> results = new ArrayList<>();
        Set<Filler> fillerSet = new HashSet<>();
        Map<Filler, int[]> testArrays = new HashMap<>();

        for(AnalyzeSet set : analyzeSets){
            fillerSet.addAll(set.getFillerList());
        }

        for(Filler filler : fillerSet){
            int[] testArray = new int[size];
            filler.fill(testArray, 0, 100000);
            testArrays.put(filler, testArray);
        }

        for(AnalyzeSet set : analyzeSets){
            for(Sorter sorter : set.getSorterList()){
                for(Filler filler : set.getFillerList()){
                    results.add(new AnalyzerResult(
                            sorter,
                            filler,
                            doTest(testArrays.get(filler), sorter)
                    ));
                }
            }
        }

        return results;
    }

    private long doTest(int[] arr, Sorter sorter){
        int[] substituteArray = Arrays.copyOf(arr, arr.length);
        return getTime( () -> sorter.ascSort(substituteArray) );
    }

    private long getTime(Procedure proc) {
        long time = System.currentTimeMillis();
        proc.invoke();
        return System.currentTimeMillis() - time;
    }

}
