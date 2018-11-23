package netcracker.analyzer;

import netcracker.fillers.Filler;
import netcracker.sorters.*;
import org.junit.Before;
import org.junit.Test;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;

import java.lang.reflect.Method;
import java.util.*;

public class AnalyzerTest {

    private List<AbstractSorter> sorterList;

    @Before
    public void init(){
        sorterList = Sorters.getSortersForTesting();
    }

    @Test(timeout = 10000)
    public void analyzeDifficultyCorrectnessTest(){
        Reflections reflections = new Reflections("netcracker.fillers", new MethodAnnotationsScanner());
        Set<Method> fillersMethods = reflections.getMethodsAnnotatedWith(Filler.class);
        List<String> fillers = new ArrayList<>();

        for(Method filler : fillersMethods){
            fillers.add(filler.getAnnotation(Filler.class).name());
        }

        int[] sizes = new int[]{1000, 2000};
        List<AnalyzerResult> results = new AnalyzerImpl().analyzeDifficulty(sizes);

        for(String filler : fillers){
            for(AbstractSorter sorter : sorterList){
                for(int size : sizes){
                    if(!contains(results, filler, sorter, size)){
                        throw new AssertionError(
                                "Result list does not contain:" +
                                " filler=" + filler +
                                " sorter=" + sorter.toString() +
                                " size=" + size);
                    }
                }
            }
        }

    }

    @Test(expected = IllegalArgumentException.class, timeout = 10000)
    public void analyzeDifficultyIllegalArgumentsTest_0(){
        new AnalyzerImpl().analyzeDifficulty();
    }

    @Test(expected = IllegalArgumentException.class, timeout = 10000)
    public void analyzeDifficultyIllegalArgumentsTest_1(){
        new AnalyzerImpl().analyzeDifficulty(null);
    }

    private boolean contains(List<AnalyzerResult> list, String filler, AbstractSorter sorter, int size){
        for(AnalyzerResult result : list){
            String resultFiller = result.getFiller();
            AbstractSorter resultSorter = result.getSorter();
            Map<Integer, Long> sizeTime = result.getElapsedTime();

            if(!resultFiller.equals(filler)) continue;

            if(sorter instanceof HalfDivisionSort){
                if(sorter.getClass() != resultSorter.getClass()) continue;

                HalfDivisionSort sort = (HalfDivisionSort) sorter;
                HalfDivisionSort resultSort = (HalfDivisionSort) resultSorter;

                if(sort.getSorter().getClass() != resultSort.getSorter().getClass()) continue;
            }
            else{
                if(sorter.getClass() != resultSorter.getClass()) continue;
            }

            if(!sizeTime.containsKey(size)) continue;

            return true;
        }
        return false;
    }
}
