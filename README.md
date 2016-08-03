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

In JWeb, there are a very few concepts to remember: an **app**

This is your main:
``` java
public static void main(String[] args) throws IOException {
    JWebConfiguration conf = new JWebConfiguration();
    JWebServer jweb = new JWebServer(conf);
    jweb.addApp(new EchoApp());
}
```

