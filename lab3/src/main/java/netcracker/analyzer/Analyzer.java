package netcracker.analyzer;

import java.util.List;

public interface Analyzer {

    //List<AnalyzerResult> analyzeDifficulty(List<AbstractSorter> sorters, List<Filler> fillers, int ... sizes);

    List<AnalyzerResult> analyzeDifficulty(int ... sizes);

}
