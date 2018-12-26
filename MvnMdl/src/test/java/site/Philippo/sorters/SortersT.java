package site.Philippo.sorters;
import java.util.Arrays;
import java.util.List;

public class SortersT {

    public static List<Sorters> getSortersForTesting(){
        Sorters arraysSort = new ArraysSort();
        Sorters bstSort = new BubbleSortSt();
        Sorters bbkSort = new BubbleSortBk();
        Sorters quickSort = new QuickSort();

        return Arrays.asList(
                arraysSort,
                bstSort,
                bbkSort,
                quickSort,
                new HalfSort(arraysSort),
                new HalfSort(bstSort),
                new HalfSort(bbkSort),
                new HalfSort(quickSort)
        );
    }

}