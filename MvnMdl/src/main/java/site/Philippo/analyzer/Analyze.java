package site.Philippo.analyzer;

import java.util.List;

/**
 * Interface for analysis methods
 *
 * @author Larin
 */
public interface Analyze {

    /**
     * Analyses time of process depends on array length
     *
     * @param sizes An array of array sizes to analyze
     * @return {@link List} of {@link Result}
     */
    List<Result> analyzeTime(int ... sizes);
}