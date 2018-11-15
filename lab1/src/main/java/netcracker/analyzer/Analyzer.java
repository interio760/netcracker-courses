package netcracker.analyzer;

import java.util.Collections;
import java.util.List;

public interface Analyzer {

    List<AnalyzerResult> analyze(List<AnalyzeSet> analyzeSets, int size);

    default List<AnalyzerResult> analyze(AnalyzeSet analyzeSets, int size){
        return analyze(Collections.singletonList(analyzeSets), size);
    }

}
