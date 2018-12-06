package site.Philippo.analyzer.output;
import site.Philippo.analyzer.Result;

import java.util.List;

/**
 * Interface that defines {@link Result} output methods
 *
 * @author Larin
 */
public interface PrintResults {

    String printResults(List<Result> resultList);
}
