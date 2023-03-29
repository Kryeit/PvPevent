package com.kryeit.pvpevent;

import java.io.IOException;
import java.util.UUID;

import static com.kryeit.pvpevent.PvPevent.totalKills;
import static com.kryeit.pvpevent.PvPevent.uniqueKills;

public class API {

    public static void slaySuccess(UUID killer, UUID victim) throws IOException {
        totalKills.addKill(killer);
        uniqueKills.addKill(killer,victim);
    }

    public static boolean isEventActive(){
        return PvPevent.eventData.getElement("eventActive");
    }
}
