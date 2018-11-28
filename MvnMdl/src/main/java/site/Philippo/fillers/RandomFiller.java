package site.Philippo.fillers;


public class RandomFiller extends Filler {
	
   
    @Override
    public void initArrayOfInt(int[] arr, int start, int end) {
    	if (arr == null || arr.length == 0) return;
        if(start > end) return;
        randomFiller(arr, start, end);
    }

    protected void randomFiller(int[] arr, int start, int end) {
        for(int i = 0; i < arr.length; i++){
            arr[i] = start + (int)(Math.random() * end);
        }
    }
}

