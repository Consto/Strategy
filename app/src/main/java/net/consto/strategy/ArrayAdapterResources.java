package net.consto.strategy;

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

/**
 * Created by moritz on 04.09.15.
 */
public class ArrayAdapterResources extends ArrayAdapter<ResourceData> {
    private GameMaintenance gm;
    private Context mContext;
    private int layoutResourceId;
    private ArrayList<ResourceData> data = null;
    private ListenerBuildings listener;

    public ArrayAdapterResources(Context mContext, int layoutResourceId, ArrayList<ResourceData> data, GameMaintenance gameMaintenance) {

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
            //get the data
            ResourceData rowData=data.get(position);
            // inflate the layout
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            vRow = inflater.inflate(layoutResourceId, parent, false);

            TextView tvName=(TextView)vRow.findViewById(R.id.tvResName);
            TextView tvChange=(TextView)vRow.findViewById(R.id.tvResChange);
            TextView tvValue=(TextView)vRow.findViewById(R.id.tvResValue);
            TextView tvMax=(TextView)vRow.findViewById(R.id.tvResMax);

            tvName.setText(rowData.name);
            tvChange.setText(rowData.change.toString());
            tvValue.setText(rowData.current.toString());
         //   tvMax.setText(rowData.max);

        }



        return vRow;

    }
}
