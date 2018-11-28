package site.Philippo.fillers;

public class AscendingWithRandom extends Filler {
    
	
	public void initArrayOfInt(int[] arr, int start, int end) {
		if (arr == null || arr.length == 0) return;
		if(start > end) return;
        for (int i = start; i < end; i++) {
            arr[i] = i;
        }
        arr[arr.length-1] = start + (int)(Math.random() * end);
    }
}
