package it.besil.web.app.resources;

import it.besil.web.app.handlers.JWebHandler;

/**
 * Created by besil on 03/08/2016.
 */
public abstract class JWebResource {
    public abstract HttpMethod getMethod();

    public abstract JWebHandler getHandler();

    public abstract String getPath();
}
