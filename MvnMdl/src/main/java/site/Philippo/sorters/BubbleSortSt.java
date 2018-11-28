package site.Philippo.sorters;

public class BubbleSortSt extends Sorters {
	
	@Override
	public void doSort(int[] arr, int minInd, int maxInd) {
		
		if (arr == null || arr.length == 0) return;
		actuallyDoSort(arr, minInd, maxInd);
	}
	
	private void actuallyDoSort(int[] arr, int minInd, int maxInd) {
		
		for (int i = 0; i < maxInd; i++) {
			boolean sorted = false;
			for (int j = 0; j < (maxInd-i); j++) {
				if (arr[j] > arr[j+1]) {
					swapElements(arr,j+1,j);
					sorted = true;
					//int tp = arr[j];
					//arr[j] = arr[j+1];
					//arr[j+1] = tp;
				}
			}
			if(!sorted) break;
		}
	}
	
}
