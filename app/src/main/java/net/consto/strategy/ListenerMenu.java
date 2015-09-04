package net.consto.strategy;

import android.view.View;

import java.util.Objects;

/**
 * Created by moritz on 30.08.15.
 */
public class ListenerMenu implements View.OnClickListener {
    private Control p;


    public ListenerMenu(Control parent) {
        p = parent;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNewGame:
//                FragmentMenu.testAdd();
                break;
            case R.id.btnBuildings:
                GUI.showFragmentInMain("Buildings");
                break;
            case R.id.btnResources:
                GUI.showFragmentInMain("Resources");
            default:
                break;
        }
    }
}
