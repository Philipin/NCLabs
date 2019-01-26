package site.Philippo;

import site.Philippo.analyzer.output.*;
import site.Philippo.analyzer.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {

	public static void main(String[] args) throws IOException  {


		ImpAnalyzer analyzer = new ImpAnalyzer(new RefSource(
				"site.Philippo.fillers", "site.Philippo.sorters"));

		List<Result> resultList = analyzer.analyzeTime( 10000, 20000, 50000, 100000);

		PrintResultsImp analyzerPrint = new PrintResultsImp();
		String report = analyzerPrint.printResults(resultList);

		System.out.println(report);

		PrintResultsExl exlOutput = new PrintResultsExl();
		exlOutput.writeToExl(resultList, new File("results.xlsx"));
	}
}