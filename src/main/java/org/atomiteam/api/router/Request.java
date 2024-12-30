package org.atomiteam.api.router;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Represents an HTTP request in an AWS Lambda function, providing utilities for
 * accessing headers, query parameters, and the request body.
 */
public class Request {

    private static ObjectMapper objectMapper = new ObjectMapper()//
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    private APIGatewayProxyRequestEvent event;

    /**
     * Constructs a Request object with the given API Gateway request event.
     *
     * @param event the API Gateway request event.
     */
    public Request(APIGatewayProxyRequestEvent event) {
        this.event = event;
    }

    /**
     * Retrieves the query string parameters as a QueryString object.
     *
     * @return the QueryString object.
     */
    public QueryString query() {
        return new QueryString(event.getQueryStringParameters());
    }

    /**
     * Retrieves the headers as a Headers object.
     *
     * @return the Headers object.
     */
    public Headers headers() {
        return new Headers(event.getHeaders());
    }

    /**
     * Deserializes the request body into an object of the specified type.
     *
     * @param <T>   the type of the deserialized object.
     * @param klazz the class of the object to deserialize.
     * @return the deserialized object.
     * @throws RuntimeException if the body cannot be parsed.
     */
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
