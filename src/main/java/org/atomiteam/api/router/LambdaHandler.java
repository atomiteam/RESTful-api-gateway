package org.atomiteam.api.router;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

/**
 * AWS Lambda handler class responsible for routing incoming API Gateway requests
 * to the appropriate route handlers.
 *
 * <p>This class implements the AWS Lambda {@link RequestHandler} interface,
 * allowing it to process API Gateway events and return structured responses.
 * It delegates the routing logic to the {@link Router} class.
 */
public abstract class LambdaHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    /**
     * The router instance used to handle routing logic for incoming requests.
     */
    protected Router router;

    /**
     * Constructs a LambdaHandler.
     */
    public LambdaHandler() {
        init();
    }

    /**
     * Initializes the {@link Router} instance used for request handling and sets up routes.
     *
     * <p>This method is called during the construction of the LambdaHandler instance.
     */
    protected void init() {
        router = new Router();
    }

    /**
     * Handles the incoming API Gateway request and delegates it to the router.
     *
     * <p>This method is the main entry point for Lambda function execution.
     * It extracts the HTTP method and resource path from the input and forwards
     * the request to the {@link Router} for processing.
     *
     * @param input   the API Gateway request event containing the HTTP method, headers, body, and path.
     * @param context the AWS Lambda context providing runtime information.
     * @return an {@link APIGatewayProxyResponseEvent} containing the response status, headers, and body.
     */
    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {
        String method = input.getHttpMethod();
        String path = input.getResource();
        return router.route(method, path, input, context);
    }
}
