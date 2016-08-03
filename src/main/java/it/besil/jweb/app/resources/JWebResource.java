package it.besil.jweb.app.resources;

import it.besil.jweb.app.handlers.JWebHandler;

/**
 * Created by besil on 03/08/2016.
 */
public interface JWebResource {
    HttpMethod getMethod();

    JWebHandler getHandler();

    String getPath();
}
