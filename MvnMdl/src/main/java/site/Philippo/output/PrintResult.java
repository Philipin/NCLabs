package site.Philippo.output;

public class PrintResult {
	
    public static void printResult(int[] array) {
    	int ARRAY_LENGTH = array.length;
    	
        for (int i=0; i<ARRAY_LENGTH-1; i++) {
            System.out.print(array[i] + ", ");
        }
        System.out.println(array[ARRAY_LENGTH-1]);
    }
}
