package org.atomiteam.api.router;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Response {

    private int httpCode;
    private Object body;
    private String contentType = "application/json";

    public Response(int httpCode, Object body) {
        super();
        this.httpCode = httpCode;
        this.body = body;
    }

    public Response contentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    public String contentType() {
        return contentType;
    }

    public Response httpCode(int httpCode) {
        this.httpCode = httpCode;
        return this;
    }

    public int httpCode() {
        return httpCode;
    }

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
