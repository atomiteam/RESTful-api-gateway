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
public class LambdaHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private Router router;

    /**
     * Constructs a LambdaHandler and initializes the router.
     */
    public LambdaHandler() {
        init();
    }

    /**
     * Initializes the Router instance used for request handling.
     */
    public void init() {
        router = new Router();
    }

    /**
     * Handles the incoming API Gateway request and delegates it to the router.
     *
     * @param input   the API Gateway request event.
     * @param context the AWS Lambda context.
     * @return the API Gateway response event.
     */
    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {
        String method = input.getHttpMethod();
        String path = input.getResource();
        return router.route(method, path, input, context);
    }
}
