package com.cardinalsolutions.eventbus.UI;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.cardinalsolutions.eventbus.mainBus.FootballBus;
import com.cardinalsolutions.eventbus.FootballBusEvents.OnFootballTeamCLickedEvent;
import com.cardinalsolutions.eventbus.R;
import com.squareup.otto.Subscribe;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadFragment(savedInstanceState);
        FootballBus.getInstance().register(this);
    }

    private void loadFragment(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new MainListFragment())
                    .commit();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        FootballBus.getInstance().unregister(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Subscribe
    public void onTeamClicked(OnFootballTeamCLickedEvent event)
    {
        TeamDetailDialogFragment fragment = TeamDetailDialogFragment.newInstance(event.teamName);

        fragment.show(getFragmentManager(), "DIALOG_FRAGMENT");
    }
}
