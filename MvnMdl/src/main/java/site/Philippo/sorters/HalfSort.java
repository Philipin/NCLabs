package site.Philippo.sorters;

import java.util.Arrays;

public class HalfSort extends Sorters {
	
	private final Sorters sorter;
	
	public HalfSort(Sorters sorter) {
		this.sorter = sorter;
	}
	
	public Sorters getSorter() {
		return sorter;
	}
	
    @Override
    public void doSort(int[] arr) {
        doSort(arr, 0, arr.length - 1);
    }
    
    @Override
    public void doSort(int[] arr, int minInd, int maxInd) {
    	if (arr == null || arr.length == 0) return;
    	int[][] twoDimResultedArray = split(arr, (arr.length / 2));
    	for(int[] array : twoDimResultedArray) {
    	    sorter.doSort(array);
    	}
    	System.arraycopy(merge(twoDimResultedArray[0], twoDimResultedArray[1]), 0, arr, 0, arr.length);
    }
    
    
    private int[] merge(int[] array1, int[] array2){
    	
    	int finalLength = array1.length + array2.length;
    	int[] result = new int[finalLength];
    	int a = 0;
    	int b = 0;
	 
    	for(int i = 0; i < finalLength; i++) {
            if (a < array1.length && b < array2.length) {
                if (array1[a] > array2[b]){
                    result[i] = array2[b++];
                }
                else {
                    result[i] = array1[a++];
                }
            } else if (a < array1.length) {
                result[i] = array1[a++];
            } else {
                result[i] = array2[b++];
            }
        }
    	return result;
    }


    private int[][] split(int[] array, int i){
    	int[][] twoDimResultedArray = new int [2][];
    	twoDimResultedArray[0] = Arrays.copyOfRange(array,0, i);
    	twoDimResultedArray[1] = Arrays.copyOfRange(array, i, array.length);
    	return twoDimResultedArray;
    }


}

