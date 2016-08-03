package it.besil.web.app;

import it.besil.web.app.resources.JWebResource;

import java.util.List;

/**
 * Created by besil on 03/08/2016.
 */
public abstract class JWebApp {

    public abstract List<? extends JWebResource> getResources();
}
