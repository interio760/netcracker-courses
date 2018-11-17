package netcracker;

import netcracker.analyzer.output.*;
import netcracker.analyzer.*;
import netcracker.fillers.*;
import netcracker.sorters.*;

import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args) {
        Sorter arraysSort = new ArraysSort();
        Sorter bteSort = new BubbleSortBtE();
        Sorter etbSort = new BubbleSortEtB();
        Sorter quickSort = new QuickSort();

        Filler sortedAsc = new AscFiller(quickSort);

        Filler sortedAscWithRandom = new AscFiller(quickSort);
        sortedAscWithRandom.addOption(new LastRandomOption(100, 200));

        Filler sortedDesc = new DescFiller(quickSort);

        Filler trueRandom = new RandomFiller();

        AnalyzerImpl analyzer = new AnalyzerImpl();

/*        List<AnalyzerResult> resultList = analyzer.analyzeDifficulty(
                Arrays.asList(
                        arraysSort,
                        bteSort,
                        etbSort,
                        quickSort,
                        new HalfDivisionSort(arraysSort),
                        new HalfDivisionSort(bteSort),
                        new HalfDivisionSort(etbSort),
                        new HalfDivisionSort(quickSort)
                ),

                Arrays.asList(
                        sortedAsc,
                        sortedAscWithRandom,
                        sortedDesc,
                        trueRandom
                )
                , 1000, 10000);*/

        List<AnalyzerResult> resultList = analyzer.analyzeDifficulty(
                Arrays.asList(
                        sortedAsc,
                        sortedAscWithRandom,
                        sortedDesc,
                        trueRandom
                )
                , 1000, 10000, 20000);

        AnalyzerFormatterImpl analyzerFormatter = new AnalyzerFormatterImpl();
        String report = analyzerFormatter.format(resultList);

        System.out.println(report);
    }
}