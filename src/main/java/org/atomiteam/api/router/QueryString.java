package org.atomiteam.api.router;

import java.util.Map;

/**
 * Represents query parameters in an HTTP request and provides utility methods
 * for type-safe access to their values.
 */
public class QueryString {
    private final Map<String, String> queryParams;

    /**
     * Constructs a QueryString object.
     *
     * @param queryParams a map of query parameter key-value pairs. If null, an empty map is used.
     */
    public QueryString(Map<String, String> queryParams) {
        this.queryParams = queryParams == null ? Map.of() : queryParams;
    }

    /**
     * Retrieves the string value associated with a query parameter key.
     *
     * @param key the query parameter key.
     * @return the string value, or null if the key is not present.
     */
    public String stringValue(String key) {
        return queryParams.get(key);
    }

    /**
     * Retrieves the integer value associated with a query parameter key.
     *
     * @param key the query parameter key.
     * @return the integer value, or null if the key is not present or cannot be parsed.
     */
    public Integer intValue(String key) {
        String value = queryParams.get(key);
        return value != null ? Integer.parseInt(value) : null;
    }

    /**
     * Retrieves the double value associated with a query parameter key.
     *
     * @param key the query parameter key.
     * @return the double value, or null if the key is not present or cannot be parsed.
     */
    public Double doubleValue(String key) {
        String value = queryParams.get(key);
        return value != null ? Double.parseDouble(value) : null;
    }

    /**
     * Retrieves the boolean value associated with a query parameter key.
     *
     * @param key the query parameter key.
     * @return the boolean value, or null if the key is not present or cannot be parsed.
     */
    public Boolean booleanValue(String key) {
        String value = queryParams.get(key);
        return value != null ? Boolean.parseBoolean(value) : null;
    }

    /**
     * Retrieves the float value associated with a query parameter key.
     *
     * @param key the query parameter key.
     * @return the float value, or null if the key is not present or cannot be parsed.
     */
    public Float floatValue(String key) {
        String value = queryParams.get(key);
        return value != null ? Float.parseFloat(value) : null;
    }

    /**
     * Returns a string representation of the query parameters.
     *
     * @return a string representation of the query parameters.
     */
    @Override
    public String toString() {
        return "[queryParams=" + queryParams + "]";
    }
}
