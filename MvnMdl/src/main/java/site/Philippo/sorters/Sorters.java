package site.Philippo.sorters;

/**
 * <pre>
 * Abstract class for array sorting in several methods
 * Educational class, use arrays.sort
 * @code
 * Sorters name = new Sorters();
 * sorter.doSort(anyArray);
 *
 * </pre>
 *
 * @author Larin
 */
public abstract class Sorters {

    /**
     * Swap elements implementation for sorting
     *
     * @param arr An array of elements to swap
     * @param i Index of element will be swapped with element j
     * @param j Index of element swapping with element i
     */
    protected void swapElements(int[] arr, int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * Sorting implementation
     *
     * @param arr An array to sort
     * @param minInd Start index of the interval
     * @param maxInd End index of the interval
     */
	protected abstract void doSort(int[] arr, int minInd, int maxInd);

    /**
     * <pre>
     * Default implementation, provides additional parameters
     * Use to sort whole array
     * </pre>
     *
     * @param arr An array to sort
     * @throws IllegalArgumentException Parameters correctness test failed
     */
    public void doSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Illegal Argument. Check the type and/or content");
        }
        if (arr.length == 1) return;
		doSort(arr, 0, arr.length-1);
	}
}
