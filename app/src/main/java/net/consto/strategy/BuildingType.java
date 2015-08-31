package net.consto.strategy;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by moritz on 26.08.15.
 */
public class BuildingType {
    private String name;    //just for GUI
    private int amount;     //# of buildings of this type
    private Boolean buildable = false;
    private HashMap<String, Integer> buildCosts = new HashMap<>();
    private HashMap<String, Float> costs = new HashMap<>();
    private HashMap<String, Float> products = new HashMap<>();
    private HashMap<String, Integer> permanentProducts = new HashMap<>();
    private HashMap<String, Float> uniqueProducts = new HashMap<>();
    private Resources res;
    private float efficient;

    public BuildingType(String name,Resources res) {
        this.res=res;
        this.name = name;
        amount = 0;
    }

    public void update(int deltaTime) {

        efficient = calculateEfficient();
        for (Map.Entry en : costs.entrySet()) {
            res.addAmount(en.getKey().toString(), -calculateCosts(en.getKey(), deltaTime));
        }
        for (Map.Entry en : products.entrySet()) {
            res.addAmount(en.getKey().toString(), calculateProducts(en.getKey(), deltaTime));
        }
        buildable = calculateBuildable();
    }

    public void addBuildResource(String name, int amount) {
        buildCosts.put(name, amount);
    }

    public void removeBuildResourde(String name) {
        if (buildCosts.containsKey(name)) {
            buildCosts.remove(name);
        }
    }

    public void addCostResource(String name, float amount) {
        costs.put(name, amount);
    }

    public void removeCostResourde(String name) {
        if (costs.containsKey(name)) {
            costs.remove(name);
        }
    }

    public HashMap<String, Float> getCosts() {
        return costs;
    }

    public void addProduct(String name, float amount) {

        products.put(name, amount);
    }

    public void removeProduct(String name) {
        if (products.containsKey(name)) {
            products.remove(name);
        }
    }

    public HashMap<String, Float> getProducts() {
        return products;
    }

    public void buildBuilding() {
        if (calculateBuildable()) {
            for (Map.Entry en : buildCosts.entrySet()) {
                res.addAmount(en.getKey().toString(), -Float.valueOf(en.getValue().toString()));
            }
            for (Map.Entry en : uniqueProducts.entrySet()) {
                res.addAmount(en.getKey().toString(), Float.valueOf(en.getValue().toString()));
            }
            for (Map.Entry en : permanentProducts.entrySet()) {
                res.addToMax(en.getKey().toString(), Integer.valueOf(en.getValue().toString()));
            }
            amount++;
        }
    }

    public void destroyBuilding() {
        if (amount > 0) {
            for (Map.Entry en : buildCosts.entrySet()) {
                res.addAmount(en.getKey().toString(), Float.valueOf(en.getValue().toString()) / 2);
            }
            for (Map.Entry en : permanentProducts.entrySet()) {
                res.addToMax(en.getKey().toString(), -Integer.valueOf(en.getValue().toString()));
            }
            amount--;
        }
    }

    public float getEfficient() {
        return efficient;
    }

    public Boolean isBuildable() {
        return buildable;
    }
    public String getName(){
        return name;
    }

    private Boolean calculateBuildable() {
        for (Map.Entry en : buildCosts.entrySet()) {
            if (Float.valueOf(en.getValue().toString()) > res.getCurrentOf(en.getKey().toString())) {
                return false;
            }
        }
        return true;
    }

    private float calculateCosts(Object name, int time) {
        return amount * efficient * time * costs.get(name);
    }

    private float calculateProducts(Object name, int time) {
        return amount * efficient * time * products.get(name);
    }

    private float calculateEfficient() {
        float minEfficient = 1;
        float tmpEfficient;
        for (Map.Entry en : costs.entrySet()) {
            if (Float.valueOf(en.getValue().toString()) > res.getCurrentOf(en.getKey().toString())) {
                tmpEfficient = res.getCurrentOf(en.getKey().toString()) / Float.valueOf(en.getValue().toString());
                if (tmpEfficient < minEfficient) {
                    minEfficient = tmpEfficient;
                }
            }
        }
        return minEfficient;
    }

}
