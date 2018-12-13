package site.Philippo.sorters;

/**
 * <pre>
 * Sorts the array with QuickSort algorithm
 * @see Sorters
 * </pre>
 *
 * @author Larin
 */
public class QuickSort extends Sorters {

    /**
     * Sorts the array quickly
     *
     * @param arr    The array to be sorted
     * @param minInd Start index of the interval, inclusive
     * @param maxInd End index of the interval, exclusive
     */
    @Override
    public void doSort(int[] arr, int minInd, int maxInd) {
        actuallyDoSort(arr, minInd, maxInd);
    }

    /**
     * Quick sort algorithm
     *
     * @param arr First part of array to merge
     * @param start Second part of array to merge
     * @param end Second part of array to merge
     */
    private void actuallyDoSort(int[] arr, int start, int end) {

        if (start >= end) return;

        int i = start, j = end;
        int cur = i - (i - j) / 2;
        int piv = arr[cur];

        while (i < j) {
            while (i < cur && (arr[i] <= piv)) i++;

            while (j > cur && (piv <= arr[j])) j--;

            if (i < j) {
                swapElements(arr, i, j);
                if (i == cur)
                    cur = j;
                else if (j == cur)
                    cur = i;
            }
        }
        actuallyDoSort(arr, start, cur);
        actuallyDoSort(arr, cur + 1, end);
    }

    @Override
    public String toString() {
        return "Quick sort";
    }
}
