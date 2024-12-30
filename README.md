
# API Router for AWS Lambda

This project provides a routing mechanism for AWS Lambda functions that are triggered by API Gateway, allowing the creation of RESTful APIs using Java. It features a modular and flexible design with components for request handling, query string parsing, header management, and routing.

## Features

- **Routing**: Define and handle routes based on HTTP methods and paths.
- **Request Parsing**: Easily access headers, query parameters, and body content.
- **Response Formatting**: Standardize API responses with customizable HTTP status codes and content types.
- **Logging**: Integrated logging mechanism using AWS Lambda's context.
- **AWS Lambda Integration**: Compatible with `APIGatewayProxyRequestEvent` and `APIGatewayProxyResponseEvent`.

## Project Structure

### Core Classes

1. **Router** (`Router.java`)
   - Registers and routes requests to appropriate handlers based on HTTP method and path.

2. **LambdaHandler** (`LambdaHandler.java`)
   - Entry point for AWS Lambda; initializes the router and handles incoming requests.

3. **Request** (`Request.java`)
   - Parses the incoming API Gateway event, providing access to headers, query parameters, and request body.

4. **Response** (`Response.java`)
   - Encapsulates API responses, allowing for structured and consistent HTTP responses.

5. **QueryString** (`QueryString.java`)
   - Parses query parameters for type-safe access.

6. **Headers** (`Headers.java`)
   - Provides utilities to process HTTP headers, such as extracting locale information.

7. **RouteHandler Interface** (`RouteHandler.java`)
   - Defines the contract for handling requests routed by the `Router`.

8. **Logger** (`Logger.java`)
   - Simplified logging utility for use within AWS Lambda's execution context.

## Usage

### Registering Routes

Below is an updated and improved example inspired by real-world implementations:

```java
import org.example.router.LambdaHandler;
import org.example.handlers.PageHandler;
import org.example.handlers.ApiHandler;
import org.example.storage.DataStore;
import org.example.templates.MessageResolver;
import org.example.templates.ResourceResolver;

public class CustomLambdaHandler extends LambdaHandler {

    public CustomLambdaHandler(DataStore dataStore) {
        super(dataStore);
    }

    public CustomLambdaHandler() {
        super();
    }

    @Override
    protected void initRoutes() {
        router.register("GET", "/home", new PageHandler(dataStore, new ResourceResolver()));
        router.register("POST", "/api/data", new ApiHandler(dataStore, new MessageResolver()));
    }
}
```

### Example Endpoint Logic

#### `/home` Endpoint
```java
public class PageHandler implements RouteHandler {
    private final DataStore dataStore;
    private final ResourceResolver resourceResolver;

    public PageHandler(DataStore dataStore, ResourceResolver resourceResolver) {
        this.dataStore = dataStore;
        this.resourceResolver = resourceResolver;
    }

    @Override
    public Response handleRequest(Request input, Context context) {
        return new Response(200, "<html>Welcome to our homepage</html>")
                .contentType("text/html");
    }
}
```

#### `/api/data` Endpoint
```java
public class ApiHandler implements RouteHandler {
    private final DataStore dataStore;
    private final MessageResolver messageResolver;

    public ApiHandler(DataStore dataStore, MessageResolver messageResolver) {
        this.dataStore = dataStore;
        this.messageResolver = messageResolver;
    }

    @Override
    public Response handleRequest(Request input, Context context) {
        // Logic to process and return data
        return new Response(200, Map.of("message", "Data processed successfully"));
    }
}
```

This example demonstrates how to structure route registrations and handler implementations effectively.
