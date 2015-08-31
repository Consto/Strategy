package net.consto.strategy;

import android.view.View;

import java.util.Objects;

/**
 * Created by moritz on 30.08.15.
 */
public class ListenerMenu implements View.OnClickListener {
    private Object p;

    public ListenerMenu(Object parent) {
        p = parent;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case 1:
                break;
            default:
                break;
        }
    }
}
