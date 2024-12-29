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

To register routes, use the `Router` class:

```java
router.register("GET", "/example", (input, context) -> {
    return new Response(200, Map.of("message", "Hello, World!"));
});
