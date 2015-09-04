package net.consto.strategy;

/**
 * Created by moritz on 29.08.15.
 */

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentMenu extends Fragment {

    private View view;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_menu, container, false);
        GUI.setFragViewMenu(view);




        //Inflate the layout for this fragment
        return view;
    }
}

