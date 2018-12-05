package site.Philippo.analyzer;
import site.Philippo.sorters.*;
import site.Philippo.fillers.*;


public class Analyzer {

	private Sorters sorter;
	private Filler filler;
    private int[] array;

	public Analyzer(int[] arr, Filler filler, Sorters sorter) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Illegal Argument. Check the type and/or content");
        }
        array = new int[arr.length];
        System.arraycopy(arr, 0, array, 0, arr.length);
		this.sorter = sorter;
		this.filler = filler;

	}

	public double getMeasure(){
	    double start = System.nanoTime();
        filler.initArrayOfInt(array,0,array.length);
        sorter.doSort(array);
        double end = System.nanoTime();
        double timeMils = (end - start)/1000000;
        return timeMils;
    }
}
