package net.consto.strategy;

import android.graphics.Color;

import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by moritz on 26.08.15.
 */
public class Resources {
    private  HashMap<String, Float> currentValue = new HashMap<>();
    private  HashMap<String, Integer> maxValue = new HashMap<>();
    private  HashMap<String, Float> change = new HashMap<>();

    public  float getCurrentOf(String name) {
        return currentValue.get(name);
    }
    public int getMaxValueOf(String name){
        return maxValue.get(name);
    }

    public  void addAmount(String name, float value) {
        if (currentValue.containsKey(name)) {
            if (currentValue.get(name) + value < maxValue.get(name)) {//currentValue can be negative; that's intended
                currentValue.put(name, currentValue.get(name) + value);
            }else{
                currentValue.put(name,new Float(maxValue.get(name)));
            }
        }
    }

    public void setChange(String res, float value) {
        change.put(res, value);
    }

    public  void addResource(String name, int maximumValue) {
        if (!currentValue.containsKey(name)) {
            currentValue.put(name, 0f);
            maxValue.put(name, maximumValue);
            change.put(name, 0f);
        }
    }

    public  void changeMaxValue(String name, int newValue) {
        maxValue.put(name, maxValue.get(name) + newValue);
        if (maxValue.get(name) < 0) {                           //Testign after changing might be discourage, but it is faster
            maxValue.put(name, maxValue.get(name) - newValue);
        }
    }

    public  int getResourceColor(String res) {
        if (currentValue.get(res) == 0 || currentValue.get(res).equals(maxValue.get(res))) {
            return Color.RED;
        }
        return Color.WHITE;
    }

    public  int getChangeColor(String res) {
        if (change.get(res) > 0) {
            return Color.GREEN;
        }
        if (change.get(res) < 0) {
            return Color.RED;
        }
        return Color.WHITE;
    }
    public  void addToMax(String res, int value){//TODO
        maxValue.put(res,maxValue.get(res)+value);
    }
}
