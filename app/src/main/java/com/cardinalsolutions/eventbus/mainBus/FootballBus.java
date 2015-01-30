package com.cardinalsolutions.eventbus.mainBus;

import com.squareup.otto.Bus;

/**
 * Created by adam on 12/1/14.
 */
public class FootballBus
{
    private static Bus instance = null;

    private FootballBus()
    {
        instance = new Bus();
    }

    public static Bus getInstance()
    {
        if(instance == null)
        {
            instance = new Bus();
        }
        return instance;
    }
}
