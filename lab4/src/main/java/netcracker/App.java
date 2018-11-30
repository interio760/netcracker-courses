package netcracker;

import netcracker.analyzer.output.*;
import netcracker.analyzer.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class App {
    public static void main(String[] args) throws IOException {

        AnalyzerImpl analyzer = new AnalyzerImpl(new ReflectionProvider(
                "netcracker.sorter", "netcracker.filler"));

        List<AnalyzerResult> resultList = analyzer.analyzeDifficulty(10000, 20000, 30000, 40000, 50000);

        AnalyzerFormatterImpl analyzerFormatter = new AnalyzerFormatterImpl();
        String report = analyzerFormatter.format(resultList);

        System.out.println(report);

        AnalyzerExcelOutput excelOutput = new AnalyzerExcelOutput();
        excelOutput.writeToExcel(resultList, new File("result.xlsx"));

    }
}