package org.atomiteam.api.router;


import java.util.Map;

public class QueryString {
    private final Map<String, String> queryParams;

    public QueryString(Map<String, String> queryParams) {
        this.queryParams = queryParams == null ? Map.of() : queryParams;
    }

    public String stringValue(String key) {
        return queryParams.get(key);
    }

    public Integer intValue(String key) {
        String value = queryParams.get(key);
        return value != null ? Integer.parseInt(value) : null;
    }

    public Double doubleValue(String key) {
        String value = queryParams.get(key);
        return value != null ? Double.parseDouble(value) : null;
    }

    public Boolean booleanValue(String key) {
        String value = queryParams.get(key);
        return value != null ? Boolean.parseBoolean(value) : null;
    }

    public Float floatValue(String key) {
        String value = queryParams.get(key);
        return value != null ? Float.parseFloat(value) : null;
    }

    @Override
    public String toString() {
        return "[queryParams=" + queryParams + "]";
    }

}