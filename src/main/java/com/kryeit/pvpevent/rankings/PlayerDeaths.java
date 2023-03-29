package com.kryeit.pvpevent.rankings;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.UUID;

public class PlayerDeaths {
    private File file;
    private Properties properties;

    public PlayerDeaths(String filePath) throws IOException {
        this.file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }
        this.properties = new Properties();
        this.properties.load(new FileInputStream(file));
    }

    public void addDeath(UUID playerID) throws IOException {
        properties.setProperty(playerID.toString(), Integer.toString(getDeaths(playerID) + 1));
        properties.store(new FileOutputStream(file), null);
    }

    public int getDeaths(UUID playerID) {
        return Integer.parseInt(properties.getProperty(playerID.toString()));
    }

    public HashMap<UUID, Integer> getDeathsMap() {
        HashMap<UUID, Integer> kills = new HashMap<>();
        for (String key : properties.stringPropertyNames()) {
            UUID uuid = UUID.fromString(key);
            int killCount = Integer.parseInt(properties.getProperty(key, "0"));
            kills.put(uuid, killCount);
        }
        return kills;
    }

}
