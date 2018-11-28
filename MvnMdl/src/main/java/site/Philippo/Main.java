package site.Philippo;
import site.Philippo.sorters.*;
import site.Philippo.fillers.*;
import site.Philippo.analyzer.*;


public class Main {

	public static void main(String[] args) {
		int[] testLengthOfArray = {1, 100, 10000};

		Filler[] arrayOfFillers = new Filler[4];

		arrayOfFillers[0] = new AscendingFiller();
		arrayOfFillers[1] = new DescendingFiller();
		arrayOfFillers[2] = new AscendingWithRandom();
		arrayOfFillers[3] = new RandomFiller();

		Sorters[] arrayOfSorters = new Sorters[8];

		arrayOfSorters[0] = new BubbleSortSt();
		arrayOfSorters[1] = new BubbleSortBk();
		arrayOfSorters[2] = new QuickSort();
		arrayOfSorters[3] = new ArraysSort();
		arrayOfSorters[4] = new HalfSort(arrayOfSorters[0]);
		arrayOfSorters[5] = new HalfSort(arrayOfSorters[1]);
		arrayOfSorters[6] = new HalfSort(arrayOfSorters[2]);
		arrayOfSorters[7] = new HalfSort(arrayOfSorters[3]);

		String[] nameOfSorter = new String[8];

		nameOfSorter[0] = "BubbleSortSt";
		nameOfSorter[1] = "BubbleSortBk";
		nameOfSorter[2] = "QuickSort";
		nameOfSorter[3] = "ArraysSort";
		nameOfSorter[4] = "HalfSort(BubbleSortSt)";
		nameOfSorter[5] = "HalfSort(BubbleSortBk)";
		nameOfSorter[6] = "HalfSort(QuickSort)";
		nameOfSorter[7] = "HalfSort(ArraysSort)";

		String[] nameOfFiller = new String[4];

		nameOfFiller[0] = "AscendingFiller";
		nameOfFiller[1] = "DescendingFiller";
		nameOfFiller[2] = "AscendingWithRandom";
		nameOfFiller[3] = "RandomFiller";


		for (int numOfElements : testLengthOfArray) {
			int i = 0;
			System.out.println("Array Length = " + numOfElements);
			for (Filler eachFiller : arrayOfFillers) {
				int j = 0;
				for (Sorters eachSorter : arrayOfSorters) {
					System.out.println(nameOfFiller[i]);
					System.out.println(nameOfSorter[j]);
					int[] arr = new int[numOfElements];
					System.out.println(new Analyzer(arr, eachFiller, eachSorter).getMeasure()+" ms");
					j++;
				}
				i++;
			}
		}
	}

}