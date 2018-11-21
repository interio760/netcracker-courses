package netcracker.analyzer.output;

import netcracker.analyzer.AnalyzerResult;

import java.util.List;

public interface AnalyzerFormatter {

    String format(List<AnalyzerResult> resultList);

}
