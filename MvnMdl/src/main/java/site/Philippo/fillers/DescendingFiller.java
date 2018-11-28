package site.Philippo.fillers;

public class DescendingFiller extends Filler {
	
    @Override
    public void initArrayOfInt(int[] arr, int start, int end) {
    	
    	if (arr == null || arr.length == 0) return;
    	end -= 1;
    	int contetEnd = end;
        for (int i = start; i <= end; i++) {
            arr[i] = contetEnd;
            contetEnd--;
        }
    }
    
    
}
