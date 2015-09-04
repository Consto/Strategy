package net.consto.strategy;

/**
 * Created by moritz on 04.09.15.
 */
public class ResourceData {
    public String name;
    public Float change;
    public Float current;
    public int max;
    public ResourceData(String name,Float change,Float current,int max){
        this.name=name;
        this.change=change;
        this.current=current;
        this.max=max;
    }
}
