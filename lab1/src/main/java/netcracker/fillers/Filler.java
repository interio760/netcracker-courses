package netcracker.fillers;

import java.util.*;

public abstract class Filler {

    private final TreeSet<Option> optionList = new TreeSet<>
            (Comparator.comparingInt(Option::getPriority));

    public abstract void fill(int[] arr, int min, int max);

    protected void applyOptions(int[] arr){
        for(Option opt : optionList){
            opt.execute(arr);
        }
    }

    public void addOption(Option option){
        if(option == null) return;
        optionList.add(option);
    }

    protected String optionsToString(){
        if(optionList.isEmpty()) return "";
        StringBuilder builder = new StringBuilder(" (");
        for(Option option : optionList){
            builder.append(option);
            builder.append(", ");
        }
        int length = builder.length();
        builder.replace(length - 2, length - 1, ")");
        return builder.toString();
    }

    public void removeOption(Option option){
        optionList.remove(option);
    }

    public Set<Option> getOptions(){
        return optionList;
    }

}
