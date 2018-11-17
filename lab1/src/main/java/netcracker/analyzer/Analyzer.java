package netcracker.analyzer;

import netcracker.fillers.Filler;
import netcracker.sorters.Sorter;

import java.util.List;

public interface Analyzer {

    List<AnalyzerResult> analyzeDifficulty(List<Sorter> sorters, List<Filler> fillers,  int ... sizes);

    List<AnalyzerResult> analyzeDifficulty(List<Filler> fillers,  int ... sizes);

}
