package org.atomiteam.api.router;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.jupiter.api.Test;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;

class RequestTest {
    @Test
    void testQueryStringParsing() {
        APIGatewayProxyRequestEvent requestEvent = new APIGatewayProxyRequestEvent();
        requestEvent.setQueryStringParameters(Map.of("key", "value"));

        Request request = new Request(requestEvent);
        QueryString queryString = request.query();

        assertEquals("value", queryString.stringValue("key"));
    }
}
