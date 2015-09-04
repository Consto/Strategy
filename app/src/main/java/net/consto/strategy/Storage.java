package net.consto.strategy;

import java.util.HashMap;

/**
 * Created by moritz on 30.08.15.
 */
public class Storage {
    public static OldGame getGame() {
        if (!false) {
            return createNewGame();
        }
        return null;
    }

    private static OldGame createNewGame() {
        Resources resources = new Resources();
        HashMap<BType, BuildingType> buildings = new HashMap<>();
        System.currentTimeMillis();
        resources.addResource(ResType.WOOD, 6000,"Wood");
        resources.addResource(ResType.CUTTEDWOOD, 1200000,"Cutted Wood");
        resources.addAmount(ResType.WOOD, 6000);
        buildings.put(BType.WOODCUTTER, Blueprint.createWoodCutter(resources));
        buildings.get(BType.WOODCUTTER).buildBuilding();
        return new OldGame(buildings, resources, System.currentTimeMillis());
    }

}
