package net.consto.strategy;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Control extends Activity {

    public GameMaintenance gameMaintenance;

    private Button btnTest;



    private View.OnClickListener listenerMenu = new ListenerMenu(this);



    @Override


    protected void onCreate(Bundle savedInstanceState) {
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);


        GUI.setMainContext(this);
        GUI.setParent(this);
        GUI.setUpFragments();
        GUI.setUpBtn();


        gameMaintenance = new GameMaintenance(Storage.getGame());
        gameMaintenance.start();

    }




}
