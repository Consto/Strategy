package net.consto.strategy;

/**
 * Created by moritz on 02.09.15.
 */

import android.app.Activity;
import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ArrayAdapterBuilding extends ArrayAdapter<BuildingType> {
    private GameMaintenance gm;
    private Context mContext;
    private int layoutResourceId;
    private ArrayList<BuildingType> data = null;
    private ListenerBuildings listener;

    public ArrayAdapterBuilding(Context mContext, int layoutResourceId, ArrayList<BuildingType> data, GameMaintenance gameMaintenance) {

        super(mContext, layoutResourceId, data);

        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.data = data;
        this.gm = gameMaintenance;
        listener = new ListenerBuildings(gm);
    }

    @Override
    public View getView(int position, View vRow, ViewGroup parent) {

        if (vRow == null) {
            // inflate the layout
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            vRow = inflater.inflate(layoutResourceId, parent, false);
        }

        BuildingType buildingType = data.get(position);

        // get the TextView and then set the text (item name) and tag (item ID) values
        TextView tvBuildingName = (TextView) vRow.findViewById(R.id.tvBuildingName);
        TextView tvBuildingAmount = (TextView) vRow.findViewById(R.id.tvBuildingAmount);
        Button btnAddBuilding = (Button) vRow.findViewById(R.id.btnAddBuilding);
        Button btnRemoveBuilding = (Button) vRow.findViewById(R.id.btnRemoveBuilding);
        View btnShowdetails= vRow.findViewById(R.id.btnShowDetails);

        btnAddBuilding.setOnClickListener(listener);
        btnRemoveBuilding.setOnClickListener(listener);
        btnShowdetails.setOnClickListener(listener);


        btnAddBuilding.setTag(new Pair<BType, String>(buildingType.getBType(), "ADD"));
        btnRemoveBuilding.setTag(new Pair<BType, String>(buildingType.getBType(), "REMOVE"));
        btnShowdetails.setTag(new Pair<>(buildingType.getBType(),"DETAILS"));
        btnAddBuilding.setEnabled(buildingType.isBuildable());
        if (buildingType.getAmount() < 1) {
            btnRemoveBuilding.setEnabled(false);
        } else {
            btnRemoveBuilding.setEnabled(true);
        }

        tvBuildingName.setText(buildingType.getName());
        tvBuildingAmount.setText(String.valueOf(buildingType.getAmount()));


        return vRow;

    }


}
