package it.besil.jweb.server;

import it.besil.jweb.app.JWebApp;
import it.besil.jweb.app.handlers.JWebHandler;
import it.besil.jweb.app.resources.HttpMethod;
import it.besil.jweb.app.resources.JWebResource;
import it.besil.jweb.server.conf.JWebConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;
import spark.Route;
import spark.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by besil on 09/06/2016.
 */
public class JWebServer {
    private final Service http;
    private Logger log;
    private List<JWebApp> apps;

    public JWebServer(JWebConfiguration conf) {
        if (conf.debugMode())
            System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, Level.DEBUG.name());
        log = LoggerFactory.getLogger(JWebServer.class);
        log.debug("Using conf\n{}", conf.toString());
        this.http = Service.ignite();
        this.apps = new LinkedList<>();
    }

    public void start() {
        for (JWebApp app : apps) {
            log.debug("Installing app {}", app.getClass().getName());
            List<? extends JWebResource> resources = app.getResources();
            for (JWebResource resource : resources) {
                this.install(resource);
            }
        }
        log.info("Server started");
    }

    private void install(JWebResource resource) {
        JWebHandler handler = resource.getHandler();
        HttpMethod method = resource.getMethod();
        String path = resource.getPath();

        try {
            Method m = http.getClass().getDeclaredMethod(method.name(), String.class, Route.class);
            m.invoke(http, path, handler);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    public void addApp(JWebApp app) {
        this.apps.add(app);
    }
}
