package site.Philippo.analyzer;

import site.Philippo.sorters.Sorters;
import java.util.*;

/**
 * Implementation of {@link Analyze} that uses {@link Provider} to get {@link Sorters} and Fillers lists  <br/>
 *
 *
 * <pre>{@code
 * Analyze analyzer = new ImpAnalyzer(new ReflectionProvider("path_to_fillers_package", "path_to_sorters_package");
 * List<AnalyzersRes> = analyzer.analyzeTime(1000, 100000);
 * }</pre>
 *
 * @author Larin
 * @see Provider
 * @see Sorters
 * @see site.Philippo.fillers.Fillers
 */
public class ImpAnalyzer implements Analyze{

    private final Provider provider;
    /**
     * Providers constructor
     *
     * @param provider Provider of sorters and fillers
     */
    public ImpAnalyzer(Provider provider) {
        this.provider = provider;
    }

    /**
     * Analyzes sorters time usage with different fillers and array sizes <br/>
     *
     * Sorters and fillers lists a took from {@link Provider}
     *
     * @param sizes An array of array sizes for testing
     * @return List of {@link Result}
     * @throws IllegalArgumentException Sizes is null or zero
     */
    @Override
    public List<Result> analyzeTime(int... sizes){
        if(sizes == null || sizes.length == 0) throw new IllegalArgumentException("Sizes is null or zero");

        // map: ( filler name ) - map( test array size - test array )
        Map<String, Map<Integer, int[]>> testSuite = getSuite(sizes);

        List<Sorters> sorters = getSorters();

        return doAnalyze(sorters, testSuite);
    }

    /**
     * Returns provider's sorters
     *
     * @return {@link List} of sorters
     */
    protected List<Sorters> getSorters(){
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
     * @return {@link List} of {@link Result}
     */
    private List<Result> doAnalyze(List<Sorters> sorters, Map<String, Map<Integer, int[]>> testSuite) {
        List<Result> result = new ArrayList<>();

        if(sorters == null || sorters.isEmpty()
                || testSuite == null || testSuite.isEmpty()) return result;

        for (Sorters sorter : sorters) {

            for(Map.Entry<String, Map<Integer, int[]>> entry : testSuite.entrySet()){

                Map<Integer, Long> elapsedTime = new HashMap<>();
                String filler = entry.getKey();
                Map<Integer, int[]> testArrays = entry.getValue();

                for(Map.Entry<Integer, int[]> subject : testArrays.entrySet()){
                    elapsedTime.put(subject.getKey(), doMeasure(subject.getValue(), sorter));
                }

                result.add( new Result(filler, sorter, elapsedTime));

            }
        }

        return result;
    }

    /**
     * Measures time needed to run
     *
     * @param arr The array to sort
     * @param sorter A sorter to measure
     * @return Elapsed time in milliseconds
     */
    private long doMeasure(int[] arr, Sorters sorter) {
        int[] substituteArray = Arrays.copyOf(arr, arr.length);

        long start = System.nanoTime();
        sorter.doSort(substituteArray);

        return System.nanoTime() - start;
    }
}
