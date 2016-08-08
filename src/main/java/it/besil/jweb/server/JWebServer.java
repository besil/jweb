package it.besil.jweb.server;

import it.besil.jweb.app.JWebApp;
import it.besil.jweb.app.filter.FilterType;
import it.besil.jweb.app.filter.JWebFilter;
import it.besil.jweb.app.filter.JWebFilterHandler;
import it.besil.jweb.app.handlers.JWebHandler;
import it.besil.jweb.app.resources.HttpMethod;
import it.besil.jweb.app.resources.JWebController;
import it.besil.jweb.server.conf.JWebConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;
import spark.Filter;
import spark.Route;
import spark.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by besil on 09/06/2016.
 */
public class JWebServer {
    private final Service http;
    private Logger log;

    public JWebServer(JWebConfiguration conf) {
        if (conf.debugMode())
            System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, Level.DEBUG.name());
        log = LoggerFactory.getLogger(JWebServer.class);
        log.debug("Using conf\n{}", conf.toString());
        this.http = Service.ignite();
        this.http.port(conf.getServerPort());
        if (conf.getKeystorePath() != null && conf.getKeystorePassword() != null)
            http.secure(conf.getKeystorePath(), conf.getKeystorePassword(), null, null);
//        this.http.ipAddress("0.0.0.0");
    }

    public void addApp(JWebApp app) {
        log.debug("Installing app {}", app.getClass().getName());
        List<? extends JWebFilter> filters = app.getFilters();
        for (JWebFilter filter : filters) {
            this.install(filter);
        }
        List<? extends JWebController> resources = app.getControllers();
        for (JWebController resource : resources) {
            this.install(resource);
        }
    }

    private void install(JWebFilter filter) {
        JWebFilterHandler handler = filter.getHandler(http);
        String path = filter.getPath();
        FilterType filterType = filter.getType();

        try {
            Method m = http.getClass().getDeclaredMethod(filterType.name(), String.class, Filter.class);
            m.invoke(http, path, handler);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void install(JWebController resource) {
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

    public void stop() {
        this.http.stop();
    }

}
