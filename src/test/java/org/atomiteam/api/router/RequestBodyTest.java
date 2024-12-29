package org.atomiteam.api.router;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;

class RequestBodyTest {
    static class TestBody {
        public String name;
        public int age;
    }

    @Test
    void testBodyParsing() {
        APIGatewayProxyRequestEvent requestEvent = new APIGatewayProxyRequestEvent();
        requestEvent.setBody("{\"name\":\"John\", \"age\":30}");

        Request request = new Request(requestEvent);
        TestBody body = request.body(TestBody.class);

        assertEquals("John", body.name);
        assertEquals(30, body.age);
    }
}
