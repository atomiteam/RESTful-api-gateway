package org.atomiteam.api.router;


import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Request {

    private static ObjectMapper objectMapper = new ObjectMapper()//
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    private APIGatewayProxyRequestEvent event;

    public Request(APIGatewayProxyRequestEvent event) {
        this.event = event;
    }

    public QueryString query() {
        return new QueryString(event.getQueryStringParameters());
    }

    public Headers headers() {
        return new Headers(event.getHeaders());
    }

    public <T> T body(Class<T> klazz) {
        try {
            return objectMapper.readValue(event.getBody(), klazz);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    
    
}