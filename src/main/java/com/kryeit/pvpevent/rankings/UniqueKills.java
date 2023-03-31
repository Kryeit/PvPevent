package com.kryeit.pvpevent.rankings;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class UniqueKills {
    private File file;
    private Properties properties;

    public UniqueKills(String filePath) throws IOException {
        this.file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }
        this.properties = new Properties();
        this.properties.load(new FileInputStream(file));
    }

    public void addKill(UUID killerID, UUID victimID) throws IOException {
        Map<UUID, List<String>> killsMap = getKillsMap();
        if (!killsMap.containsKey(killerID)) {
            killsMap.put(killerID, new ArrayList<>());
        }
        List<String> killsList = killsMap.get(killerID);
        if (!killsList.contains(victimID.toString())) {
            killsList.add(victimID.toString());
            properties.setProperty(killerID.toString(), String.join(",", killsList));
            properties.store(new FileOutputStream(file), null);
        }
    }

    public int getKillsCount(UUID playerID) {
        Map<UUID, List<String>> killsMap = getKillsMap();
        List<String> killsList = killsMap.get(playerID);
        return killsList != null ? killsList.size() : 0;
    }

    private Map<UUID, List<String>> getKillsMap() {
        Map<UUID, List<String>> killsMap = new HashMap<>();
        for (String key : properties.stringPropertyNames()) {
            UUID uuid = UUID.fromString(key);
            String[] killsArray = properties.getProperty(key).split(",");
            List<String> killsList = new ArrayList<>();
            for (String kill : killsArray) {
                if (!kill.isEmpty()) {
                    killsList.add(kill);
                }
            }
            killsMap.put(uuid, killsList);
        }
        return killsMap;
    }
}

