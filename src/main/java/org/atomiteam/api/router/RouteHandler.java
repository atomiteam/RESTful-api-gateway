package org.atomiteam.api.router;

import com.amazonaws.services.lambda.runtime.Context;

/**
 * Defines the contract for handling HTTP requests routed by the Router.
 */
public interface RouteHandler {

    /**
     * Handles an HTTP request and returns a response.
     *
     * @param input   the request object containing headers, query parameters, and body.
     * @param context the AWS Lambda context.
     * @return the response object.
     */
    Response handleRequest(Request input, Context context);
}
