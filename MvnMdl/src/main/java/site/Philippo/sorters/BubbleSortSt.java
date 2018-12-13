package site.Philippo.sorters;

/**
 * <pre>
 * Implements BubbleSort with ascending ordering
 * @see Sorters
 * </pre>
 *
 * @author Larin
 */
public class BubbleSortSt extends BubbleSort {

	/**
	 * Sort array using bubble sort with ascending ordering
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
	 * Private method to sort array with bubble sort <br/> (descending order)
	 *
	 * @param arr    The array to be sorted
	 * @param minInd Start index of the interval, inclusive
	 * @param maxInd End index of the interval, exclusive
	 */
	private void actuallyDoSort(int[] arr, int minInd, int maxInd) {
		
		for (int i = 0; i < maxInd; i++) {
			boolean flag = false;
			for (int j = minInd; j < (maxInd-i); j++) {
				if (arr[j] > arr[j+1]) {
					swapElements(arr,j+1,j);
					flag = true;
				}
			}
			if(!flag) break;
		}
	}

	@Override
	public String toString() {
		return "Bubble sort straight";
	}
}