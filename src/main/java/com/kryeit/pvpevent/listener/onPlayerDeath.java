package com.kryeit.pvpevent.listener;

import com.kryeit.pvpevent.API;
import com.kryeit.pvpevent.PvPevent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.io.IOException;

public class onPlayerDeath implements Listener {

    @EventHandler
    public void onPlayerDie (PlayerDeathEvent e) throws IOException {
        if(!API.isEventActive()) return;
        Player p = e.getEntity();
        PvPevent.playerDeaths.addDeath(p.getUniqueId());
    }
}
