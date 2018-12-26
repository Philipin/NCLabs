package site.Philippo.sorters;

import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
public class SortersTests {

    private List<Sorters> sorterList;

    @Before
    public void init(){
        sorterList = SortersT.getSortersForTesting();
    }

    @Test(timeout = 1000)
    public void sortingIllegalArgumentsTest(){
        int[] arrDef = new int[]{1, -3, 3, 6, -23, 33};
        for(Sorters sorter : sorterList) {
            int[] arr = Arrays.copyOf(arrDef, arrDef.length);

            try {

                testAscSort(sorter, arr, 5, -15);
                testAscSort(sorter, arr, -15, 5);
                testAscSort(sorter, arr, 1000, 2);
                testAscSort(sorter, arr, 2, 1000);
                testAscSort(sorter, null, 0, -15);
                testAscSort(sorter, new int[0], 0, -15);

                testDescSort(sorter, arr, 5, -15);
                testDescSort(sorter, arr, -15, 5);
                testDescSort(sorter, arr, 1000, 2);
                testDescSort(sorter, arr, 2, 1000);
                testDescSort(sorter, null, 0, -15);
                testDescSort(sorter, new int[0], 0, -15);

                testPlainAscSort(sorter, null);
                testPlainAscSort(sorter, new int[0]);

                testPlainDescSort(sorter, null);
                testPlainDescSort(sorter, new int[0]);

            }catch (Exception e){
                fail(sorter.toString() + " illegal arguments test failed.");
            }

        }
    }

    @Test(timeout = 1000)
    public void sortingCorrectnessTest(){
        int[] arrDef = new int[]{1, -3, 3, 6, -23, 33};
        int[] ascArr = new int[]{-23, -3, 1, 3, 6, 33};
        int[] descArr = new int[]{33, 6, 3, 1, -3, -23};
        int[] oneIntArr = new int[]{42};

        for(Sorters sorter : sorterList){
            doSortTest(ascArr, descArr, arrDef, sorter);
            doSortTest(oneIntArr, oneIntArr, oneIntArr, sorter);
        }
    }

    private void doSortTest(int[] expectAsc, int[] expectDesc, int[] original, Sorters sorter){
        int[] arr = Arrays.copyOf(original, original.length);
        sorter.doSort(arr);
        assertArrayEquals(expectAsc, arr);
        sorter.doSort(arr);
        assertArrayEquals(expectDesc, arr);
    }

    private void testAscSort(Sorters sorter, int[] arr, int start, int end){
        try {
            sorter.doSort(arr, start, end);
        }catch (IllegalArgumentException e){
            return;
        }
        fail(sorter.toString() + " asc sort test of illegal arguments failed.");
    }

    private void testDescSort(Sorters sorter, int[] arr, int start, int end){
        try {
            sorter.doSort(arr, start, end);
        }catch (IllegalArgumentException e){
            return;
        }
        fail(sorter.toString() + " desc sort test of illegal arguments failed." );
    }

    private void testPlainAscSort(Sorters sorter, int[] arr){
        try {
            sorter.doSort(arr);
        }catch (IllegalArgumentException e){
            return;
        }
        fail(sorter.toString() + " plain asc sort test of illegal arguments failed.");
    }

    private void testPlainDescSort(Sorters sorter, int[] arr){
        try {
            sorter.doSort(arr);
        }catch (IllegalArgumentException e){
            return;
        }
        fail(sorter.toString() + " plain desc sort test of illegal arguments failed.");
    }

}
