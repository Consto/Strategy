package net.consto.strategy;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by moritz on 01.09.15.
 */
public class FragmentBuildings extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_buildings, container, false);
        GUI.setFragViewBuilding(view);
        return view;
    }
}
