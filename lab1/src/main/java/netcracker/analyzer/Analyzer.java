package netcracker.analyzer;

import netcracker.fillers.Filler;
import netcracker.sorters.Sorter;

import java.util.List;

public interface Analyzer {

    List<AnalyzerResult> analyze(List<Sorter> fillers, List<Filler> sorters, int size);

}
