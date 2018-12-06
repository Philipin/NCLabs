package site.Philippo.analyzer;
import site.Philippo.sorters.Sorters;

import java.util.List;
import java.util.Map;

/**
 * Abstract class that provides a list of sorters and a map of Fillers <br/>
 * Filler name - Map (Array size - Array)
 *
 * @author Larin
 */
public abstract class Provider {

    /**
     * Returns sorters list
     *
     * @return List of sorters
     */
    protected abstract List<Sorters> getSorters();

    /**
     * Returns a map  <br/>
     * Filler name - Map (Array size - Array)
     *
     * @param sizes An array of array sizes for testing
     * @return Map of Filler name - Map (Array size - Array)
     */
    protected abstract Map<String, Map<Integer, int[]>> getSuite(int ... sizes);
}
