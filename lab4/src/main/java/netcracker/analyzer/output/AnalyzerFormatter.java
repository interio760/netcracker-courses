package netcracker.analyzer.output;

import netcracker.analyzer.AnalyzerResult;

import java.util.List;

/**
 * Interface that defines {@link AnalyzerResult} output methods
 *
 * @author Zakh
 */
public interface AnalyzerFormatter {

    String format(List<AnalyzerResult> resultList);

}
