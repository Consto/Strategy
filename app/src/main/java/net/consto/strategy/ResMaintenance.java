package net.consto.strategy;

import java.util.ArrayList;

/**
 * Created by moritz on 27.08.15.
 */
public class ResMaintenance extends Thread {
    private Resources res;
    public long lastStartTime;
    private int dTime;
    private ArrayList<BuildingType> buildings=new ArrayList<>();        // To ArrayLists to avoid HashSet and save time
    private ArrayList<String> buildingNames=new ArrayList<>();


    public ResMaintenance(Resources res) {
        this.res = res;
        lastStartTime=System.currentTimeMillis();
        addBuildingType(Blueprint.createWoodCutter(res));
        buildings.get(0).buildBuilding();
    }

    public void run() {
        while (true) {
            dTime= (int) (System.currentTimeMillis()-lastStartTime);    //Time Stamp
            lastStartTime=System.currentTimeMillis();
            for(BuildingType bType:buildings) {
                bType.update(dTime);
            }
//            System.out.println("Wood: " +
//                    Tools.roundToString(res.getCurrentOf("Wood")) + "/" + res.getMaxValueOf("Wood") +
//                    " --- Cutted Wood: " +
//                    Tools.roundToString(res.getCurrentOf("Cutted Wood")) + "/" + res.getMaxValueOf("Cutted Wood"));

            try {
                sleep(200);                                             // pause between updates; makes the app cheaper(hopefully)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public BuildingType getBuildingType(String name){
        for(int i=0;i<buildingNames.size();i++){
            if(buildingNames.get(i).equals(name)){
                return buildings.get(i);
            }
        }
        return null;
    }
    public void addBuildingType(BuildingType bType){
        buildings.add(bType);
        buildingNames.add(bType.getName());
    }
}
