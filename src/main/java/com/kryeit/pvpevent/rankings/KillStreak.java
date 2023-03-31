package com.kryeit.pvpevent.rankings;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.UUID;

public class KillStreak {
    private File file;
    private Properties properties;

    public KillStreak(String filePath) throws IOException {
        this.file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }
        this.properties = new Properties();
        this.properties.load(new FileInputStream(file));
    }

    public void setStreak(UUID playerID, int streak) throws IOException {
        if (streak > getStreak(playerID)) {
            properties.setProperty(playerID.toString(), Integer.toString(streak));
            properties.store(new FileOutputStream(file), null);
        }
    }

    public HashMap<UUID, Integer> getHashmap() {
        HashMap<UUID, Integer> streaks = new HashMap<>();
        for (String key : properties.stringPropertyNames()) {
            UUID uuid = UUID.fromString(key);
            int streak = Integer.parseInt(properties.getProperty(key, "0"));
            streaks.put(uuid, streak);
        }
        return streaks;
    }

    public int getStreak(UUID playerID) {
        return Integer.parseInt(properties.getProperty(playerID.toString(), "0"));
    }
}

