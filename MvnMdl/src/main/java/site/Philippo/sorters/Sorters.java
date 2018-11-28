package site.Philippo.sorters;

public abstract class Sorters {
	
    public void swapElements(int[] arr, int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }    
    
	abstract void doSort(int[] arr, int minInd, int maxInd);

    public void doSort(int[] arr) {
		doSort(arr, 0, arr.length-1);
	}
}
