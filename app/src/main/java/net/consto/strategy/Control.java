package net.consto.strategy;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class Control extends Activity{
    Resources resources = new Resources();

    private ResMaintenance resMaintenance;

    private Button btnTest;
    private View btnMenu;
    private View fDetails;


    private View vMain;
    private FragmentManager fm;
    private Fragment fragMenu = new FragmentMenu();
    private Fragment fragDetails = new FragmentDetails();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);


        btnTest = (Button) findViewById(R.id.btnTest);
        fDetails=findViewById(R.id.containerDetails);
        btnMenu = findViewById(R.id.btnMenu);
        fm = getFragmentManager();
        vMain = findViewById(R.id.FrameMain);

        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDetails(null);
            }
        });
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fragMenu.isHidden()) {
                    showFragment(fragMenu);
                } else {
                    hideFragment(fragMenu);
                }
            }
        });
        fDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });




        fm.beginTransaction().add(R.id.containerMenu, fragMenu).hide(fragMenu).commit();
        fm.beginTransaction().add(R.id.containerDetails, fragDetails).hide(fragDetails).commit();


        vMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!fragMenu.isHidden()) {
                    hideFragment(fragMenu);
                }
                if (!fragDetails.isHidden()) {
                    hideFragment(fragDetails);
                }
            }
        });

        resources.addResource("Wood", 6000);
        resources.addResource("Cutted Wood", 1200000);
        resources.addAmount("Wood", 6000);
        resMaintenance = new ResMaintenance(resources);
        resMaintenance.start();

    }

    private void showFragment(Fragment frag) {
        if (frag.isHidden()) {
            fm.beginTransaction().show(frag).commit();
        }
    }

    private void hideFragment(Fragment frag) {
        if (!frag.isHidden()) {
            fm.beginTransaction().hide(frag).commit();
        }

    }

    private void showDetails(BuildingType bType) {
        hideFragment(fragMenu);
        showFragment(fragDetails);
    }


}
