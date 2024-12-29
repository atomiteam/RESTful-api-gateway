package org.atomiteam.api.router;


import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

public class Router {

    private Map<String, Map<String, RouteHandler>> routes = new HashMap<>();

    // Register handler for a given method and path
    public void register(String method, String path, RouteHandler handler) {
        routes.computeIfAbsent(method, k -> new HashMap<>()).put(path, handler);
    }

    // Route the request to the appropriate handler
    public APIGatewayProxyResponseEvent route(String method, String path, APIGatewayProxyRequestEvent input,
            Context context) {
        try {
            Map<String, RouteHandler> methodRoutes = routes.get(method);
            if (methodRoutes != null) {
                RouteHandler handler = methodRoutes.get(path);
                if (handler != null) {
                    new Logger(context).log("Route %s %s %s", method, path, handler);
                    Response response = handler.handleRequest(new Request(input), context);
                    APIGatewayProxyResponseEvent apiResponse = new APIGatewayProxyResponseEvent();
                    apiResponse.setStatusCode(response.httpCode());
                    apiResponse.setBody(response.body());
                    apiResponse.withHeaders(Map.of("Content-Type", response.contentType()));
                    return apiResponse;
                }
            }
            APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
            response.setStatusCode(404);
            response.setBody("Not Found");
            return response;
        } catch (Exception e) {
            new Logger(context).log("General error %s %s", e.getMessage(), e.getStackTrace());
            context.getLogger().log("Error " + e.getMessage() + "" + e.getStackTrace());
            APIGatewayProxyResponseEvent apiResponse = new APIGatewayProxyResponseEvent();
            apiResponse.setStatusCode(503);
            apiResponse.setBody(e.getMessage());
            return apiResponse;
        }
    }
}
