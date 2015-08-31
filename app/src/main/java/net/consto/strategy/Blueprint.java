package net.consto.strategy;

/**
 * Created by moritz on 27.08.15.
 */
public class Blueprint {
    private static final float MS_IN_MINUTE=60000;
    public static BuildingType createWoodCutter(Resources res){
        BuildingType bt=new BuildingType("Wood Cutter",res);
        bt.addBuildResource("Wood",200);
        bt.addCostResource("Wood", 1000 / MS_IN_MINUTE);
        bt.addProduct("Cutted Wood", 20000000/MS_IN_MINUTE);
        bt.buildBuilding();
        return bt;
    }

}
