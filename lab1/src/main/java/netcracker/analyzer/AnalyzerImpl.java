package netcracker.analyzer;

import netcracker.fillers.Filler;
import netcracker.sorters.Sort;
import netcracker.sorters.SortWithDependency;
import netcracker.sorters.Sorter;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.util.*;

public class AnalyzerImpl implements Analyzer {

    @Override
    public List<AnalyzerResult> analyzeDifficulty(List<Sorter> sorters, List<Filler> fillers, int... sizes) {
        List<AnalyzerResult> results = new ArrayList<>();
        if(sorters == null || sorters.isEmpty()
                || fillers == null || fillers.isEmpty()) return results;

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
                            doTest(testArrays.get(filler).get(currentSize), sorter)
                    );
                }
                results.add(new AnalyzerResult(sorter, filler, difficulty));
            }
        }

        return results;
    }

    @Override
    public List<AnalyzerResult> analyzeDifficulty(List<Filler> fillers, int... sizes) {
        Reflections reflections = new Reflections("netcracker.sorters");
        Set<Class<?>> sort = reflections.getTypesAnnotatedWith(Sort.class);

        List<Sorter> sorters = new ArrayList<>();

        for(Class<?> clazz : sort){
            try {
                sorters.add((Sorter) clazz.newInstance());
            }catch (Exception e) {
                e.printStackTrace();
            }
        }

        Set<Class<?>> sortWithDependency = reflections.getTypesAnnotatedWith(SortWithDependency.class);

        List<Sorter> depSorters = new ArrayList<>();

        for(Class<?> clazz : sortWithDependency) {
            try {
                Constructor constructor = clazz.getConstructor(Sorter.class);
                for(Sorter sorter : sorters){
                    depSorters.add((Sorter) constructor.newInstance(sorter));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        sorters.addAll(depSorters);

        return analyzeDifficulty(sorters, fillers, sizes);
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
