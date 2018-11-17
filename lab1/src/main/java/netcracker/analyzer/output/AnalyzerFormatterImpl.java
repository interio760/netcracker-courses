package netcracker.analyzer.output;

import netcracker.analyzer.AnalyzerResult;

import java.util.List;
import java.util.Map;

public class AnalyzerFormatterImpl implements AnalyzerFormatter {

    @Override
    public String format(List<AnalyzerResult> resultList) {
        if(resultList == null || resultList.isEmpty()) return "";
        StringBuilder resultBuilder = new StringBuilder();
        String lastSorterName = "";
        for(AnalyzerResult result : resultList){
            String sorterName = result.getSorter().toString();

            if(!lastSorterName.equals(sorterName)){
                resultBuilder
                        .append("--------------------------\nTesting: ")
                        .append(sorterName)
                        .append("\n\n");
            }
            else{
                resultBuilder.append("\n");
            }

            resultBuilder
                    .append("Filler: ")
                    .append(result.getFiller())
                    .append("\nElapsed time: ");

            Map<Integer, Long> elapsedTime = result.getElapsedTime();

            for(Integer size : elapsedTime.keySet()){
                resultBuilder
                        .append("\nArray size: ")
                        .append(size)
                        .append(" | Time: ")
                        .append(elapsedTime.get(size))
                        .append(" ms.");
            }

            resultBuilder.append("\n");

            lastSorterName = sorterName;

        }
        return resultBuilder.toString();
    }

}
