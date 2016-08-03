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



This is your main:
``` java
public static void main(String[] args) throws IOException {
    JWebConfiguration conf = new JWebConfiguration();
    JWebServer jweb = new JWebServer(conf);
    jweb.addApp(new EchoApp());
}
```

