package net.consto.strategy;

import java.util.HashMap;

/**
 * Created by moritz on 01.09.15.
 */
public class OldGame {
    public HashMap<BType,BuildingType> buildings=new HashMap<>();
    public Resources resources;
    public long lastTimeStamp;
    public OldGame(HashMap<BType, BuildingType> buildings, Resources resources, long lastTimeStamp){
        this.buildings=buildings;
        this.resources=resources;
        this.lastTimeStamp=lastTimeStamp;
    }
}
