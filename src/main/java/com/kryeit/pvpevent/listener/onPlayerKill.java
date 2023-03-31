package com.kryeit.pvpevent.listener;

import com.kryeit.pvpevent.API;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.io.IOException;

public class onPlayerKill implements Listener {

    @EventHandler
    public void onPlayerDeath (PlayerDeathEvent e) throws IOException {
        if(!API.isEventActive()) return;
        Player victim = e.getEntity();
        Player killer = victim.getKiller();
        if(killer == null) return;
        API.slaySuccess(killer.getUniqueId(),victim.getUniqueId());
    }
}
