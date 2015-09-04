package net.consto.strategy;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by moritz on 26.08.15.
 */
public class Resources {
//    private HashMap<ResType, Float> currentValue = new HashMap<>();
//    private HashMap<ResType, Integer> maxValue = new HashMap<>();
//    private HashMap<ResType, Float> change = new HashMap<>();

    private ArrayList<String> nameGUI=new ArrayList<>();
    private ArrayList<ResType> name = new ArrayList<>();
    private ArrayList<Float> currentValue = new ArrayList<>();
    private ArrayList<Integer> maxValue = new ArrayList<>();
    private ArrayList<Float> change = new ArrayList<>();

    public float getCurrentOf(ResType name) {
        return currentValue.get(getIndex(name));
    }

    public int getMaxValueOf(ResType name) {
        return maxValue.get(getIndex(name));
    }

    public void addAmount(ResType name, float value) {
        int i = getIndex(name);
        if (currentValue.get(i) + value < maxValue.get(i)) {//currentValue can be negative; that's intended
            currentValue.set(i, currentValue.get(i) + value);
        } else {
            currentValue.set(i, new Float(maxValue.get(i)));
        }

    }

    public void setChange(ResType res, float value) {
        change.set(getIndex(res), value);
    }

    public void addResource(ResType name, int maximumValue,String nameGUI) {
        this.name.add(name);
        this.currentValue.add(0f);
        this.maxValue.add(maximumValue);
        this.change.add(0f);
        this.nameGUI.add(nameGUI);

    }

    public void addToMax(ResType name, int newValue) {
        int i=getIndex(name);
        maxValue.set(i, maxValue.get(i) + newValue);
        if (maxValue.get(i) < 0) {                           //Testign after changing might be discourage, but it is faster
            maxValue.set(i, maxValue.get(i) - newValue);
        }
    }

    public int getResourceColor(ResType res) {
        int i=getIndex(res);
        if (currentValue.get(i) == 0 || currentValue.get(i).equals(maxValue.get(i))) {
            return Color.RED;
        }
        return Color.WHITE;
    }

    public int getChangeColor(ResType res) {
        int i=getIndex(res);
        if (change.get(i) > 0) {
            return Color.GREEN;
        }
        if (change.get(i) < 0) {
            return Color.RED;
        }
        return Color.WHITE;
    }

    public ArrayList<ResourceData> getResourceData() {
        ArrayList<ResourceData> data = new ArrayList<>();
        for (int i=0;i<name.size();i++) {
            data.add(new ResourceData(
                    nameGUI.get(i),
                    change.get(i),
                    currentValue.get(i),
                    maxValue.get(i)));
        }
        return data;
    }

    private int getIndex(ResType name) {
        for (int i = 0; i < this.name.size(); i++) {
            if (this.name.get(i) == name) {
                return i;
            }
        }
        return -1;
    }
}
