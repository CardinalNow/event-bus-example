package com.cardinalsolutions.eventbus.UI;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.R;
import android.widget.ListView;

import com.cardinalsolutions.eventbus.mainBus.FootballBus;
import com.cardinalsolutions.eventbus.FootballBusEvents.OnFootballTeamCLickedEvent;
import com.cardinalsolutions.eventbus.FootballBusEvents.OnTeamDetailModifiedEvent;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;

public class MainListFragment extends ListFragment {

    private ArrayList<String> teams = new ArrayList<String>();
    private ArrayAdapter<String> adapter;


    public MainListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FootballBus.getInstance().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        FootballBus.getInstance().unregister(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(com.cardinalsolutions.eventbus.R.layout.fragment_main, container, false);

        teams.add("Baltimore Ravens");
        teams.add("Cincinnati Bengals");
        teams.add("Cleveland Browns");
        teams.add("Pittsburgh Steelers");

        adapter = new ArrayAdapter<String>(getActivity(), R.layout.simple_list_item_1, teams);

        setListAdapter(adapter);

        return rootView;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Bus bus = FootballBus.getInstance();
        bus.post(new OnFootballTeamCLickedEvent(teams.get(position)));
    }

    @Subscribe
    public void onTeamNameChanged(OnTeamDetailModifiedEvent event)
    {
        for (int x = 0; x < teams.size(); ++x)
        {
            if(teams.get(x).equalsIgnoreCase(event.oldTeamName))
            {
                teams.set(x, event.teamName);
            }
        }
        adapter.notifyDataSetChanged();
    }

}