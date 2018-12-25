package site.Philippo.fillers;

import site.Philippo.sorters.QuickSort;
import org.junit.Test;

import java.util.Arrays;
import static org.junit.Assert.*;

public class FillersTests {
    @Test(timeout = 1000)
    public void ascFillIllegalArgumentsTest(){
        testAscFill(null, 0, 10);
        testAscFill(new int[0], 0, 10);
        testAscFill(new int[10], 10, 0);
    }

    @Test(timeout = 1000)
    public void ascFillWithLastRandomIllegalArgumentsTest(){
        testAscWithLastRandomFill(null, 0, 10);
        testAscWithLastRandomFill(new int[0], 0, 10);
        testAscWithLastRandomFill(new int[10], 10, 0);
    }

    @Test(timeout = 1000)
    public void descFillIllegalArgumentsTest(){
        testDescFill(null, 0, 10);
        testDescFill(new int[0], 0, 10);
        testDescFill(new int[10], 10, 0);
    }

    @Test(timeout = 1000)
    public void randomIllegalArgumentsTest(){
        testRandomFill(null, 0, 10);
        testRandomFill(new int[0], 0, 10);
        testRandomFill(new int[10], 10, 0);
    }

    @Test(timeout = 1000)
    public void ascFillCorrectnessTest(){
        int[] testArr = new int[10];
        Fillers.initArrayWithAscendingOrder(testArr, -100, 100);
        int[] ascArr = Arrays.copyOf(testArr, testArr.length);
        Arrays.sort(ascArr);
        assertArrayEquals(testArr, ascArr);
    }

    @Test(timeout = 1000)
    public void ascFillWithLastRandomCorrectnessTest(){
        int[] testArr = new int[10];
        Fillers.initArrAscLastRandom(testArr, -100, 100);
        int[] ascArr = Arrays.copyOf(testArr, testArr.length - 1);
        Arrays.sort(ascArr);
        int[] testArrWithoutLast = new int[9];
        System.arraycopy(testArr, 0, testArrWithoutLast, 0, testArr.length - 1);
        assertArrayEquals(testArrWithoutLast, ascArr);
    }

    @Test(timeout = 1000)
    public void descFillCorrectnessTest(){
        int[] testArr = new int[10];
        Fillers.initArrayWithDescendingOrder(testArr, -100, 100);
        int[] descArr = Arrays.copyOf(testArr, testArr.length);
        new QuickSort().doSort(descArr);
        assertArrayEquals(testArr, descArr);
    }

    private void testAscFill(int[] arr, int min, int max){
        try{
            Fillers.initArrayWithAscendingOrder(arr, min, max);
        }catch (IllegalArgumentException e){
            return;
        }
        fail("" +
                "initArrayWithAscendingOrder no exception with arguments: arr="
                + (arr == null ? "null" : "[" + arr.length + "]")
                + " min=" + min + " max=" + max
                + " | Expected: IllegalArgumentException"
        );
    }

    private void testDescFill(int[] arr, int min, int max){
        try{
            Fillers.initArrayWithDescendingOrder(arr, min, max);
        }catch (IllegalArgumentException e){
            return;
        }
        fail(
                "initArrayWithDescendingOrder no exception with arguments: arr="
                        + (arr == null ? "null" : "[" + arr.length + "]")
                        + " min=" + min + " max=" + max
                        + " | Expected: IllegalArgumentException"
        );
    }

    private void testAscWithLastRandomFill(int[] arr, int min, int max){
        try{
            Fillers.initArrAscLastRandom(arr, min, max);
        }catch (IllegalArgumentException e){
            return;
        }
        fail(
                "initArrAscLastRandom no exception with arguments: arr="
                        + (arr == null ? "null" : "[" + arr.length + "]")
                        + " min=" + min + " max=" + max
                        + " | Expected: IllegalArgumentException"
        );
    }

    private void testRandomFill(int[] arr, int min, int max){
        try{
            Fillers.initArrRandomFiller(arr, min, max);
        }catch (IllegalArgumentException e){
            return;
        }
        fail(
                "initArrRandomFiller no exception with arguments: arr="
                        + (arr == null ? "null" : "[" + arr.length + "]")
                        + " min=" + min + " max=" + max
                        + " | Expected: IllegalArgumentExceptions"
        );
    }
}
