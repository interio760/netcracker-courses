package netcracker.analyzer;

import netcracker.fillers.Filler;
import netcracker.sorters.AbstractSorter;
import netcracker.sorters.HalfDivisionSort;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * Provider implementation that searches sorters and fillers using {@link Reflections}
 *
 * @author Zakh
 */
public class ReflectionProvider extends Provider {

    private final String sortersLocation;
    private final String fillersLocation;

    /**
     * Constructor for search locations
     *
     * @param sortersLocation Package location of sorters
     * @param fillersLocation Package location of fillers
     */
    public ReflectionProvider(String sortersLocation, String fillersLocation) {
        this.sortersLocation = sortersLocation;
        this.fillersLocation = fillersLocation;
    }

    /**
     * Finds {@link AbstractSorter} children and returns them in a {@link List}
     *
     * @return List of sorters
     */
    @Override
    protected List<AbstractSorter> getSorters() {
        Reflections reflections = new Reflections(sortersLocation);
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

    /**
     * Searches all fillers and creates a test array for each filler and size
     *
     * @param sizes An array of array sizes for testing
     * @return Map of Filler name - Map (Array size - Array)
     */
    @Override
    protected Map<String, Map<Integer, int[]>> getSuite(int... sizes) {
        Reflections reflections = new Reflections(fillersLocation, new MethodAnnotationsScanner());
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

}
