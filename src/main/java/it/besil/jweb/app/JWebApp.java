package it.besil.jweb.app;

import it.besil.jweb.app.filter.JWebFilter;
import it.besil.jweb.app.resources.JWebController;

import java.util.Arrays;
import java.util.List;

/**
 * Created by besil on 03/08/2016.
 */
public abstract class JWebApp {

    public List<? extends JWebController> getControllers() {
        return Arrays.asList();
    }

    public List<? extends JWebFilter> getFilters() {
        return Arrays.asList();
    }
}
