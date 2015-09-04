package net.consto.strategy;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by moritz on 26.08.15.
 */
public class BuildingType {
    private String name;    //just for GUI
    private BType bType;    //for Program
    private int amount;     //# of buildings of this type
    private Boolean buildable = false;
    private HashMap<ResType, Integer> buildCosts = new HashMap<>();
    private HashMap<ResType, Float> costs = new HashMap<>();
    private HashMap<ResType, Float> products = new HashMap<>();
    private HashMap<ResType, Integer> permanentProducts = new HashMap<>();
    private HashMap<ResType, Float> uniqueProducts = new HashMap<>();
    private Resources res;
    private float efficient;

    public BuildingType(String name,Resources res,BType bType) {
        this.res=res;
        this.name = name;
        this.bType = bType;
        amount = 0;
    }

    public void update(int deltaTime) {

        efficient = calculateEfficient();
        for (Map.Entry en : costs.entrySet()) {
            res.addAmount((ResType) en.getKey(), -calculateCosts(en.getKey(), deltaTime));
        }
        for (Map.Entry en : products.entrySet()) {
            res.addAmount((ResType) en.getKey(), calculateProducts(en.getKey(), deltaTime));
        }
        buildable = calculateBuildable();
    }

    public void addBuildResource(ResType name, int amount) {
        buildCosts.put(name, amount);
    }

    public void removeBuildResourde(ResType name) {
        if (buildCosts.containsKey(name)) {
            buildCosts.remove(name);
        }
    }

    public void addCostResource(ResType name, float amount) {
        costs.put(name, amount);
    }

    public void removeCostResourde(String name) {
        if (costs.containsKey(name)) {
            costs.remove(name);
        }
    }

    public HashMap<ResType, Float> getCosts() {
        return costs;
    }

    public void addProduct(ResType name, float amount) {

        products.put(name, amount);
    }

    public void removeProduct(ResType name) {
        if (products.containsKey(name)) {
            products.remove(name);
        }
    }

    public HashMap<ResType, Float> getProducts() {
        return products;
    }

    public void buildBuilding() {
        if (calculateBuildable()) {
            for (Map.Entry en : buildCosts.entrySet()) {
                res.addAmount((ResType) en.getKey(), -Float.valueOf(en.getValue().toString()));
            }
            for (Map.Entry en : uniqueProducts.entrySet()) {
                res.addAmount((ResType)en.getKey(), Float.valueOf(en.getValue().toString()));
            }
            for (Map.Entry en : permanentProducts.entrySet()) {
                res.addToMax((ResType)en.getKey(), Integer.valueOf(en.getValue().toString()));
            }
            amount++;
        }
    }

    public void removeBuilding() {
        if (amount > 0) {
            for (Map.Entry en : buildCosts.entrySet()) {
                res.addAmount((ResType)en.getKey(), Float.valueOf(en.getValue().toString()) / 2);
            }
            for (Map.Entry en : permanentProducts.entrySet()) {
                res.addToMax((ResType)en.getKey(), -Integer.valueOf(en.getValue().toString()));
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
    public BType getBType(){
        return bType;
    }
    public int getAmount(){
        return amount;
    }

    private Boolean calculateBuildable() {
        for (Map.Entry en : buildCosts.entrySet()) {
            if (Float.valueOf(en.getValue().toString()) > res.getCurrentOf((ResType)en.getKey())) {
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
            if (Float.valueOf(en.getValue().toString()) > res.getCurrentOf((ResType)en.getKey())) {
                tmpEfficient = res.getCurrentOf((ResType)en.getKey()) / Float.valueOf(en.getValue().toString());
                if (tmpEfficient < minEfficient) {
                    minEfficient = tmpEfficient;
                }
            }
        }
        return minEfficient;
    }

}
