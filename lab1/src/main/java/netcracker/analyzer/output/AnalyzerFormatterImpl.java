package netcracker.analyzer.output;

import netcracker.analyzer.AnalyzerResult;
import netcracker.fillers.Option;
import netcracker.sorters.HalfDivisionSort;

import java.util.List;

public class AnalyzerFormatterImpl implements AnalyzerFormatter {

    @Override
    public String format(List<AnalyzerResult> resultList) {
        StringBuilder resultBuilder = new StringBuilder();
        String lastSorterName = "";
        for(AnalyzerResult result : resultList){
            String sorterName = result.getSorter().getClass().getSimpleName();

            if(result.getSorter() instanceof HalfDivisionSort) {
                HalfDivisionSort sort = (HalfDivisionSort) result.getSorter();
                sorterName = sorterName + " (" + sort.getSorter().getClass().getSimpleName() +")";
            }

            if(!lastSorterName.equals(sorterName)){
                resultBuilder.append("--------------------------\nTesting: ");
                resultBuilder.append(sorterName);
                resultBuilder.append("\n\n");
            }
            else{
                resultBuilder.append("\n");
            }

            resultBuilder.append("Filler: ");
            resultBuilder.append(result.getFiller().getClass().getSimpleName());

            if(!result.getFiller().getOptions().isEmpty()){
                for(Option option : result.getFiller().getOptions()){
                    resultBuilder.append(" (");
                    resultBuilder.append(option.getClass().getSimpleName());
                    resultBuilder.append(")");
                }
            }

            resultBuilder.append("\nElapsed time: ");
            resultBuilder.append(result.getElapsedTime());
            resultBuilder.append(" ms.\n");
            lastSorterName = sorterName;

        }
        return resultBuilder.toString();
    }

}
