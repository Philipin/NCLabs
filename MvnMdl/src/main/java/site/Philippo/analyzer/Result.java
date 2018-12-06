package site.Philippo.analyzer;

import site.Philippo.sorters.Sorters;
import java.util.Map;

/**
 * Class representing {@link Analyze} results
 *
 * @author Larin
 * @see Analyze
 */
public class Result {

    private final Sorters sorter;
    private final String filler;
    private final Map<Integer, Long> elapsedTime;

    /**
     * Constructor that takes analyzer result and context
     *
     * @param sorter Sorted used in test
     * @param filler Filler used in test
     * @param elapsedTime Map (Test array size - Elapsed time)
     */
    public Result(String filler, Sorters sorter, Map<Integer, Long> elapsedTime) {
        this.filler = filler;
        this.sorter = sorter;
        this.elapsedTime = elapsedTime;
    }

    /**
     * @return {@link Sorters} used in testing
     */
    public Sorters getSorter() {
        return sorter;
    }

    /**
     * @return A name of filler used in testing
     */
    public String getFiller() {
        return filler;
    }

    /**
     * @return {@link Map} Array size - Array
     */
    public Map<Integer, Long> getElapsedTime() {
        return elapsedTime;
    }
}
