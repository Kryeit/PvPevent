package com.kryeit.pvpevent;

import com.kryeit.pvpevent.listener.onPlayerDeath;
import com.kryeit.pvpevent.listener.onPlayerKill;
import com.kryeit.pvpevent.rankings.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.*;

public final class PvPevent extends JavaPlugin {

    public static TotalKills totalKills;
    public static UniqueKills uniqueKills;
    public static PlayerDeaths playerDeaths;
    public static EventData eventData;
    public static KillStreak killStreak;

    public static HashMap<UUID,List<UUID>> killStreaks = new HashMap<>();

    @Override
    public void onEnable() {

        try {
            totalKills = new TotalKills("plugins/PvPevent/totalKills");
            uniqueKills = new UniqueKills("plugins/PvPevent/uniqueKills");
            playerDeaths = new PlayerDeaths("plugins/PvPevent/playerDeaths");
            eventData = new EventData("plugins/PvPevent/eventData");
            killStreak = new KillStreak("plugins/PvPevent/killStreak");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Bukkit.getPluginManager().registerEvents(new onPlayerKill(), this);
        Bukkit.getPluginManager().registerEvents(new onPlayerDeath(), this);

        startEvent();
        stopEvent();
    }

    public void startEvent() {
        Timer timer = new Timer();

        Calendar scheduledTime = Calendar.getInstance();
        scheduledTime.setTimeZone(TimeZone.getTimeZone("GMT+1"));
        scheduledTime.set(2023, Calendar.APRIL, 1, 1, 0, 0);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Bukkit.broadcastMessage("PvP event has started! Don't worry, pvp is disabled outside arenas.");
                try {
                    eventData.addElement("eventRunning", true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, scheduledTime.getTime());
    }

    public void stopEvent() {
        Timer timer = new Timer();

        Calendar scheduledTime = Calendar.getInstance();
        scheduledTime.setTimeZone(TimeZone.getTimeZone("GMT+1"));
        scheduledTime.set(2023, Calendar.APRIL, 8, 1, 0, 0);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Bukkit.broadcastMessage("PvP event has ended! Rewards will be given manually to each winner.");
                try {
                    eventData.addElement("eventRunning", false);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, scheduledTime.getTime());
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
