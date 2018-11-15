package netcracker.analyzer;

import netcracker.fillers.Filler;
import netcracker.sorters.Sorter;

import java.util.List;

public class AnalyzeSet {

    private final List<Sorter> sorterList;
    private final List<Filler> fillerList;

    public AnalyzeSet(List<Sorter> sorterList, List<Filler> fillerList) {
        this.sorterList = sorterList;
        this.fillerList = fillerList;
    }

    public List<Sorter> getSorterList() {
        return sorterList;
    }

    public List<Filler> getFillerList() {
        return fillerList;
    }
}
