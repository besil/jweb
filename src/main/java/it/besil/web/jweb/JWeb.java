package it.besil.web.jweb;

import it.besil.web.app.JWebApp;
import it.besil.web.app.handlers.JWebHandler;
import it.besil.web.app.resources.HttpMethod;
import it.besil.web.app.resources.JWebResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Route;
import spark.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by besil on 09/06/2016.
 */
public class JWeb {
    private final Service http;
    private Logger log = LoggerFactory.getLogger(JWeb.class);
    private List<JWebApp> apps;

    public JWeb() {
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
