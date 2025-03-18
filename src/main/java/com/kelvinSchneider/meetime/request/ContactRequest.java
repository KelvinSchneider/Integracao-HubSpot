package com.kelvinSchneider.meetime.request;

import java.util.HashMap;
import java.util.Map;

public class ContactRequest {

    private Map<String, String> properties;

    public ContactRequest() {
        this.properties = new HashMap<>();
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    public void addProperty(String key, String value) {
        this.properties.put(key, value);
    }
}