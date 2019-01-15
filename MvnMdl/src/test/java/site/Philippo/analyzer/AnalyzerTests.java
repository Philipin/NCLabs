package site.Philippo.analyzer;

import site.Philippo.fillers.ArrayFiller;
import site.Philippo.sorters.*;
import org.junit.Before;
import org.junit.Test;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;

import java.lang.reflect.Method;
import java.util.*;

public class AnalyzerTests {

    private List<Sorters> sorterList;
    private Analyze analyzer;

    @Before
    public void init(){
        sorterList = SortersT.getSortersForTesting();
        analyzer = new ImpAnalyzer(new RefSource(
                "site.Philippo.fillers", "site.Philippo.sorters"));
    }

    @Test(timeout = 10000)
    public void analyzeDifficultyCorrectnessTest(){
        Reflections reflections = new Reflections("site.Philippo.fillers", new MethodAnnotationsScanner());
        Set<Method> fillersMethods = reflections.getMethodsAnnotatedWith(ArrayFiller.class);
        List<String> fillers = new ArrayList<>();

        for(Method filler : fillersMethods){
            fillers.add(filler.getAnnotation(ArrayFiller.class).name());
        }

        int[] sizes = new int[]{1000, 2000};
        List<Result>  results = analyzer.analyzeTime(sizes);

        for(String filler : fillers){
            for(Sorters sorter : sorterList){
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
        analyzer.analyzeTime();
    }

    @Test(expected = IllegalArgumentException.class, timeout = 10000)
    public void analyzeDifficultyIllegalArgumentsTest_1(){
        analyzer.analyzeTime(null);
    }

    private boolean contains(List<Result> list, String filler, Sorters sorter, int size){
        for(Result result : list){
            String resultFiller = result.getFiller();
            Sorters resultSorter = result.getSorter();
            Map<Integer, Long> sizeTime = result.getElapsedTime();

            if(!resultFiller.equals(filler)) continue;

            if(sorter instanceof HalfSort){
                if(sorter.getClass() != resultSorter.getClass()) continue;

                HalfSort sort = (HalfSort) sorter;
                HalfSort resultSort = (HalfSort) resultSorter;

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
