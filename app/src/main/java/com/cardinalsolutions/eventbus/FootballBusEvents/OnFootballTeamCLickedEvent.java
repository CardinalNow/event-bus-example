package com.cardinalsolutions.eventbus.FootballBusEvents;

public class OnFootballTeamCLickedEvent {
    public final String teamName;

    public OnFootballTeamCLickedEvent(String teamName)
    {
        this.teamName = teamName;
    }
}
