package site.Philippo.fillers;



public class AscendingFiller extends Filler {
		
    @Override
    public void initArrayOfInt(int[] arr, int start, int end) {
    	if (arr == null || arr.length == 0) return;
        if(start > end) return;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }   
    }

}