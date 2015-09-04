package net.consto.strategy;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

/**
 * Created by moritz on 02.09.15.
 */
public class GUI {
    private static Context parentContext;
    private static Control p;

    private static View fragViewMenu;
    private static View fragViewBuilding;
    private static View fragViewDetails;

    private static View fDetails;
    private static View fMenu;
    private static View fMain;

    private static View.OnClickListener listenerMenu;

    private static FragmentManager fm;
    private static Fragment fragBuildings = new FragmentBuildings();
    private static Fragment fragResources = new FragmentResources();
    private static Fragment fragMenu = new FragmentMenu();
    private static Fragment fragDetails = new FragmentDetails();


    private static ListView lvBuildings;
    private static ListView lvResources;
    private static ArrayAdapterBuilding adapterBuilding;
    private static ArrayAdapterResources adapterResources;

    private static View btnMenu;

    private static View btnShowBuildings;
    private static View btnShowResources;
    private static View btnShowRanking;
    private static View btnShowPreferences;
    private static View btnShowNewGame;

    //Initialize
    public static void setMainContext(Context context) {
        parentContext = context;
    }

    public static void setParent(Control parent) {
        p = parent;
        listenerMenu = new ListenerMenu(p);
    }

    public static void setFragViewMenu(View view) {
        fragViewMenu = view;

        btnShowBuildings = view.findViewById(R.id.btnBuildings);
        btnShowResources = view.findViewById(R.id.btnResources);
        btnShowRanking = view.findViewById(R.id.btnRanking);
        btnShowPreferences = view.findViewById(R.id.btnPreferences);
        btnShowNewGame = view.findViewById(R.id.btnNewGame);


        btnShowBuildings.setOnClickListener(listenerMenu);
        btnShowResources.setOnClickListener(listenerMenu);
        btnShowRanking.setOnClickListener(listenerMenu);
        btnShowPreferences.setOnClickListener(listenerMenu);
        btnShowNewGame.setOnClickListener(listenerMenu);


        //trial
//        ListView listViewItems = new ListView(p);
//        listViewItems.setAdapter(adapterBuilding);
//        lTest.addView(listViewItems);//not found yet

    }

    public static void setFragViewBuilding(View view) {
        fragViewBuilding = view;
        lvBuildings = (ListView) view.findViewById(R.id.lvBuildigs);
        adapterBuilding = new ArrayAdapterBuilding(p, R.layout.list_view_row_building, p.gameMaintenance.getBuildings(), p.gameMaintenance);
        lvBuildings.setAdapter(adapterBuilding);
    }

    public static void setFragViewResources(View view) {
        fragViewBuilding = view;
        lvResources = (ListView) view.findViewById(R.id.lvResources);
        adapterResources = new ArrayAdapterResources(p, R.layout.list_view_row_resources, p.gameMaintenance.getResources(), p.gameMaintenance);
        lvResources.setAdapter(adapterResources);
    }


    public static void setFragViewDetails(View view) {
        fragViewDetails = view;


    }

    public static void setUpFragments() {
        fDetails = p.findViewById(R.id.containerDetails);
        fMenu = p.findViewById(R.id.containerMenu);
        fMain = p.findViewById(R.id.FrameMain);

        fDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        fMain.setOnClickListener(new View.OnClickListener() {
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

        fm = p.getFragmentManager();
        fm.beginTransaction().add(R.id.FrameMain, fragBuildings).hide(fragBuildings).commit();
        fm.beginTransaction().add(R.id.FrameMain, fragResources).hide(fragResources).commit();
        fm.beginTransaction().add(R.id.containerMenu, fragMenu).commit();
        fm.beginTransaction().add(R.id.containerDetails, fragDetails).hide(fragDetails).commit();

    }

    public static void setUpBtn() {
        btnMenu = p.findViewById(R.id.btnMenu);
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
    }

    //public
    public static void showFragmentInMain(String fragName) {
        if ("Buildings".equals(fragName)) {
            hideFragment(fragResources);
            showFragment(fragBuildings);
        }
        if ("Resources".equals(fragName)) {
            updateResourcesList();
            hideFragment(fragBuildings);
            showFragment(fragResources);
        }
    }

    //private
    private static void showFragment(Fragment frag) {
        if (frag.isHidden()) {
            fm.beginTransaction().show(frag).commit();
        }
    }

    private static void hideFragment(Fragment frag) {
        if (!frag.isHidden()) {
            fm.beginTransaction().hide(frag).commit();
        }

    }


    public static void updateBuildingList() {
        adapterBuilding.notifyDataSetChanged();
    }

    public static void updateResourcesList() {
        adapterResources.notifyDataSetChanged();
    }

    public static void showDetails(BType name) {
        BuildingType buildingType = p.gameMaintenance.getBuildingType(name);
        hideFragment(fragMenu);
        showFragment(fragDetails);
    }


}
