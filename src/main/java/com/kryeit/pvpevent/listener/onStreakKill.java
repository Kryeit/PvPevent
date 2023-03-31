package com.kryeit.pvpevent.listener;

import com.kryeit.pvpevent.API;
import com.kryeit.pvpevent.PvPevent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.io.IOException;

public class onStreakKill implements Listener {

    @EventHandler
    public void onPlayerDeath (PlayerDeathEvent e) throws IOException {
        if(!API.isEventActive()) return;
        Player victim = e.getEntity();
        Player killer = victim.getKiller();
        if(killer == null) return;
        if(!PvPevent.killStreaks.get(killer.getUniqueId()).contains(victim.getUniqueId())) {
            PvPevent.killStreaks.get(killer.getUniqueId()).add(victim.getUniqueId());
        }
        if(PvPevent.killStreaks.get(killer.getUniqueId()).size() > PvPevent.killStreak.getStreak(killer.getUniqueId())) {
            PvPevent.killStreak.setStreak(killer.getUniqueId(),PvPevent.killStreaks.get(killer.getUniqueId()).size());
        }
    }
}
