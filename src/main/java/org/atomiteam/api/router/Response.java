package org.atomiteam.api.router;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Represents an HTTP response in an AWS Lambda function, providing utilities for
 * setting status codes, content types, and body content.
 */
public class Response {

    private int httpCode;
    private Object body;
    private String contentType = "application/json";

    /**
     * Constructs a Response object with the given HTTP status code and body.
     *
     * @param httpCode the HTTP status code.
     * @param body     the response body.
     */
    public Response(int httpCode, Object body) {
        super();
        this.httpCode = httpCode;
        this.body = body;
    }

    /**
     * Sets the content type of the response.
     *
     * @param contentType the content type (e.g., "application/json").
     * @return the current Response instance for method chaining.
     */
    public Response contentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    /**
     * Retrieves the content type of the response.
     *
     * @return the content type.
     */
    public String contentType() {
        return contentType;
    }

    /**
     * Sets the HTTP status code of the response.
     *
     * @param httpCode the HTTP status code.
     * @return the current Response instance for method chaining.
     */
    public Response httpCode(int httpCode) {
        this.httpCode = httpCode;
        return this;
    }

    /**
     * Retrieves the HTTP status code of the response.
     *
     * @return the HTTP status code.
     */
    public int httpCode() {
        return httpCode;
    }

    /**
     * Retrieves the body of the response as a string.
     *
     * @return the body as a string. If the body is an object, it is serialized to JSON.
     * @throws RuntimeException if the body cannot be serialized.
     */
    public String body() {
        if (this.body == null) {
            return "";
        }
        if (this.body instanceof String) {
            return (String) this.body;
        }
        try {
            return new ObjectMapper().writeValueAsString(body);
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }
    }
}
