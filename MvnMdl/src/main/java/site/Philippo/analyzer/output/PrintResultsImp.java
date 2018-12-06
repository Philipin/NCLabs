package site.Philippo.analyzer.output;

import site.Philippo.analyzer.Result;

import java.util.List;
import java.util.Map;

/**
 * {@link PrintResults} implementation
 *
 * @author Larin
 * @see Result
 */
public class PrintResultsImp implements PrintResults{

    /**
     * Formats output
     *
     * @param resultList A list of {@link Result}
     * @return String output
     */
    @Override
    public String printResults(List<Result> resultList) {
        if(resultList == null || resultList.isEmpty()) return "";
        StringBuilder resultBuilder = new StringBuilder();
        String lastSorterName = "";
        for(Result result : resultList){
            String sorterName = result.getSorter().toString();

            if(!lastSorterName.equals(sorterName)){
                resultBuilder
                        .append("--------------------------\nTesting: ")
                        .append(sorterName)
                        .append("\n\n");
            }
            else{
                resultBuilder.append("\n");
            }

            resultBuilder
                    .append("Filler: ")
                    .append(result.getFiller())
                    .append("\nElapsed time: ");

            Map<Integer, Long> elapsedTime = result.getElapsedTime();

            for(Map.Entry<Integer, Long> entry : elapsedTime.entrySet()){
                resultBuilder
                        .append("\nArray size: ")
                        .append(entry.getKey())
                        .append(" | Time: ")
                        .append(entry.getValue())
                        .append(" ms.");
            }

            resultBuilder.append("\n");

            lastSorterName = sorterName;

        }
        return resultBuilder.toString();
    }
}
