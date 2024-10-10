package com.jasonkaranik.jsonsimpleenhanced;

import java.util.Arrays;
import java.util.Map;

public class JSONObject extends org.json.simple.JSONObject {

    public JSONObject() {
        super();
    }

    public JSONObject(Map map) {
        super(map);
    }

    public JSONObject(String json) {
        super();
        Object obj = org.json.simple.JSONValue.parse(json);
        if (obj != null) {
            if (obj instanceof org.json.simple.JSONObject) {
                super.putAll((org.json.simple.JSONObject) obj);
            }
        }
    }

    public JSONObject(org.json.simple.JSONObject obj) {
        super();
        if (obj != null) {
            super.putAll(obj);
        }
    }

    public Object get(String path) {
        if (path == null || path.isEmpty()) {
            return null;
        }

        return findObject(this, path.split("\\."));
    }

    public boolean containsKey(String path) {
        if (path == null || path.isEmpty()) {
            return false;
        }

        return findObject(this, path.split("\\.")) != null;
    }

    private Object findObject(org.json.simple.JSONObject current, String[] path) {
        if (current == null || path == null || path.length == 0) {
            return null;
        }

        String currentPath = path[0];
        if (currentPath != null) {
            Object value = current.get(currentPath);

            if (value != null) {
                if (value instanceof org.json.simple.JSONObject && path.length > 1) {
                    return findObject((org.json.simple.JSONObject) value, Arrays.copyOfRange(path, 1, path.length));
                } else {
                    return value.getClass() == org.json.simple.JSONObject.class ? new JSONObject((org.json.simple.JSONObject) value) : value;
                }
            }
        }
        return null;
    }

    public void put(String path, Object newValue) {
        if (path == null || path.isEmpty()) {
            return;
        }

        changeObject(this, path.split("\\."), newValue);
    }

    private void changeObject(org.json.simple.JSONObject current, String[] path, Object newValue) {
        if (current == null || path == null || path.length == 0) {
            return;
        }

        String currentPath = path[0];
        if (currentPath != null) {
            Object currentValue = current.get(currentPath);

            if (currentValue != null) {
                if (currentValue instanceof org.json.simple.JSONObject && path.length > 1) {
                    changeObject((org.json.simple.JSONObject) currentValue, Arrays.copyOfRange(path, 1, path.length), newValue);
                    return;
                }
            }

            if (newValue == null) {
                current.remove(currentPath);
            } else {
                current.put(currentPath, newValue);
            }
        }
    }
}