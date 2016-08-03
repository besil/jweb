# JWeb

JWeb is a framework for developing and testing easily rest services.
It is built around [Spark](http://sparkjava.com/),
according with the [Spark testing guide](https://sparktutorials.github.io/2015/07/30/spark-testing-unit.html)

The framework aims to be a library with support for common funcionalities
(such as pojo request mapping, session management),
similarly to Django or other common web frameworks.


## Installation
Please clone the repo and include it as maven dependency
``` xml
<dependency>
    <groupId>it.besil.web</groupId>
    <artifactId>jweb</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Quickstart
We will create a simple Echo Rest Server, with unit testing.

In JWeb, there are a very few concepts to remember: an **App** is the main block of your application. An App
incapsulates one or more **Controllers** and **Filters**.

**Controllers** defines the business logic for a resource. A Controller is made of 3 elements:

1. A _path_, which is the resource url exposed by the server

2. A _method_ (ie GET, POST, ...) for the resource

3. A _handler_, which contains the business logic associated to the specific resource for that method

An **handler** consumes a **Payload** and produce an **Answer**, according to the business logic.

**Payloads** are user defined POJO, which extracts parameters from http requests.
**Answers** are simple objects with a status code and a map of key-values to return.

**Filters** are very similar to handlers, but they are executed _before_ or _after_ each request/response.

Enough reading, let's see some code

This is your main:
``` java
public static void main(String[] args) throws IOException {
    JWebConfiguration conf = new JWebConfiguration();
    JWebServer jweb = new JWebServer(conf);
    jweb.addApp(new EchoApp());
}
```

The EchoApp is:
``` java
public class EchoApp extends JWebApp {
    public static class EchoPayload implements Payload {
        private String message;

        public EchoPayload() {

        }

        public void init(Request req) {
            this.message = req.queryParams("message");
        }

        public String getMessage() {
            return message;
        }
    }

    public static class GetEchoHandler extends JWebHandler<EchoPayload> {
        public GetEchoHandler() {
            super(EchoPayload.class);
        }

        @Override
        public Answer process(EchoPayload ep) {
            return new SuccessAnswer("message", "Echo: " + ep.getMessage());
        }
    }

    @Override
        public List<? extends JWebController> getControllers() {
            return Arrays.asList(new JWebController() {
                public HttpMethod getMethod() {
                    return HttpMethod.get;
                }

                public JWebHandler getHandler() {
                    return new GetEchoHandler();
                }

                public String getPath() {
                    return "/api/echo";
                }
            });
        }
}
```