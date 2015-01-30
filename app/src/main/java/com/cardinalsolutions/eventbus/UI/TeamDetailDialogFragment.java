package com.cardinalsolutions.eventbus.UI;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.cardinalsolutions.eventbus.mainBus.FootballBus;
import com.cardinalsolutions.eventbus.FootballBusEvents.OnTeamDetailModifiedEvent;
import com.cardinalsolutions.eventbus.R;
import com.squareup.otto.Bus;

public class TeamDetailDialogFragment extends DialogFragment {

    public static final String EXTRA_TEAM_NAME = "EXTRA_TEAM_NAME";
    private EditText teamNameEditText;
    private Button saveButton;
    private String teamName;

    public static TeamDetailDialogFragment newInstance(String teamName) {
        TeamDetailDialogFragment fragment = new TeamDetailDialogFragment();
        Bundle args = new Bundle();
        args.putString(EXTRA_TEAM_NAME, teamName);
        fragment.setArguments(args);

        return fragment;
    }

    public TeamDetailDialogFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState == null)
        {
            teamName = getArguments().getString(EXTRA_TEAM_NAME);
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog d = super.onCreateDialog(savedInstanceState);
        d.setTitle("Team Details");
        return  d;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_team_detail_dialog, container, false);
        teamNameEditText = (EditText)v.findViewById(R.id.team_name);
        teamNameEditText.setText(teamName);
        saveButton = (Button)v.findViewById(R.id.save);
        saveButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Bus footballBus = FootballBus.getInstance();
                OnTeamDetailModifiedEvent newTeamName = new OnTeamDetailModifiedEvent(teamName, teamNameEditText.getText().toString());
                footballBus.post(newTeamName);
                dismiss();
            }
        });
        return  v;
    }

}
