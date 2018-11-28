package site.Philippo.sorters;

public class QuickSort extends Sorters{
	
	@Override
	public void doSort(int[] arr, int minInd, int maxInd) {
		if (arr == null || arr.length == 0) return;
        actuallyDoSort(arr, minInd, maxInd);
    }
	
	
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
        actuallyDoSort(arr, cur+1, end);
    }
		
	
}
