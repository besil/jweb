package it.besil.jweb.app;

import it.besil.jweb.app.filter.JWebFilter;
import it.besil.jweb.app.resources.JWebController;
import it.besil.jweb.server.conf.JWebConfiguration;

import java.util.Arrays;
import java.util.List;

/**
 * Created by besil on 03/08/2016.
 */
public abstract class JWebApp {
    private final JWebConfiguration jwebConf;

    public JWebApp(JWebConfiguration jwebConf) {
        this.jwebConf = jwebConf;
    }

    public List<? extends JWebController> getControllers() {
        return Arrays.asList();
    }

    public List<? extends JWebFilter> getFilters() {
        return Arrays.asList();
    }

    public JWebConfiguration getJWebConf() {
        return jwebConf;
    }
}
