package com.cardinalsolutions.eventbus.FootballBusEvents;

/**
 * Created by adam on 12/3/14.
 */
public class OnTeamDetailModifiedEvent
{
    public final String teamName;
    public final String oldTeamName;

    public OnTeamDetailModifiedEvent(String oldTeamName, String newTeamName)
    {
        this.oldTeamName = oldTeamName;
        teamName = newTeamName;
    }
}
