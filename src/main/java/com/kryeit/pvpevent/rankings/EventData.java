package com.kryeit.pvpevent.rankings;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class EventData {
    private File file;
    private Properties properties;

    public EventData(String filePath) throws IOException {
        this.file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }
        this.properties = new Properties();
        this.properties.load(new FileInputStream(file));
    }

    public void addElement(String name, boolean value) throws IOException {
        properties.setProperty(name, String.valueOf(value));
        properties.store(new FileOutputStream(file), null);
    }

    public boolean getElement(String name) {
        String value = properties.getProperty(name);
        if (value == null) {
            return false;
        }
        return Boolean.parseBoolean(value);
    }
}



