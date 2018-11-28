package site.Philippo.sorters;

import java.util.Arrays;

public class ArraysSort extends Sorters {
	
	@Override
	public void doSort(int[] arr, int minInd, int maxInd) {
		if (arr == null || arr.length == 0) return;
		Arrays.sort(arr, minInd, arr.length);
		
	}
	

    
}
