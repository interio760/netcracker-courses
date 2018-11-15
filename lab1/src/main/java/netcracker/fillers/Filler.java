package netcracker.fillers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Filler {

    private final List<Option> optionList = new ArrayList<>();
    protected ThreadLocalRandom random = ThreadLocalRandom.current();

    public abstract void fill(int[] arr, int min, int max);

    protected void applyOptions(int[] arr){
        optionList.sort(Comparator.comparingInt(Option::getPriority));
        for(Option opt : optionList){
            opt.execute(arr);
        }
    }

    public void addOption(Option option){
        if(option == null) return;
        optionList.add(option);
    }

    public void removeOption(Option option){
        optionList.remove(option);
    }

    public List<Option> getOptions(){
        return optionList;
    }

}
