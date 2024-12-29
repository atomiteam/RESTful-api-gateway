package org.atomiteam.api.router;


import com.amazonaws.services.lambda.runtime.Context;

public interface RouteHandler {
    Response handleRequest(Request input, Context context);
}
