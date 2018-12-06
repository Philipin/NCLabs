package site.Philippo;

import site.Philippo.analyzer.output.*;
import site.Philippo.analyzer.*;

import java.util.List;

public class Main {

	public static void main(String[] args) {


		ImpAnalyzer analyzer = new ImpAnalyzer(new RefSource(
				"site.Philippo.fillers", "site.Philippo.sorters"));

		List<Result> resultList = analyzer.analyzeTime(1000, 10000, 20000);

		PrintResultsImp analyzerPrint = new PrintResultsImp();
		String report = analyzerPrint.printResults(resultList);

		System.out.println(report);
	}
}