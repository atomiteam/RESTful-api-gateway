package org.atomiteam.api.router;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.jupiter.api.Test;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

class RouterTest {
    @Test
    void testRouteValidRequest() {
        Router router = new Router();
        router.register("GET", "/test", (req, ctx) -> new Response(200, Map.of("message", "Success")));

        APIGatewayProxyRequestEvent request = new APIGatewayProxyRequestEvent();
        request.setHttpMethod("GET");
        request.setResource("/test");

        Context context = null; // Mock or stub context if needed
        APIGatewayProxyResponseEvent response = router.route("GET", "/test", request, context);

        assertEquals(200, response.getStatusCode());
        assertEquals("{\"message\":\"Success\"}", response.getBody());
    }
}
