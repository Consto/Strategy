package net.consto.strategy;

import android.util.Pair;
import android.view.View;

/**
 * Created by moritz on 03.09.15.
 */
public class ListenerBuildings implements View.OnClickListener {
    private GameMaintenance gm;

    public ListenerBuildings(GameMaintenance gm) {
        this.gm = gm;
    }

    @Override
    public void onClick(View v) {

        switch (((Pair<BType, String>) v.getTag()).second) {
            case "ADD":
                gm.addBuilding(((Pair<BType, String>) v.getTag()).first);
                break;
            case "REMOVE":
                gm.removeBuilding(((Pair<BType, String>) v.getTag()).first);
                break;
            case "DETAILS":
                GUI.showDetails(((Pair<BType, String>) v.getTag()).first);
                break;
            default:

                break;
        }
    }

}
