package netcracker.analyzer;

import netcracker.fillers.Filler;
import netcracker.sorters.AbstractSorter;
import netcracker.sorters.HalfDivisionSort;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

public class AnalyzerImpl implements Analyzer {

    @Override
    public List<AnalyzerResult> analyzeDifficulty(int... sizes){
        if(sizes == null || sizes.length == 0) throw new IllegalArgumentException();

        Map<String, Map<Integer, int[]>> testSuite = createSuite(sizes);

        List<AbstractSorter> sorters = findSorters();

        return doAnalyze(sorters, testSuite);
    }

    private Map<String, Map<Integer, int[]>> createSuite(int[] sizes){
        Reflections reflections = new Reflections("netcracker.fillers", new MethodAnnotationsScanner());
        Set<Method> fillers = reflections.getMethodsAnnotatedWith(Filler.class);
        Map<String, Map<Integer, int[]>> testSuite = new HashMap<>();

        for (Method filler : fillers) {
            Map<Integer, int[]> list = new HashMap<>();
            for (int currentSize : sizes) {
                int[] testArray = new int[currentSize];
                try {
                    filler.invoke(null, testArray, 0, 100000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                list.put(currentSize, testArray);
            }
            testSuite.put(filler.getAnnotation(Filler.class).name(), list);
        }
        return testSuite;
    }

    private List<AbstractSorter> findSorters(){
        Reflections reflections = new Reflections("netcracker.sorters");
        Set<Class<? extends AbstractSorter>> sortersClasses =
                reflections.getSubTypesOf(AbstractSorter.class);

        List<AbstractSorter> sorters = new ArrayList<>();

        for(Class<? extends AbstractSorter> clazz : sortersClasses){
            if(clazz == HalfDivisionSort.class) continue;
            if( Modifier.isAbstract( clazz.getModifiers() )) continue;
            try {
                sorters.add(clazz.newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        List<AbstractSorter> halfDivisionSorters = new ArrayList<>();

        for(AbstractSorter sorter : sorters){
            halfDivisionSorters.add(new HalfDivisionSort(sorter));
        }

        sorters.addAll(halfDivisionSorters);

        return sorters;
    }

    private List<AnalyzerResult> doAnalyze(List<AbstractSorter> sorters, Map<String, Map<Integer, int[]>> testSuite) {
        List<AnalyzerResult> results = new ArrayList<>();
        if(sorters == null || sorters.isEmpty()
                || testSuite == null || testSuite.isEmpty()) return results;

        // todo rewrite
        for (AbstractSorter sorter : sorters) {
            for(Map.Entry<String, Map<Integer, int[]>> entry : testSuite.entrySet()){
                Map<Integer, Long> elapsedTime = new HashMap<>();
                String filler = entry.getKey();
                Map<Integer, int[]> testArrays = entry.getValue();
                for(Map.Entry<Integer, int[]> subject : testArrays.entrySet()){
                    elapsedTime.put(subject.getKey(), doTest(subject.getValue(), sorter));
                }
                results.add(new AnalyzerResult(
                                sorter,
                                filler,
                                elapsedTime
                        )
                );
            }
        }

        return results;
    }

    private long doTest(int[] arr, AbstractSorter sorter) {
        int[] substituteArray = Arrays.copyOf(arr, arr.length);
        return getTime(() -> sorter.sort(substituteArray));
    }

    private long getTime(Procedure proc) {
        long time = System.currentTimeMillis();
        proc.invoke();
        return System.currentTimeMillis() - time;
    }

}
