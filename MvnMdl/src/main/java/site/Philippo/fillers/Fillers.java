package site.Philippo.fillers;


/**
 * Static methods to fill arrays of integer
 *
 * @author Larin
 */
public class Fillers {

	/**
	 * <pre>
	 * Used to fill the array of integers with ascending order
	 *
	 * </pre>
	 *
	 * @param arr An array to fill
	 * @param start First wanted element index
	 * @param end Last wanted element index
	 * @throws IllegalArgumentException Check the parameters you use
	 */
	@ArrayFiller(name = "Ascending filler")
	public static void initArrayWithAscendingOrder(int[] arr, int start, int end) {
		paramCheck(arr, start, end);
		for (int i = start; i < end; i++) {
			arr[i] = i;
		}
	}

	/**
	 * <pre>
	 * Used to fill the array of integers with descending order
	 *
	 * </pre>
	 *
	 * @param arr An array to fill
	 * @param start First wanted element index
	 * @param end Last wanted element index
	 * @throws IllegalArgumentException Check the parameters you use
	 */
	@ArrayFiller(name = "Descending filler")
	public static void initArrayWithDescendingOrder(int[] arr, int start, int end) {
		paramCheck(arr, start, end);
		end -= 1;
		int contetEnd = end;
		for (int i = start; i <= end; i++) {
			arr[i] = contetEnd;
			contetEnd--;
		}
	}

	/**
	 * <pre>
	 * Used to fill the array of integers with ascending order <br/>
	 * Last element is random between values you input
	 * </pre>
	 *
	 * @param arr An array to fill
	 * @param start First wanted element index
	 * @param end Last wanted element index
	 * @throws IllegalArgumentException Check the parameters you use
	 */
	@ArrayFiller(name = "Ascending filler with last random")
	public static void initArrAscLastRandom(int[] arr, int start, int end) {
		paramCheck(arr, start, end);
		for (int i = start; i < end+1; i++) {
			arr[i] = i;
		}
		arr[arr.length-1] = start + (int)(Math.random() * end);
	}

	/**
	 * <pre>
	 * Used to fill the array of integers with ascending order <br/>
	 * Last element is random between values of index you input
	 * </pre>
	 *
	 * @param arr An array to fill
	 * @param start First wanted element index
	 * @param end Last wanted element index
	 * @throws IllegalArgumentException Check the parameters you use
	 */
	@ArrayFiller(name = "Random filler")
	public static void initArrRandomFiller(int[] arr, int start, int end) {
		paramCheck(arr, start, end);
		for(int i = start; i < end+1; i++){
			arr[i] = start + (int)(Math.random() * end);
		}
	}

	/**
	 * <pre>
	 * Used to check input parameters <br/>
	 * </pre>
	 *
	 * @param arr An array to fill
	 * @param start First wanted element index
	 * @param end Last wanted element index
	 * @throws IllegalArgumentException Check the parameters you use
	 */
	private static void paramCheck(int[] arr, int start, int end){
		if (
				arr == null || arr.length == 0 || start == end ||
				end > start || end > arr.length - 1 ||
				start > arr.length - 1 || start < 0 || end < 0)

			throw new IllegalArgumentException("Parameters correctness test failed");
	}
}


