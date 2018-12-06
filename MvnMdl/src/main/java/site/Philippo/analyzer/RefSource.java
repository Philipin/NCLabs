package site.Philippo.analyzer;

import site.Philippo.fillers.ArrayFiller;
import site.Philippo.sorters.Sorters;
import site.Philippo.sorters.HalfSort;

import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * Class implementation for searching sorters and fillers using {@link Reflections}
 *
 * @author Larin
 */
public class RefSource extends Provider {

    private final String fillersPlace;
    private final String sortersPlace;

    /**
     * Constructor to search places - locations of each sorter and filler
     *
     * @param fillersPlace Package location of fillers
     * @param sortersPlace Package location of sorters
     */
    public RefSource(String fillersPlace, String sortersPlace) {
        this.fillersPlace = fillersPlace;
        this.sortersPlace = sortersPlace;
    }

    /**
     * Searches {@link Sorters} heirs, and returns them as a {@link List}
     *
     * @return List of sorters
     */
    @Override
    protected List<Sorters> getSorters() {
        Reflections reflections = new Reflections(sortersPlace);
        Set<Class<? extends Sorters>> sortersClasses =
                reflections.getSubTypesOf(Sorters.class);

        List<Sorters> sorters = new ArrayList<>();

        for(Class<? extends Sorters> clazz : sortersClasses){
            if(clazz == HalfSort.class) continue;
            if(Modifier.isAbstract( clazz.getModifiers() )) continue;
            try {
                sorters.add(clazz.newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        List<Sorters> halfSorters = new ArrayList<>();

        for(Sorters sorter : sorters){
            halfSorters.add(new HalfSort(sorter));
        }

        sorters.addAll(halfSorters);

        return sorters;
    }

    /**
     * Searches all fillers and creates a test array for each filler and size
     *
     * @param sizes An array of array sizes for testing
     * @return Map of Filler name - Map (Array size - Array)
     */
    @Override
    protected Map<String, Map<Integer, int[]>> getSuite(int ... sizes) {
        Reflections reflections = new Reflections(fillersPlace, new MethodAnnotationsScanner());
        Set<Method> fillers = reflections.getMethodsAnnotatedWith(ArrayFiller.class);
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
            testSuite.put(filler.getAnnotation(ArrayFiller.class).name(), list);
        }
        return testSuite;
    }
}

