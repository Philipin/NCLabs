package site.Philippo.sorters;

import java.util.Arrays;

/**
 * <pre>
 * Implements java.util's array sorter
 * @see Sorters
 * </pre>
 *
 * @author Larin
 */
public class ArraysSort extends Sorters {

	/**
	 * Sort array using Arrays.sort
	 *
	 * @param arr The array to be sorted
	 * @param minInd Start index of the interval, inclusive
	 * @param maxInd End index of the interval, exclusive
	 */
	@Override
	public void doSort(int[] arr, int minInd, int maxInd) {
		Arrays.sort(arr, minInd, maxInd);
	}

	@Override
	public String toString() {
		return "Arrays sort";
	}
}