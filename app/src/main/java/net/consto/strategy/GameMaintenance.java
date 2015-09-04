package net.consto.strategy;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by moritz on 27.08.15.
 */
public class GameMaintenance extends Thread {
    public boolean wait = false;
    public boolean waiting = false;

    private Resources res;
    private ArrayList<ResourceData> resData=new ArrayList<>();
    public long lastStartTime;
    private int dTime;
    private ArrayList<BuildingType> buildings = new ArrayList<>();        // To ArrayLists to avoid HashSet and save time
    private ArrayList<BType> buildingNames = new ArrayList<>();


    public GameMaintenance(OldGame o) {
        this.res = o.resources;
        this.lastStartTime = o.lastTimeStamp;
        for (Map.Entry en : o.buildings.entrySet()) {
            this.buildings.add((BuildingType) en.getValue());
            this.buildingNames.add((BType) en.getKey());
        }


    }

    public void run() {

        while (true) {
            dTime = (int) (System.currentTimeMillis() - lastStartTime);    //Time Stamp
            lastStartTime = System.currentTimeMillis();

            resData=res.getResourceData();
            System.out.println("testa"+resData.get(0).current);

            for (BuildingType bType : buildings) {
                bType.update(dTime);
            }
            if (wait) {

                waiting = true;
                while (waiting) {
                    try {
                        sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            } else {
                try {
                    sleep(200);                                             // pause between updates; makes the app cheaper(hopefully)
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

    }


    public BuildingType getBuildingType(BType name) {
        for (int i = 0; i < buildingNames.size(); i++) {
            if (buildingNames.get(i).equals(name)) {
                return buildings.get(i);
            }
        }
        return null;
    }

    public void addBuildingType(BType name, BuildingType bType) {
        buildings.add(bType);
        buildingNames.add(name);
    }

    public void addBuilding(BType name) {
        wait = true;
        while (!waiting) {
            try {
                sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        getBuildingType(name).buildBuilding();
        GUI.updateBuildingList();
        waiting = false;
        wait = false;
    }

    public void removeBuilding(BType name) {
        wait = true;
        while (!waiting) {
            try {
                sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        getBuildingType(name).removeBuilding();
        GUI.updateBuildingList();
        waiting = false;
        wait = false;
    }

    public ArrayList<BuildingType> getBuildings() {
        return buildings;
    }
    public ArrayList<ResourceData> getResources(){

        return resData;
    }
}
