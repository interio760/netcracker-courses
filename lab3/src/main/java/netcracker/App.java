package netcracker;

import netcracker.analyzer.output.*;
import netcracker.analyzer.*;

import java.util.List;

public class App {
    public static void main(String[] args) {

        AnalyzerImpl analyzer = new AnalyzerImpl();

        List<AnalyzerResult> resultList = analyzer.analyzeDifficulty(1000, 10000, 20000);

        AnalyzerFormatterImpl analyzerFormatter = new AnalyzerFormatterImpl();
        String report = analyzerFormatter.format(resultList);

        System.out.println(report);

    }
}